package mad.com.its02.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

import mad.com.its02.R;
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
				mSetIPPreferences.getString("ip", "0.0.0.0"));

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

	
}
