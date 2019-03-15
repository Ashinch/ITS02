package mad.com.its02.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mad.com.its02.R;
import mad.com.its02.request.BaseRequest;
import mad.com.its02.request.CarActionRequest;

public class RoadRestrictFragment extends BaseFragment implements View.OnClickListener{
    private TextView tvToday;
    private TextView tvDes;
    private Switch swRestrict;
    private Switch swCar1;
    private Switch swCar2;
    private Switch swCar3;
    private Switch swCar4;
    private LinearLayout layoutRestrictCar;
    private boolean onSwitch;
    private int isRes = 0;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_road_restrict;
    }

    @Override
    protected void initData() {
        tvToday = (TextView) mView.findViewById(R.id.tv_today);
        tvDes = (TextView) mView.findViewById(R.id.tv_des);
        swRestrict = (Switch) mView.findViewById(R.id.switch_restrict);
        swCar1 = (Switch) mView.findViewById(R.id.switch_car_1);
        swCar2 = (Switch) mView.findViewById(R.id.switch_car_2);
        swCar3 = (Switch) mView.findViewById(R.id.switch_car_3);
        swCar4 = (Switch) mView.findViewById(R.id.switch_car_4);
        layoutRestrictCar = (LinearLayout) mView.findViewById(R.id.layout_restrict_car);
    }

    @Override
    protected void initView() {
        swRestrict.setOnClickListener(this);
        swCar1.setOnClickListener(this);
        swCar2.setOnClickListener(this);
        swCar3.setOnClickListener(this);
        swCar4.setOnClickListener(this);

        setCarAction(1,true,false);
        setCarAction(2,true,false);
        setCarAction(3,true,false);
        setCarAction(4,true,false);

        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        tvToday.setText(df.format(new Date()));

        beginRestrict();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_restrict:
                if (swRestrict.isChecked()) {
                    swRestrict.setText("开");
                    layoutRestrictCar.setVisibility(View.VISIBLE);
                    Toast.makeText(mContext,"部分车辆受到限制！",Toast.LENGTH_SHORT).show();
                    beginRestrict();
                } else {
                    swRestrict.setText("关");
                    layoutRestrictCar.setVisibility(View.GONE);
                    setCarAction(1,true,false);
                    setCarAction(2,true,false);
                    setCarAction(3,true,false);
                    setCarAction(4,true,false);
                    Toast.makeText(mContext,"所有车辆限制解除！",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.switch_car_1:
                if (swCar1.isChecked()) {
                    swCar1.setText("开");
                    setCarAction(1,true,true);
                    swCar1.setChecked(true);
                } else {
                    swCar1.setText("开");
                    setCarAction(1,false,true);
                    swCar1.setChecked(false);
                }
                break;
            case R.id.switch_car_2:
                if (swCar2.isChecked()) {
                    swCar2.setText("开");
                    setCarAction(2,true,true);
                    swCar2.setChecked(true);
                } else {
                    swCar2.setText("关");
                    setCarAction(2,false,true);
                    swCar2.setChecked(false);
                }
                break;
            case R.id.switch_car_3:
                if (swCar3.isChecked()) {
                    swCar3.setText("开");
                    setCarAction(3,true,true);
                    swCar3.setChecked(true);
                } else {
                    swCar3.setText("关");
                    setCarAction(3,false,true);
                    swCar3.setChecked(false);
                }
                break;
            case R.id.switch_car_4:
                if (swCar4.isChecked()) {
                    swCar4.setText("开");
                    setCarAction(4,true,true);
                    swCar4.setChecked(true);
                } else {
                    swCar4.setText("关");
                    setCarAction(4,false,true);
                    swCar4.setChecked(false);
                }
                break;
        }
    }

    private void setCarAction(final int carId, final boolean isStart, final boolean toast) {
        CarActionRequest carActionRequest = new CarActionRequest(mContext);
        carActionRequest.setCarId(carId);
        carActionRequest.setAction(isStart ? "Move" : "Stop");
        carActionRequest.connec(new BaseRequest.OnGetDataListener() {
            @Override
            public void onReturn(Object data) {
                if (toast) {
                    Toast.makeText(mContext,Integer.toString(carId) + "号小车，"
                            + (isStart ? "启动" : "停止") + "成功！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void beginRestrict() {
        Calendar calendar = new GregorianCalendar();
        isRes = calendar.get(Calendar.DATE) % 2 == 0 ? 0 : 1;
        if (isRes == 0) {
            tvDes.setText("今日双号出行车辆2、4");
            setCarAction(1,false,false);
            setCarAction(2,true,false);
            setCarAction(3,false,false);
            setCarAction(4,true,false);
            swCar1.setChecked(false);
            swCar2.setChecked(true);
            swCar3.setChecked(false);
            swCar4.setChecked(true);
            swCar1.setText("关");
            swCar2.setText("开");
            swCar3.setText("关");
            swCar4.setText("开");

        } else {
            tvDes.setText("今日单号出行车辆1、3");
            setCarAction(1,true,false);
            setCarAction(2,false,false);
            setCarAction(3,true,false);
            setCarAction(4,false,false);
            swCar1.setChecked(true);
            swCar2.setChecked(false);
            swCar3.setChecked(true);
            swCar4.setChecked(false);
            swCar1.setText("开");
            swCar2.setText("关");
            swCar3.setText("开");
            swCar4.setText("关");
        }


    }
}