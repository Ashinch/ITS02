package mad.com.its02.fragment;

import android.widget.TextView;

import mad.com.its02.R;
import mad.com.its02.request.BaseRequest;
import mad.com.its02.request.CarSpeedRequest;

public class UserAccountFragment extends BaseFragment {
    private TextView textView;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_user_account;
    }

    @Override
    protected void initData() {
        textView = (TextView) mView.findViewById(R.id.tv_temp);
    }

    @Override
    protected void initView() {
        CarSpeedRequest carSpeedRequest = new CarSpeedRequest(mContext);
        carSpeedRequest.setCarId(2);
        carSpeedRequest.connec(new BaseRequest.OnGetDataListener() {
            @Override
            public void onReturn(Object data) {
                textView.setText(data.toString());
            }
        });
    }
}
