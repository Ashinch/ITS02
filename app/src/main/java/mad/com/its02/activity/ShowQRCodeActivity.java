package mad.com.its02.activity;

import android.view.View;
import android.widget.ImageView;

import mad.com.its02.R;
import mad.com.its02.utils.Session;

public class ShowQRCodeActivity extends BaseActivity implements View.OnClickListener{
    private ImageView ivQRCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_show_qrcode;
    }

    @Override
    protected void initData() {
        ivQRCode = (ImageView) findViewById(R.id.iv_qrcode);
    }

    @Override
    protected void initView() {
        ivQRCode.setOnClickListener(this);
        ivQRCode.setImageBitmap(Session.bmpQRCode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_qrcode:
                finish();
                break;
        }
    }

}
