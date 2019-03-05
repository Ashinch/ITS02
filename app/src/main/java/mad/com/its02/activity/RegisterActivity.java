package mad.com.its02.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import mad.com.its02.R;
import mad.com.its02.bean.User;
import mad.com.its02.dao.UserDao;
import mad.com.its02.utils.MyToast;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    private EditText etUsername;
    private EditText etPassword;
    private EditText etSex;
    private EditText etPhoneNum;
    private EditText etIdNum;
    private Button btnRegister;
    private TextView tvGotoLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        etSex = (EditText) findViewById(R.id.et_sex);
        etPhoneNum = (EditText) findViewById(R.id.et_phone_num);
        etIdNum = (EditText) findViewById(R.id.et_id_num);
        btnRegister = (Button) findViewById(R.id.btn_register);
        tvGotoLogin = (TextView) findViewById(R.id.tv_goto_login);
    }

    @Override
    protected void initView() {
        btnRegister.setOnClickListener(this);
        tvGotoLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                registerCheck();
                break;

            case R.id.tv_goto_login:
                finish();
                break;
        }
    }

    private void registerCheck() {
        if (etUsername.getText() == null || etUsername.getText().equals("")) {
            Toast.makeText(this, "请输入用户名！", Toast.LENGTH_LONG).show();
        } else if (etPassword.getText() == null || etPassword.getText().equals("")) {
            Toast.makeText(this, "请输入密码！", Toast.LENGTH_LONG).show();
        } else if (etSex.getText() == null || etSex.getText().equals("")) {
            Toast.makeText(this, "请输入性别！", Toast.LENGTH_LONG).show();
        } else if (etSex.getText().equals("男") || etSex.getText().equals("女")) {
            Toast.makeText(this, "请输入正确性别！（男、女）", Toast.LENGTH_LONG).show();
        } else if (etIdNum.getText() == null || etIdNum.getText().equals("")) {
            Toast.makeText(this, "请输入身份证号！", Toast.LENGTH_LONG).show();
        } else if (etPhoneNum.getText() == null || etPhoneNum.getText().equals("")) {
            Toast.makeText(this, "请输入手机号！", Toast.LENGTH_LONG).show();
        } else {
            register();
        }


    }

    private void register() {
        User user = new User();
        user.setUsername(etUsername.getText().toString().trim());
        user.setPassword(etPassword.getText().toString().trim());
        user.setIdNum(etIdNum.getText().toString().trim());
        user.setPhoneNum(etPhoneNum.getText().toString().trim());
        user.setSex(etSex.getText().equals("男") ? 0 : 1);
        user.setPermission(0);

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
//        ParsePosition pos = new ParsePosition(8);
//        Date currentTime_2 = formatter.parse(dateString, pos);

        user.setJoinDate(dateString);
        Log.i("datestring", dateString);
        UserDao userDao = new UserDao(RegisterActivity.this);
        userDao.insert(user);
        MyToast.getToast(this,"注册成功，继续登录！");
        finish();
    }
}
