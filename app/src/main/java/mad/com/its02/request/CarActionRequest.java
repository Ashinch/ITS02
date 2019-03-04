package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.config.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

//  小车动作请求类 继承基本请求类
public class CarActionRequest extends BaseRequest {

    private int carId;
    private String action;

    //  构造函数
    public CarActionRequest(Context context) {
        super(context);
    }

    //  读写器
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到小车动作
    @Override
    protected String getAddr() {
        return AppConfig.SET_CAR_ACTION;
    }

    //  重写抽象方法 得到请求参数
    @Override
    protected String getParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConfig.KEY_CAR_ACTION, action);
            jsonObject.put(AppConfig.KEY_CAR_ID, carId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    //  重写抽象方法 分析响应
    @Override
    protected Object anaylizeResponse(String responseString) {
        String result = "";
        try {
            JSONObject jsonObject=new JSONObject(responseString);
            result=jsonObject.getString("result");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
}
