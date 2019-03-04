package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.config.AppConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


//  公交站台请求类 继承基本请求类
public class BusStationRequest extends BaseRequest{

    private int stationId;

    //  构造函数
    public BusStationRequest(Context context) {
        super(context);
    }

    //  读取器
    public int getStationId() {
        return stationId;
    }

    //  写入器
    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到指定站台信息
    @Override
    protected String getAddr() {
        return AppConfig.GET_BUS_STATION_INFO;
    }

    //  重写抽象方法 得到请求参数
    @Override
    protected String getParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConfig.KEY_BUS_STATION_ID, stationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    //  重写抽象方法 分析响应
    @Override
    protected Object anaylizeResponse(String responseString) {
        List<Integer> distances = new ArrayList<Integer>();
        try {
            char[] first = responseString.substring(0, 1).toCharArray();
            if (first[0] == '[') {
                JSONArray jsonArray = new JSONArray(responseString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    distances.add(jsonArray.getJSONObject(i).getInt(AppConfig.KEY_DISTANCE));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return distances;
    }
}
