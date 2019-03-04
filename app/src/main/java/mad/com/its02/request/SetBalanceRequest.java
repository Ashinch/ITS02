package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.config.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

//  设置小车账户余额请求类 继承基本请求类
public class SetBalanceRequest extends BaseRequest{

    private int carId;
    private int money;

    public SetBalanceRequest(Context context) {
        super(context);
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求设置小车余额
    @Override
    protected String getAddr() {
        return AppConfig.SET_CAR_BALANCE;
    }

    @Override
    protected String getParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConfig.KEY_CAR_ID, carId);
            jsonObject.put(AppConfig.KEY_MONEY, money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    protected Object anaylizeResponse(String responseString) {
        String resultString = "";
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            resultString = jsonObject.getString("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultString;
    }

}
