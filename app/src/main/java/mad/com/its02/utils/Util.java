package mad.com.its02.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;

/**
 * 项目名称：ITS02
 * 类描述：
 * 创建人：zhaowei
 * 创建时间：2017/4/19 9:20
 * 修改人：Administrator
 * 修改时间：2017/4/19 9:20
 * 修改备注：
 */
public class Util {

    public static String urlHttp;
    public static String urlPort ;

    /**
     * 描述：保存数据到SharedPreferences对象中
     * @param ipUrl
     * @param ipPort
     */

    public static void saveSetting(String ipUrl, String ipPort,Context context) {
        SharedPreferences mSetIPPreferences = context.getSharedPreferences("ipset", 0);
        mSetIPPreferences.edit().putString("ip", ipUrl).apply();// 提交存储
        Log.i("IP", "saveSetting: " + ipUrl);
    }

    /**
     * 描述：获取数据到SharedPreferences对象中
     * @return
     */
    public static String loadSetting(Context context) {
        SharedPreferences mSetIPPreferences = context.getSharedPreferences("ipset", 0);
        String ip = mSetIPPreferences.getString("ip", "192.168.1.100");
        String urlSetting = "http://" + ip + ":" + "8080" + "/";
        Log.i("IP", "loadSetting: " + ip);
        return ip;
    }
}
