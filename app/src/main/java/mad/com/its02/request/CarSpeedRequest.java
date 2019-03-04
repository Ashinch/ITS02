package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.config.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

//  小车速度请求类 继承基本请求类
public class CarSpeedRequest extends BaseRequest {
    private int carId;

    public CarSpeedRequest(Context context) {
        super(context);
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到指定小车速度
    @Override
    protected String getAddr() {
        return AppConfig.GET_CAR_SPEED;
    }

    @Override
    protected String getParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConfig.KEY_CAR_ID, carId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    protected Object anaylizeResponse(String responseString) {
        int speed = 0;
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            speed = jsonObject.getInt(AppConfig.KEY_CAR_SPEED);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return speed;
    }
}
