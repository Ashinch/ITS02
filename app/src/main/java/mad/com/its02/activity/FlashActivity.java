package mad.com.its02.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

import mad.com.its02.R;
import mad.com.its02.bean.User;
import mad.com.its02.dao.UserDao;
import mad.com.its02.utils.MyToast;

public class FlashActivity extends Activity {

	/**
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_flash);

		SharedPreferences mSetIPPreferences = getSharedPreferences("ipset", 0);
		MyToast.getToast(FlashActivity.this,
				mSetIPPreferences.getString("ip", "请设置IP！"));

        createTables();

		new CountDownTimer(1000,1000) {
			@Override
			public void onTick(long millisUntilFinished) {
			}
			
			@Override
			public void onFinish() {
				
				Intent intent = new Intent();
				intent.setClass(FlashActivity.this, LoginActivity.class);
				startActivity(intent);
	
				int VERSION= Integer.parseInt(android.os.Build.VERSION.SDK);
				if(VERSION >= 5){
					FlashActivity.this.overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
				}
				finish();
			}
		}.start();
		
	}

    private void createTables() {
        UserDao userDao = new UserDao(FlashActivity.this);

        User user1 = new User();
        user1.setUsername("周泽齐");
        user1.setPassword("123");
        user1.setSex(0);
        user1.setPermission(1);
        user1.setPhoneNum("13326432468");
        user1.setJoinDate("2019-3-4");
        user1.setIdNum("312454189111072857");
        userDao.insert(user1);

        User user2 = new User();
        user2.setUsername("李春华");
        user2.setPassword("123");
        user2.setSex(1);
        user2.setPermission(1);
        user2.setPhoneNum("13326432468");
        user2.setJoinDate("2019-3-5");
        user2.setIdNum("420101199203197412");
        userDao.insert(user2);
    }
}
