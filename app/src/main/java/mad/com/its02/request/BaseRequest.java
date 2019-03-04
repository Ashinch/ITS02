package mad.com.its02.request;

import android.content.Context;
import android.util.Log;

import mad.com.its02.http.NetUtil;
import mad.com.its02.utils.MyToast;

//  基本请求类
public abstract class BaseRequest {

    //  接口
    public interface OnGetDataListener {
        void onReturn(Object data);
    }

    private NetUtil mNetUtil1;
    private String url;
    private Context context;

    //  构造函数
    public BaseRequest(Context context) {
        this.context = context;
        url = "http://"
                + context.getSharedPreferences("ipset", 0).getString("ip", "192.168.1.131") + ":"
                + 8080
                + "/transportservice/type/jason/action/";
        Log.i("IP", "loadSetting: " + context.getSharedPreferences("ipset", 0).getString("ip", "192.168.1.131"));
    }

    //  连接到服务器 取回结果
    public void connec(final OnGetDataListener listener) {
        mNetUtil1 = new NetUtil();
        mNetUtil1.asynPost(url + getAddr(), getParams(), new NetUtil.ResponseListener() {

            //  重写成功事件
            @Override
            public void success(String result) {
                if (listener != null) {
                    if (!result.isEmpty()) {
                        listener.onReturn(anaylizeResponse(result));
                    }
                }
            }

            //  重写错误事件
            @Override
            public void error(String msg) {
                MyToast.getToastLong(context, msg);
            }
        });
    }

    //  抽象方法 得到请求地址
    protected abstract String getAddr();

    //  抽象方法 得到请求参数
    protected abstract String getParams();

    //  抽象方法 分析响应
    protected abstract Object anaylizeResponse(String responseString);
}
