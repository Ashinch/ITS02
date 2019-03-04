package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.config.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//  查询空闲车位请求类 继承基本请求类
public class GetFreeLocationRequest extends BaseRequest{
    public GetFreeLocationRequest(Context context) {
        super(context);
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到空闲车位信息
    @Override
    protected String getAddr() {
        return AppConfig.GET_FREE_LOCATION;
    }

    @Override
    protected String getParams() {
        return "";
    }

    @Override
    protected Object anaylizeResponse(String responseString) {
        List<Integer> freeLocations = new ArrayList<Integer>();
        try {
            JSONArray jsonArray = new JSONArray(responseString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                freeLocations.add(jsonObject.getInt(AppConfig.KEY_PARK_FREE_ID));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return freeLocations;
    }
}
