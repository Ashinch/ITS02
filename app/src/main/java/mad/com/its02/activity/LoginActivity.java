package mad.com.its02.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mad.com.its02.R;
import mad.com.its02.bean.User;
import mad.com.its02.dao.UserDao;
import mad.com.its02.utils.Session;
import mad.com.its02.utils.Util;

public class LoginActivity extends Activity implements View.OnClickListener {

	private String URL_POST;
	private String urlHttp;
	private String urlPort = "80";

	private EditText user_name;
	private EditText user_pwd;
	private ImageButton btn_login;
	private ImageButton btn_setting;
    private Button btn_register;
	private TextView tvAdminEnter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_login);

		initView();
		initData();
	}

	/**
	 *
	 */
	public void initView(){
		user_name = (EditText) findViewById(R.id.user_name);
		user_pwd = (EditText) findViewById(R.id.user_pwd);
		btn_login = (ImageButton) findViewById(R.id.btn_login);
		btn_setting = (ImageButton) findViewById(R.id.btn_setting);
        btn_register = (Button) findViewById(R.id.btn_register);
		tvAdminEnter = (TextView) findViewById(R.id.tv_admin_enter);
	}

	/**
	 *
	 */
	public void initData(){
		btn_login.setOnClickListener(this);
		btn_setting.setOnClickListener(this);
        btn_register.setOnClickListener(this);
		tvAdminEnter.setOnClickListener(this);
		URL_POST = Util.loadSetting( LoginActivity.this );
//		URL_POST =loadSetting();


	}

	/**
	 * 描述：保存数据到SharedPreferences对象中
	 * @param ipUrl
	 * @param ipPort
	 */

	public void saveSetting(String ipUrl, String ipPort) {
		SharedPreferences spSettingSave = getSharedPreferences("setting", MODE_PRIVATE);// 将需要记录的数据保存在setting.xml文件中
		SharedPreferences.Editor editor = spSettingSave.edit();
		editor.putString("ipUrl", ipUrl);
		editor.putString("ipPort", ipPort);
		editor.commit();
	}

	/**
	 * 描述：获取数据到SharedPreferences对象中
	 * @return
	 */
	public String loadSetting() {
		SharedPreferences loadSettingLoad = getSharedPreferences("setting", MODE_PRIVATE);
		//这里是将setting.xml 中的数据读出来
		urlHttp = loadSettingLoad.getString("ipUrl", "");
		urlPort = loadSettingLoad.getString("ipPort", "");
		String urlSetting = "http://" + urlHttp+ ":" + urlPort + "/";
		return urlSetting;
	}

	/**
	 * 描述：点击事件监听
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_login:
				String userName = user_name.getText().toString().trim();
				String userPwd = user_pwd.getText().toString().trim();

				 if (userName == null || userName.equals("")) {
					Toast.makeText(this, "请输入用户名！", Toast.LENGTH_LONG).show();
				} else if (userPwd == null || userPwd.equals("")) {
					 Toast.makeText(this, "请输入密码！", Toast.LENGTH_LONG).show();
				 } else if (URL_POST == null || URL_POST.equals("")) {
					Toast.makeText(this, "请设置服务器地址和端口！", Toast.LENGTH_LONG).show();
				} else {

                     if (loginCheck(userName,userPwd)) {
                         Intent intent = new Intent();
                         intent.setClass(LoginActivity.this, MainActivity.class);
                         startActivity(intent);
                         this.finish();
                     } else {
                         Toast.makeText(this, "用户名或密码错误！", Toast.LENGTH_LONG).show();
                     }
				}
				break;

            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;

			case R.id.btn_setting:

				System.out.println("URL_POST:" + URL_POST);

				final Dialog urlSettingDialog = new Dialog(LoginActivity.this);
				urlSettingDialog.show();
				urlSettingDialog.setTitle("网络设置");
				urlSettingDialog.getWindow().setContentView(R.layout.login_setting);
				final EditText edit_urlHttp = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_url);
				final EditText edit_urlPort = (EditText) urlSettingDialog.getWindow().findViewById(R.id.edit_setting_port);

				edit_urlHttp.setText( Util.urlHttp );
				edit_urlPort.setText( Util.urlPort);
				Button save = (Button) urlSettingDialog.getWindow().findViewById(R.id.save);
				Button cancel = (Button) urlSettingDialog.getWindow().findViewById(R.id.cancel);
				save.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						urlHttp = edit_urlHttp.getText().toString();
						urlPort = edit_urlPort.getText().toString();

						if ( urlHttp == null || urlHttp.equals("")   ) {
							Toast.makeText(LoginActivity.this, "地址格式不正确！", Toast.LENGTH_LONG).show();
						} else {
							Util.saveSetting(urlHttp,urlPort,LoginActivity.this);
//							saveSetting(urlHttp,urlPort);
//							URL_POST = loadSetting();
							URL_POST = Util.loadSetting(LoginActivity.this);

							urlSettingDialog.dismiss();
						}
					}
				});
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						urlSettingDialog.dismiss();
					}
				});
				urlSettingDialog.show();
				break;

            case R.id.tv_admin_enter:
                user_name.setText("周泽齐");
                user_pwd.setText("123");

		}
	}

    private boolean loginCheck(String username,String password) {
        try {
            UserDao userDao = new UserDao(LoginActivity.this);
            List<User> users =  userDao.queryByName(username);
            Session.user = users.get(0);
            return (users.get(0).getPassword()).equals(password);
        } catch(IndexOutOfBoundsException e) {
            return false;
        }
	}


}

