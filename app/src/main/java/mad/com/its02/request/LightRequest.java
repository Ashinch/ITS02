package mad.com.its02.request;

import android.content.Context;

import mad.com.its02.config.AppConfig;

//  红绿灯信息请求类 继承基本请求类
public class LightRequest extends BaseRequest {
    private int roadId;

    public LightRequest(Context context) {
        super(context);
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }

    //  重写抽象方法 得到请求地址
    //  AppConfig.GET_ALL_SENSOR 请求得到红绿灯信息
    @Override
    protected String getAddr() {
        return AppConfig.GET_LIGHT_MSG;
    }

    @Override
    protected String getParams() {


        //编写代码，封装数据发送到服务器
        return null;
    }

    @Override
    protected Object anaylizeResponse(String responseString) {
        //编写代码，解析服务器返回的数据
        return null;
    }

}
