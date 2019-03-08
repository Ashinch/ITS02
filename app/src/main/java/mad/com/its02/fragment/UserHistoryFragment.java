package mad.com.its02.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mad.com.its02.R;
import mad.com.its02.bean.DepositRecord;
import mad.com.its02.bean.User;
import mad.com.its02.dao.DepositRecordDao;
import mad.com.its02.dao.UserDao;
import mad.com.its02.utils.MyToast;
import mad.com.its02.utils.Session;
import mad.com.its02.utils.SpinnerFactory;

/*
 *      用户历史记录fragment
 */

public class UserHistoryFragment extends BaseFragment implements View.OnClickListener{
    private Spinner spnOperator;
    private Spinner spnSortWay;
    private Button btnQuery;
    private UserDao userDao;
    private DepositRecordDao depositRecordDao = new DepositRecordDao(mContext);
    private int userId = 0;
    private ListView lvRecord;
    private List<DepositRecord> drList = new ArrayList<>();
    private TextView tvUserName;
    private TextView tvCarId;
    private TextView tvMoney;
    private TextView tvDate;
    private int sortWay = 0;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
        }
    };

    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return drList.size();
        }

        @Override
        public Object getItem(int position) {
            return drList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater(mSavedInstanceState);
            View view;

            if (convertView == null) {
                //  因为getView()返回的对象，adapter会自动赋给ListView
                view = inflater.inflate(R.layout.item_record,null);
            } else {
                //  有缓存，不需要重新生成
                view = convertView;
            }

            tvUserName = (TextView) view.findViewById(R.id.item_username);
            tvCarId = (TextView) view.findViewById(R.id.item_car_id);
            tvMoney = (TextView) view.findViewById(R.id.item_money);
            tvDate = (TextView) view.findViewById(R.id.item_date);

            tvUserName.setText("充值者：" + drList.get(position).getUserName());
            tvCarId.setText("车辆编号：" + drList.get(position).getCarId());
            tvMoney.setText("充值金额：" + drList.get(position).getMoney() + " 元");
            tvDate.setText(drList.get(position).getDepositDate());

            return view;
        }
    };

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_user_history;
    }

    @Override
    protected void initData() {
        btnQuery = (Button) mView.findViewById(R.id.btn_query_record);
        lvRecord = (ListView) mView.findViewById(R.id.lv_record);
        lvRecord.setAdapter(adapter);
        userDao = new UserDao(mContext);
        final List<User> users = userDao.selectAll();
        String[] operators;

        if (Session.user.getPermission() > 0) {
            operators = new String[users.size() + 1];
            operators[0] = "全部";
            for (int i=0;i<users.size();i++) {
                operators[i+1] = users.get(i).getUsername();
            }
        } else {
            operators = new String[]{Session.user.getUsername()};
        }


        SpinnerFactory.getSpinner(mContext,
                (Spinner) mView.findViewById(R.id.spinner_operator),
                operators, new SpinnerFactory.OnItemSelected() {
                    @Override
                    public void onSelected(int position) {
                        if (Session.user.getPermission() > 0) {
                            userId = position == 0 ? 0 : users.get(--position).getId();
                        } else {
                            userId = Session.user.getId();
                        }
                    }
                });

        SpinnerFactory.getSpinner(mContext,
                (Spinner) mView.findViewById(R.id.spinner_sort_way),
                new String[]{"时间降序", "时间升序"}, new SpinnerFactory.OnItemSelected() {
                    @Override
                    public void onSelected(int position) {
                        sortWay = position;
                    }
                });
    }

    @Override
    protected void initView() {
        btnQuery.setOnClickListener(this);
        queryRecord();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_query_record:
                queryRecord();
                break;
        }
    }

    private void queryRecord () {
        drList.clear();
        adapter.notifyDataSetChanged();
        if (userId == 0) {
            drList = depositRecordDao.selectAll();
        } else {
            drList = depositRecordDao.queryByUserId(Integer.toString(userId));
        }
        sortRecord();
        Message message = handler.obtainMessage();
        message.obj = drList;
        handler.sendMessage(message);

    }

    private void sortRecord() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        for (int i=0; i<drList.size()-1; i++) {
            for (int j=1; j<drList.size()-i-1; j++) {
                try {
                    Date date1 = df.parse(drList.get(j).getDepositDate());
                    Date date2 = df.parse(drList.get(j+1).getDepositDate());

                    if (sortWay == 0) {
                        //  时间降序
                        if (date1.before(date2)) {
                            DepositRecord temp = drList.get(j);
                            drList.set(j,drList.get(j+1));
                            drList.set(j+1,temp);
                        }
                    } else {
                        //  时间升序
                        if (date2.before(date1)) {
                            DepositRecord temp = drList.get(j);
                            drList.set(j,drList.get(j+1));
                            drList.set(j+1,temp);
                        }
                    }

                }catch (ParseException e) {
                    MyToast.getToastLong(mContext,e.toString());
                }
            }
        }
    }

}

