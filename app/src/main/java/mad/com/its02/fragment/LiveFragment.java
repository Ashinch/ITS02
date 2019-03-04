package mad.com.its02.fragment;

import android.view.View;
import android.widget.Button;

import mad.com.its02.R;

public class LiveFragment extends BaseFragment implements View.OnClickListener{
    private Button mBtnLiveIndex;
    private Button mBtnLiveWeather;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBtnLiveIndex = (Button) mView.findViewById(R.id.btn_live_index);
        mBtnLiveWeather = (Button) mView.findViewById(R.id.btn_live_weather);

        mBtnLiveIndex.setOnClickListener(this);
        mBtnLiveWeather.setOnClickListener(this);

        gotoFragment(R.id.live_content,new LiveIndexFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //  生活指数
            case R.id.btn_live_index:
                gotoFragment(R.id.live_content,new LiveIndexFragment());
                break;
            //  天气信息
            case R.id.btn_live_weather:
                gotoFragment(R.id.live_content,new LiveWeatherFragment());
                break;
        }
    }
}