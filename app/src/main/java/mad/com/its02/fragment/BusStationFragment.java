package mad.com.its02.fragment;

import android.view.View;
import android.widget.Button;

import mad.com.its02.R;

public class BusStationFragment extends BaseFragment implements View.OnClickListener {

    private Button detail;

    @Override
    protected int setLayoutId() {

        return R.layout.fragment_bus_station;

    }



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        detail=(Button)mView.findViewById(R.id.detail);
        detail.setOnClickListener(this);
        gotoFragment(R.id.bus_content,new BusStationinfoFragment());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.detail:
                gotoFragment(R.id.bus_content,new BusDetailFragment());
        }
    }

}
