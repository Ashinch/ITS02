package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.config.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

//  查询余额请求类 继承基本请求类
public class GetBalanceRequest extends BaseRequest {

    private int carId;

    public GetBalanceRequest(Context context) {
        super(context);
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到指定小车余额
    @Override
    protected String getAddr() {
        return AppConfig.GET_CAR_BALANCE;
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
        int money = 0;
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            money = jsonObject.getInt(AppConfig.KEY_CAR_BALANCE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return money;
    }
}
