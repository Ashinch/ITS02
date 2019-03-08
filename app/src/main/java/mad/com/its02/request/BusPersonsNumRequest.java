package mad.com.its02.request;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import mad.com.its02.config.AppConfig;


//  公交载客人数请求类 继承基本请求类
public class BusPersonsNumRequest extends BaseRequest{

    private int busId;

    //  构造函数
    public BusPersonsNumRequest(Context context) {
        super(context);
    }

    //  读取器
    public int getBusId() {
        return busId;
    }

    //  写入器
    public void setBusId(int busId) {
        this.busId = busId;
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到指定站台信息
    @Override
    protected String getAddr() {
        return AppConfig.GET_BUS_PERSONS_NUM;
    }

    //  重写抽象方法 得到请求参数
    @Override
    protected String getParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConfig.KEY_BUS_ID, busId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    //  重写抽象方法 分析响应
    @Override
    protected Object anaylizeResponse(String responseString) {
        int persons = 0;
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            persons = jsonObject.getInt(AppConfig.KEY_PERSONS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return persons;
    }
}
