package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.config.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

//  设置停车场费率请求类 继承基本请求类
public class SetParkRateRequest extends BaseRequest{
    private int money;

    public SetParkRateRequest(Context context) {
        super(context);
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求设置停车场费率
    @Override
    protected String getAddr() {
        return AppConfig.SET_PARK_RATE;
    }

    @Override
    protected String getParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConfig.KEY_RATE_TYPE, "Count");
            jsonObject.put(AppConfig.KEY_MONEY, money);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    protected Object anaylizeResponse(String responseString) {
        String result = "";
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            result = jsonObject.getString("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
