package mad.com.its02.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mad.com.its02.R;

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
        initData();
        initView();

    }

    //  SharedPreferences 获取保存的 ip
    private void getIP() {
        SharedPreferences sh = getSharedPreferences("ipset", 0);
        mBasURL = "http://" + sh.getString("ip", "192.168.1.133") + ":" + 8080
                + "/transportservice/type/jason/action/";
    }

    //  抽象方法 初始化数据
    protected abstract void initData();

    //  抽象方法 初始化视图
    protected abstract void initView();

    //  抽象方法 获取布局 id
    protected abstract int getLayoutId();


}
