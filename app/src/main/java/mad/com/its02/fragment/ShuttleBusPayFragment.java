package mad.com.its02.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mad.com.its02.R;
import mad.com.its02.activity.ShowQRCodeActivity;
import mad.com.its02.utils.Session;
import mad.com.its02.utils.ZxingUtils;


public class ShuttleBusPayFragment extends BaseFragment implements View.OnClickListener{
    private int carId;
    private int money;
    private int time;
    private TextView tvQRCodeInfo;
    private ImageView ivQRCode;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shuttle_pay;
    }

    @Override
    protected void initData() {
        carId = getArguments().getInt("carid");
        money = getArguments().getInt("money");
        time = getArguments().getInt("time");
        
        tvQRCodeInfo = (TextView) mView.findViewById(R.id.tv_qrcode_info);
        ivQRCode = (ImageView) mView.findViewById(R.id.iv_qrcode);

        Session.bmpQRCode = ZxingUtils.createBitmap("车辆编号=" + carId + "，付款金额=" + money + "元",300,300);
    }

    @Override
    protected void initView() {
        if (Session.bmpQRCode != null) {
            ivQRCode.setImageBitmap(Session.bmpQRCode);
        } else {
            ivQRCode.setBackgroundColor(getResources().getColor(R.color.black));
        }

        ivQRCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                tvQRCodeInfo.setText("车辆编号=" + carId + "，付款金额=" + money + "元");

                return false;
            }
        });

        ivQRCode.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.iv_qrcode:

                startActivity(new Intent(mContext, ShowQRCodeActivity.class));
        }
    }
}
