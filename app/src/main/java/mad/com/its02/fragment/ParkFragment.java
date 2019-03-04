package mad.com.its02.fragment;

import android.view.View;
import android.widget.Button;

import mad.com.its02.R;

public class ParkFragment extends BaseFragment implements View.OnClickListener{
    private Button mBtnParkInfo;
    private Button mBtnParkQuery;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_park;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBtnParkInfo = (Button) mView.findViewById(R.id.btn_park_info);
        mBtnParkQuery = (Button) mView.findViewById(R.id.btn_park_query);

        mBtnParkInfo.setOnClickListener(this);
        mBtnParkQuery.setOnClickListener(this);

        gotoFragment(R.id.park_content,new ParkInfoFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //  停车场信息
            case R.id.btn_park_info:
                gotoFragment(R.id.park_content,new ParkInfoFragment());
                break;
            //  收费查询
            case R.id.btn_park_query:
                gotoFragment(R.id.park_content,new ParkQueryFragment());
                break;
        }
    }
}
