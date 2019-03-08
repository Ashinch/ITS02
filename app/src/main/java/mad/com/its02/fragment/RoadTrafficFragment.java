package mad.com.its02.fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mad.com.its02.R;
import mad.com.its02.bean.Traffic;
import mad.com.its02.config.AppConfig;
import mad.com.its02.dao.TrafficDao;
import mad.com.its02.http.NetUtil;

public class RoadTrafficFragment extends BaseFragment {
    private String[] sSortWay = {
            "路口升序",
            "路口降序",
            "红灯升序",
            "红灯降序",
            "黄灯升序",
            "黄灯降序",
            "绿灯升序",
            "绿灯降序",
    };
    private int sortCol = 0;
    private int sortWay = 0;
    private Spinner spnSortWay;
    private Button btnQuery;
    private TextView tvRoadId;
    private TextView tvRedTime;
    private TextView tvYellowTime;
    private TextView tvGreenTime;
    private NetUtil netUtil;
    private TrafficDao trafficDao;
    private List<Traffic> traffics = new ArrayList<>();
    private ListView lvTraffic;
    private BaseAdapter adapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return traffics.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
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
                view = inflater.inflate(R.layout.item_traffic,null);
            } else {
                view = convertView;
            }

            tvRoadId = (TextView) view.findViewById(R.id.item_road_id);
            tvRedTime = (TextView) view.findViewById(R.id.item_red_time);
            tvYellowTime = (TextView) view.findViewById(R.id.item_yellow_time);
            tvGreenTime = (TextView) view.findViewById(R.id.item_green_time);

            tvRoadId.setText(Integer.toString(traffics.get(position).getId()));
            tvRedTime.setText(Integer.toString(traffics.get(position).getRedTime()));
            tvYellowTime.setText(Integer.toString(traffics.get(position).getYellowTime()));
            tvGreenTime.setText(Integer.toString(traffics.get(position).getGreenTime()));

            return view;
        }
    };

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            adapter.notifyDataSetChanged();
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            adapter.notifyDataSetChanged();
        }
    };

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_road_traffic;
    }

    @Override
    protected void initData() {
        btnQuery = (Button) mView.findViewById(R.id.btn_query_traffic);
        lvTraffic = (ListView) mView.findViewById(R.id.lv_traffic);
        btnQuery = (Button) mView.findViewById(R.id.btn_query_traffic);
        spnSortWay = (Spinner) mView.findViewById(R.id.spinner_sort_way);
        netUtil = new NetUtil();
    }

    @Override
    protected void initView() {
        lvTraffic.setAdapter(adapter);

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query();
            }
        });

        spnSortWay.setAdapter(new ArrayAdapter<String>(mContext,R.layout.spinner_item,sSortWay));
        spnSortWay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sortWay = position % 2 == 0 ? 0 : 1;
                sortCol = position < 2 ? 0
                        : position < 4 ? 1
                        : position < 6 ? 2
                        : 3;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        query();

    }

    private void query() {
        traffics.clear();
        adapter.notifyDataSetChanged();
        new Thread() {
            @Override
            public void run() {
                for (int i=0;i<4;i++) {
                    getTrafficInfo(i);
                }
                
                sortList();
                
                Message message = handler.obtainMessage();
                message.obj = traffics;
                handler.sendMessage(message);
            }
        }.start();
    }

    private void sortList() {
        for (int i=0; i<traffics.size()-1; i++) {
            for (int j=0; j<traffics.size()-i-1;j++) {
                int[] lj = new int[] {
                        traffics.get(j).getId(),
                        traffics.get(j).getRedTime(),
                        traffics.get(j).getYellowTime(),
                        traffics.get(j).getGreenTime(),
                };
                int[] lj1 = new int[]{
                        traffics.get(j + 1).getId(),
                        traffics.get(j + 1).getRedTime(),
                        traffics.get(j + 1).getYellowTime(),
                        traffics.get(j + 1).getGreenTime(),
                };

                if (sortWay == 0) {
                    if (lj[sortCol] > lj1[sortCol]) {
                        Traffic temp = traffics.get(j);
                        traffics.set(j,traffics.get(j+1));
                        traffics.set(j+1,temp);
                    }
                } else {
                    if (lj[sortCol] < lj1[sortCol]) {
                        Traffic temp = traffics.get(j);
                        traffics.set(j,traffics.get(j+1));
                        traffics.set(j+1,temp);
                    }
                }
            }
        }
    }

    private void getTrafficInfo(int i) {
        try {
            JSONObject json = new JSONObject();
            json.put(AppConfig.KEY_TRAFFIC_LIGHT_ID,i+1);
            String result = netUtil.postData(mBasURL + AppConfig.GET_LIGHT_MSG,json.toString());
            json = new JSONObject(result);
            Log.d("result", result);
            Traffic traffic = new Traffic();
            traffic.setId(i + 1);
            traffic.setRedTime(json.getInt(AppConfig.KEY_LIGHT_RED));
            traffic.setYellowTime(json.getInt(AppConfig.KEY_LIGHT_YELLOW));
            traffic.setGreenTime(json.getInt(AppConfig.KEY_LIGHT_GREEN));
            traffics.add(traffic);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
