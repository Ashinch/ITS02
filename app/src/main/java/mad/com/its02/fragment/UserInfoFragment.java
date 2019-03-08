package mad.com.its02.fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import mad.com.its02.R;
import mad.com.its02.utils.Session;

public class UserInfoFragment extends BaseFragment implements View.OnClickListener{
    private ImageButton ibFace;
    private TextView tvName;
    private TextView tvSex;
    private TextView tvIdNum;
    private TextView tvPhoneNum;
    private TextView tvJoinDate;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_user_info;
    }

    @Override
    protected void initData() {
        ibFace = (ImageButton) mView.findViewById(R.id.ib_face);
        tvName = (TextView) mView.findViewById(R.id.tv_name);
        tvSex = (TextView) mView.findViewById(R.id.tv_sex);
        tvIdNum = (TextView) mView.findViewById(R.id.tv_id);
        tvPhoneNum = (TextView) mView.findViewById(R.id.tv_phone);
        tvJoinDate = (TextView) mView.findViewById(R.id.tv_join_date);
    }

    @Override
    protected void initView() {
        tvName.setText(tvName.getText() + Session.user.getUsername());
        tvIdNum.setText(tvIdNum.getText() + Session.user.getIdNum());
        tvPhoneNum.setText(tvPhoneNum.getText() + Session.user.getPhoneNum());
        tvJoinDate.setText(tvJoinDate.getText() + Session.user.getJoinDate());

        if (Session.user.getSex() == 0) {
            tvSex.setText(tvSex.getText() + "男");
        }else {
            tvSex.setText(tvSex.getText() + "女");
        }
    }

    @Override
    public void onClick(View v) {

    }



}
