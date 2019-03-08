package mad.com.its02.fragment;

import android.widget.TextView;

import mad.com.its02.R;
import mad.com.its02.utils.Session;

public class ParkICFragment extends BaseFragment {
private TextView Name;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_park_ic;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
      Name=(TextView)mView.findViewById(R.id.ic_username);

        Name.setText(Session.user.getUsername());
    }

}


