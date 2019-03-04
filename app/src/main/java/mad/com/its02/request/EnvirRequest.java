package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.bean.Envir;
import mad.com.its02.config.AppConfig;

import org.json.JSONException;
import org.json.JSONObject;

//  环境请求类 继承基本请求类
public class EnvirRequest extends BaseRequest {

    public EnvirRequest(Context context) {
        super(context);
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到所有传感器值
    @Override
    protected String getAddr() {
        return AppConfig.GET_ALL_SENSOR;
    }

    @Override
    protected String getParams() {
        return "";
    }

    @Override
    protected Object anaylizeResponse(String responseString) {
        //编写代码，解析服务器返回的数据
        Envir envir = new Envir();
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            envir.setCo2(jsonObject.getInt(AppConfig.KEY_SENSOR_CO2));
            envir.setHumi(jsonObject.getInt(AppConfig.KEY_SENSOR_HUMI));
            envir.setLight(jsonObject.getInt(AppConfig.KEY_SENSOR_LIGHT));
            envir.setPm(jsonObject.getInt(AppConfig.KEY_SENSOR_PM));
            envir.setTemp(jsonObject.getInt(AppConfig.KEY_SENSOR_TEMP));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return envir;

    }
}
