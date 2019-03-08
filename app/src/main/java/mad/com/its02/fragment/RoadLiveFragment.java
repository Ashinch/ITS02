package mad.com.its02.fragment;

import android.view.View;
import android.widget.TextView;

import mad.com.its02.R;
import mad.com.its02.request.BaseRequest;
import mad.com.its02.request.RoadStatusRequest;

public class RoadLiveFragment extends BaseFragment {
    private TextView[] tvRoad = new TextView[5];
    private boolean flag = true;
    private int[] colors = {
            R.color.road_tongchang,
            R.color.road_jiaotongchang,
            R.color.road_yongji,
            R.color.road_dusai,
            R.color.road_baobiao,
    };
    private String[] sStatus = {
            "通畅",
            "较通畅",
            "拥挤",
            "堵塞",
            "爆表"
    };

    private int[] iStatus = new int[5];

    private Thread thread;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_road_live;
    }

    @Override
    protected void initData() {
        tvRoad[0] = (TextView) mView.findViewById(R.id.tv_road_1);
        tvRoad[1] = (TextView) mView.findViewById(R.id.tv_road_2);
        tvRoad[2] = (TextView) mView.findViewById(R.id.tv_road_3);
        tvRoad[3] = (TextView) mView.findViewById(R.id.tv_road_4);
        tvRoad[4] = (TextView) mView.findViewById(R.id.tv_road_5);
    }

    @Override
    protected void initView() {
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                while (flag) {
                    for (int i=0; i<5; i++) {
                        getRoadStatus(i);
                    }
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();

        tvRoad[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
            }
        });
    }

    private void getRoadStatus(final int i) {
        RoadStatusRequest roadStatusRequest = new RoadStatusRequest(mContext);
        roadStatusRequest.setRoadId(i);
        roadStatusRequest.connec(new BaseRequest.OnGetDataListener() {
            @Override
            public void onReturn(Object data) {
                tvRoad[i].setText(sStatus[(int)data - 1]);
                tvRoad[i].setBackgroundColor(getResources().getColor(colors[(int)data - 1]));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        flag = false;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
