package mad.com.its02.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import mad.com.its02.R;
import mad.com.its02.utils.SpinnerFactory;

/**
 * Created by Administrator on 2019/3/12.
 */

public class ShuttleBusQRCodeFragment extends BaseFragment implements View.OnClickListener{
    private Button btnGeneratorQRCode;
    private Spinner spnCar;
    private EditText etMoney;
    private EditText etTime;
    private int carId = 1;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shuttle_qrcode;
    }

    @Override
    protected void initData() {
        btnGeneratorQRCode = (Button) mView.findViewById(R.id.btn_generator_qrcode);
        spnCar = (Spinner) mView.findViewById(R.id.spinner_car_id);
        SpinnerFactory.getSpinner(mContext, spnCar, new String[]{"1", "2", "3", "4"}
                , new SpinnerFactory.OnItemSelected() {
            @Override
            public void onSelected(int position) {
                carId = ++position;
            }
        });
        etMoney = (EditText) mView.findViewById(R.id.et_money);
        etTime = (EditText) mView.findViewById(R.id.et_time);
        btnGeneratorQRCode.setOnClickListener(this);
    }

    @Override
    protected void initView() {

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_generator_qrcode:

                Bundle bundle = new Bundle();
                bundle.putInt("carid",carId);

                bundle.putInt("money",Integer.valueOf(etMoney.getText().toString().trim()));
                bundle.putInt("time",Integer.valueOf(etTime.getText().toString().trim()));

                gotoFragment(R.id.shuttle_bus_content,new ShuttleBusPayFragment(),bundle);
                break;
        }
    }
}
