package mad.com.its02.request;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import mad.com.its02.config.AppConfig;

//  道路状态请求类 继承基本请求类
public class RoadStatusRequest extends BaseRequest {
    private int roadId;

    public RoadStatusRequest(Context context) {
        super(context);
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到指定小车速度
    @Override
    protected String getAddr() {
        return AppConfig.GET_ROAD_STATION_INFO;
    }

    @Override
    protected String getParams() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(AppConfig.KEY_ROAD_ID, roadId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    protected Object anaylizeResponse(String responseString) {
        int status = 1;
        try {
            JSONObject jsonObject = new JSONObject(responseString);
            status = jsonObject.getInt(AppConfig.KEY_STATUS);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return status;
    }
}
