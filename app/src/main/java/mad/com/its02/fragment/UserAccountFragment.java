package mad.com.its02.fragment;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import mad.com.its02.R;
import mad.com.its02.bean.DepositRecord;
import mad.com.its02.dao.DepositRecordDao;
import mad.com.its02.request.BaseRequest;
import mad.com.its02.request.GetBalanceRequest;
import mad.com.its02.request.SetBalanceRequest;
import mad.com.its02.utils.MyToast;
import mad.com.its02.utils.Session;

public class UserAccountFragment extends BaseFragment implements View.OnClickListener {
    private TextView textView;
    private String result;
    private Spinner spinner;
    private String CarId[]={"1","2","3","4"};
    private Button recharge;
    private EditText NumberET;
    private int Carid=0;
    private int money=0;
    private String value;
    private TextView CarAccount;
    private Button btn_query;
    private DepositRecordDao drDao = new DepositRecordDao(mContext);

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_user_account;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        spinner=(Spinner)mView.findViewById(R.id.account_sp);
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(getContext(),R.layout.spinner_item,CarId);
        spinner.setAdapter(arrayAdapter);
        recharge=(Button)mView.findViewById(R.id.btn_recharge) ;
        NumberET=(EditText) mView.findViewById(R.id.car_recharge_ev);
        CarAccount=(TextView)mView.findViewById(R.id.car_account);
        btn_query=(Button)mView.findViewById(R.id.btn_account_query);

        recharge.setOnClickListener(this);
        btn_query.setOnClickListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
//                Carid = Integer.valueOf(spinner.getSelectedItem().toString().trim());
                Carid = position + 1;
                Log.i("carid", "Carid=" + Carid);
            }
            @Override
            public void onNothingSelected (AdapterView < ? > parent){

            }

        });


    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_recharge:
                value=NumberET.getText().toString().trim();
                money=Integer.valueOf(value);
                SetBalanceRequest setBalanceRequest = new SetBalanceRequest(mContext);
                Log.i("carid", "Carid=" + Carid);
                setBalanceRequest.setCarId(Carid);
                setBalanceRequest.setMoney(money);
                setBalanceRequest.connec(new BaseRequest.OnGetDataListener() {
                    @Override
                    public void onReturn(Object data) {
                        if (data.equals("ok")) {

                            DepositRecord depositRecord = new DepositRecord();
                            depositRecord.setUserId(Session.user.getId());
                            depositRecord.setUserName(Session.user.getUsername());
                            depositRecord.setCarId(Carid);
                            depositRecord.setMoney(money);

                            Date currentTime = new Date();
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                            depositRecord.setDepositDate(formatter.format(currentTime));

                            drDao.insert(depositRecord);

                            MyToast.getToast(getActivity(),
                                    "充值成功");
                        }
                    }
                });
                break;
            case R.id.btn_account_query:
                getBalance();
                break;
        }
    }

    //查询余额
    private void getBalance() {
        GetBalanceRequest getBalanceRequest = new GetBalanceRequest(mContext);
        Log.i("carid", "Carid=" + Carid);
        getBalanceRequest.setCarId(Carid);
        getBalanceRequest.connec(new BaseRequest.OnGetDataListener() {

            @Override
            public void onReturn(Object data) {
                if (UserAccountFragment.this.isAdded()) {
                    CarAccount.setText(data.toString()+"元");
                }
            }
        });
    }
}
