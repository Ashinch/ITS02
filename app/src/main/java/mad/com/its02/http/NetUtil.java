package mad.com.its02.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

//  网络工具类
public class NetUtil {

    /**
     * TAG
     * READ_TIME 读取超时时间
     * CONNECT_TIME 连接超时时间
     * isDebug 是否调试
     * handler
     */

    private static final String TAG = "Test";
    private static final int READ_TIME = 1000 * 3;
    private static final int CONNECT_TIME = 1000 * 3;
    private boolean isDebug = false;
    private Handler handler = new Handler(Looper.getMainLooper());

    //  接口 响应监听器
    public interface ResponseListener {

        //  定义成功事件
        void success(String result);

        //  定义错误事件
        void error(String msg);
    }


    /**
     * 公开
     * 判断网络是否连接
     *
     * @param context 上下文对象
     *
     * @return 网络是否连接
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean isNetworkOK = false;
        try {
            ConnectivityManager conn = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (null == conn || null == conn.getActiveNetworkInfo()) {
                isNetworkOK = false;
            } else {
                isNetworkOK = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isNetworkOK;
    }

    /**
     * asyns 私有异步发送方法
     *
     * @param urlString url信息
     * @param params 参数信息
     * @param listener 监听器
     *
     * @return 网络是否连接
     */
    private void asynsPost(final String urlString, final String params,
                           final ResponseListener listener) {
        //  启动线程
        new Thread() {
            public void run() {
                //  监听器不为空
                if (listener != null) {

                    String result = "";
                    try {
                        //  调用本类方法 postData() 尝试进行 post 请求服务器
                        result = postData(urlString, params);

                        //  异常处理
                    } catch (MalformedURLException e) {
                        listener.error(e + "");
                        e.printStackTrace();
                    } catch (IOException e) {
                        listener.error(e + "");
                        e.printStackTrace();
                    } catch (JSONException e) {
                        listener.error(e + "");
                        e.printStackTrace();
                    }
                    listener.success(result);
                }
            };
        }.start();
    }

    /**
     * 公开
     * public 异步发送方法
     *
     * @param urlString url信息
     * @param params 参数信息
     * @param listener 监听器
     *
     * @return 无返回值
     */
    public void asynPost(final String urlString, final String params,
                         final ResponseListener listener) {

        //  调用本类方法 asynsPost() 进行异步 post 请求
        asynsPost(urlString, params, new ResponseListener() {

            //  重写成功事件
            @Override
            public void success(final String result) {
                //  启动 handler
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.success(result);
                    }
                });
            }

            //  重写错误事件
            @Override
            public void error(final String msg) {
                //  启动 handler
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.error(msg);
                    }
                });
            }
        });
    }

    /**
     * 公开
     * 开始进行 post 请求服务器
     *
     * @param urlString url信息
     * @param params 参数信息
     *
     * @return 返回服务器响应的 json 数据
     */
    public String postData(String urlString, String params) throws MalformedURLException,
            IOException, JSONException {
        String result = "";
        //  输出调试信息
        if (isDebug) {
            Log.d(TAG + " url:", urlString);
            Log.d(TAG + " body:", params);
        }

        //  调用本类方法 initURL() 初始化 URL
        HttpURLConnection mConnection = initURL(urlString);
        //  调用本类方法 setURLParams() 设置 URL 请求头参数
        setURLParams(mConnection);
        //  调用本类方法 sendData() 向服务器写入数据
        sendData(params, mConnection);
        //  调用本类方法 readData() 向服务器取回数据
        result = readData(mConnection);

        //  输出调试信息
        if (isDebug) {
            Log.d(TAG + "response_code:", mConnection.getResponseCode() + "");
            Log.d(TAG + "response:", result);
        }

        result = new JSONObject(result).getString("serverinfo");
        return result;
    }

    /**
     * 读取响应数据
     *
     * @param mConnection 已初始化过的 HttpURLConnection 类
     *
     * @return 返回服务器响应数据
     */
    private String readData(HttpURLConnection mConnection) throws IOException {
        InputStream in = null;//字节输入流
        InputStreamReader is = null;//字符输入流
        BufferedReader mReader = null;//整行读取
        String result = "";

        //  向服务器读取数据
        try {
            in = mConnection.getInputStream();
            is = new InputStreamReader(in);
            mReader = new BufferedReader(is);
            result = "";
            String line;
            while ((line = mReader.readLine()) != null) {
                if (result.equals("")) {
                    result += line;
                } else {
                    result += "\n" + line;
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (is != null) {
                is.close();
            }
            if (mReader != null) {
                mReader.close();
            }
        }
        return result;
    }

    /**
     * 发送请求数据
     *
     * @param mConnection 已初始化过的 HttpURLConnection 类
     *
     * @return 无返回值
     */
    private void sendData(final String params, HttpURLConnection mConnection) throws IOException {
        OutputStream os = null;
        OutputStreamWriter osw = null;

        //  向服务器写入数据
        try {
            os = mConnection.getOutputStream();
            osw = new OutputStreamWriter(os, "utf-8");
            osw.write(params);
            osw.flush();
        } finally {
            if (os != null) {
                os.close();
            }
            if (osw != null) {
                osw.close();
            }
        }
    }

    /**
     * 设置 URL 请求头参数
     *
     * @param mConnection 已初始化过的 HttpURLConnection 类
     *
     * @return 无返回值
     */
    private void setURLParams(HttpURLConnection mConnection) throws ProtocolException {
        mConnection.setRequestProperty("accept", "*/*");//同意所以文件类型
        mConnection.setRequestProperty("connection", "Keep-Alive");//连接方式
        mConnection.setRequestMethod("POST");//以post形式发送
        mConnection.setRequestProperty("Content-Type", "text/html; charset=UTF-8");//发送格式
        mConnection.setConnectTimeout(CONNECT_TIME);//连接超时
        mConnection.setReadTimeout(READ_TIME);//读取超时
        mConnection.setDoOutput(true);
        mConnection.setDoInput(true);
    }

    /**
     * 初始化请求 URL
     *
     * @param urlString url信息
     *
     * @return 返回 HttpURLConnection 类
     */
    private HttpURLConnection initURL(String urlString) throws IOException {
        URL mUrl;
        HttpURLConnection mConnection;

        mUrl = new URL(urlString);
        mConnection = (HttpURLConnection) mUrl.openConnection();
        return mConnection;
    }
}
