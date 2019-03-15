package mad.com.its02.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import mad.com.its02.bean.Envir;
import mad.com.its02.utils.Session;

//  基本活动类
public abstract class BaseActivity extends FragmentActivity  {
    protected TextView mTitleTV;
    protected String mBasURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        getIP();
        getSP();
        initData();
        initView();

    }

    //  SharedPreferences 获取保存的 ip
    private void getIP() {
        SharedPreferences sh = getSharedPreferences("ipset", 0);
        mBasURL = "http://" + sh.getString("ip", "192.168.1.133") + ":" + 8080
                + "/transportservice/type/jason/action/";
    }

    private void getSP() {
        SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
        //把保存在硬盘上的阈值存入Session
        Envir envir = new Envir();

        envir.setTemp(sp.getInt("wendu",0));
        envir.setHumi(sp.getInt("shidu",0));
        envir.setLight(sp.getInt("guangzhao",0));
        envir.setCo2(sp.getInt("co2",0));
        envir.setPm(sp.getInt("pm2",0));

        Session.envir = envir;

        Log.i("SP", "temp: " + sp.getInt("wendu",0));
        Log.i("SP", "humi: " + sp.getInt("shidu",0));
        Log.i("SP", "light: " + sp.getInt("guangzhao",0));
        Log.i("SP", "co2: " + sp.getInt("co2",0));
        Log.i("SP", "pm: " + sp.getInt("pm2",0));
    }

    //  抽象方法 初始化数据
    protected abstract void initData();

    //  抽象方法 初始化视图
    protected abstract void initView();

    //  抽象方法 获取布局 id
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
