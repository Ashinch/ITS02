package mad.com.its02.fragment;

import android.view.View;
import android.widget.Button;

import mad.com.its02.R;

public class RoadFragment extends BaseFragment implements View.OnClickListener{
    private Button mBtnTraffic;
    private Button mBtnLive;
    private Button mBtnRestriction;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_road;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBtnTraffic = (Button) mView.findViewById(R.id.btn_road_traffic);
        mBtnLive = (Button) mView.findViewById(R.id.btn_road_live);
        mBtnRestriction = (Button) mView.findViewById(R.id.btn_road_restriction);

        mBtnTraffic.setOnClickListener(this);
        mBtnLive.setOnClickListener(this);
        mBtnRestriction.setOnClickListener(this);

        gotoFragment(R.id.road_content,new RoadTrafficFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //  红绿灯
            case R.id.btn_road_traffic:
                gotoFragment(R.id.road_content,new RoadTrafficFragment());
                break;
            //  道路实况
            case R.id.btn_road_live:
                gotoFragment(R.id.road_content,new RoadLiveFragment());
                break;
            //  出行限制
            case R.id.btn_road_restriction:
                gotoFragment(R.id.road_content,new RoadRestrictFragment());
                break;

        }
    }
}
