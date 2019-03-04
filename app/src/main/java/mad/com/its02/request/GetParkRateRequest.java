package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.config.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

//  查询当前停车费率请求类 继承基本请求类
public class GetParkRateRequest extends BaseRequest {
    public GetParkRateRequest(Context context) {
        super(context);
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到停车费率
    @Override
    protected String getAddr() {
        return AppConfig.GET_PARK_RATE;
    }

    @Override
    protected String getParams() {
        return "";
    }

    @Override
    protected Object anaylizeResponse(String responseString) {
        int money=0;
        try {
            JSONObject jsonObject=new JSONObject(responseString);
            money= jsonObject.getInt(AppConfig.KEY_MONEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return money;
    }
}
