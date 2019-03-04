package mad.com.its02.fragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import mad.com.its02.R;

public class UserFragment extends BaseFragment implements View.OnClickListener{
    private Button mBtnUserInfo;
    private Button mBtnAccount;
    private Button mBtnThreshold;
    private Button mBtnHistory;
    private Button mBtnUserMSG;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBtnUserInfo = (Button) mView.findViewById(R.id.btn_user_info);
        mBtnAccount = (Button) mView.findViewById(R.id.btn_user_account);
        mBtnThreshold = (Button) mView.findViewById(R.id.btn_user_threshold);
        mBtnHistory = (Button) mView.findViewById(R.id.btn_user_history);
        mBtnUserMSG = (Button) mView.findViewById(R.id.btn_user_msg);

        mBtnUserInfo.setOnClickListener(this);
        mBtnAccount.setOnClickListener(this);
        mBtnThreshold.setOnClickListener(this);
        mBtnHistory.setOnClickListener(this);
        mBtnUserMSG.setOnClickListener(this);

        gotoFragment(R.id.user_content,new UserInfoFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //  用户信息
            case R.id.btn_user_info:
                gotoFragment(R.id.user_content,new UserInfoFragment());
                break;
            //  账户管理
            case R.id.btn_user_account:
                gotoFragment(R.id.user_content,new UserAccountFragment());
                break;
            //  阀值提醒
            case R.id.btn_user_threshold:
                gotoFragment(R.id.user_content,new UserThresholdFragment());
                break;
            //  充值历史
            case R.id.btn_user_history:
                gotoFragment(R.id.user_content,new UserHistoryFragment());
                break;
            //  我的消息
            case R.id.btn_user_msg:
                gotoFragment(R.id.user_content,new UserMSGFragment());
                break;
        }
    }
}
