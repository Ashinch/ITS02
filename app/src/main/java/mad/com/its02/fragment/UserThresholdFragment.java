package mad.com.its02.fragment;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import mad.com.its02.R;
import mad.com.its02.utils.Session;

import static android.content.Context.MODE_PRIVATE;

public class UserThresholdFragment extends BaseFragment implements View.OnClickListener {
    private Switch mswitch;
    private EditText wdeditText;
    private EditText sdeditText;
    private EditText gzeditText;
    private EditText coeditText;
    private EditText pmeditText;
    private EditText dleditText;
    private Button bcbutton;
    private TextView kaiguan;
    private int wendu=0;
    private int shidu=0;
    private int guangzhao=0;
    private int co2=0;
    private int pm2=0;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_user_threshold;
    }

    @Override
    protected void initData() {
        mswitch = (Switch) mView.findViewById(R.id.fzswitch);
        kaiguan= (TextView) mView.findViewById(R.id.kaiguan);
        wdeditText = (EditText) mView.findViewById(R.id.wendu_et);
        sdeditText = (EditText) mView.findViewById(R.id.shidu_et);
        gzeditText = (EditText) mView.findViewById(R.id.guangzhao_et);
        coeditText = (EditText) mView.findViewById(R.id.co2_et);
        pmeditText = (EditText) mView.findViewById(R.id.pm_et);
        dleditText = (EditText) mView.findViewById(R.id.daolu_et);
        bcbutton = (Button) mView.findViewById(R.id.bcbt);
        bcbutton.setOnClickListener(this);
        mswitch.setOnClickListener(this);
        SharedPreferences sp=mContext.getSharedPreferences("data",MODE_PRIVATE);
        mswitch.setChecked(sp.getBoolean("kaiguan",true));//获取switch的开关状态
    }

    @Override
    protected void initView() {
        setfazhi();
        SharedPreferences sp=mContext.getSharedPreferences("data",MODE_PRIVATE);
        mswitch.setChecked(sp.getBoolean("kaiguan",true));//获取switch的开关状态
        if (mswitch.isChecked()) {
            wdeditText.setEnabled(false);
            sdeditText.setEnabled(false);
            gzeditText.setEnabled(false);
            coeditText.setEnabled(false);
            pmeditText.setEnabled(false);
            dleditText.setEnabled(false);
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bcbt:
                getfazhi();
                setfazhi();
                break;
            case R.id.fzswitch:
                if (mswitch.isChecked()){
                    kaiguan.setText("开");
                    wdeditText.setEnabled(false);
                    sdeditText.setEnabled(false);
                    gzeditText.setEnabled(false);
                    coeditText.setEnabled(false);
                    pmeditText.setEnabled(false);
                    dleditText.setEnabled(false);
                    SharedPreferences sp=mContext.getSharedPreferences("data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putBoolean("kaiguan",true);//保存switch的开关状态
                    editor.commit();

                }else
                {
                    kaiguan.setText("关");
                    wdeditText.setEnabled(true);
                    sdeditText.setEnabled(true);
                    gzeditText.setEnabled(true);
                    coeditText.setEnabled(true);
                    pmeditText.setEnabled(true);
                    dleditText.setEnabled(true);
                    SharedPreferences sp=mContext.getSharedPreferences("data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putBoolean("kaiguan",false);//保存switch的开关状态
                    editor.commit();

                }

                break;
        }
    }
//获取EditText上的阈值//存入SharedPreferences
    public void getfazhi()
    {
        String e1= wdeditText.getText().toString();
        wendu=Integer.valueOf(e1);
        String e2=  sdeditText.getText().toString();
        shidu=Integer.valueOf(e2);
        String e3=  gzeditText.getText().toString();
        guangzhao=Integer.valueOf(e3);
        String e4= coeditText.getText().toString();
        co2=Integer.valueOf(e4);
        String e5=  pmeditText.getText().toString();
        pm2=Integer.valueOf(e5);
        SharedPreferences sp=mContext.getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("wendu",wendu);
        editor.putInt("shidu",shidu);
        editor.putInt("guangzhao",guangzhao);
        editor.putInt("co2",co2);
        editor.putInt("pm2",pm2);
        editor.commit();

        Session.envir.setCo2(co2);
        Session.envir.setPm(pm2);
        Session.envir.setLight(guangzhao);
        Session.envir.setTemp(wendu);
        Session.envir.setHumi(shidu);
    }

    //修改EditText显示的阈值
    public void setfazhi()
    {
//        SharedPreferences sp=mContext.getSharedPreferences("data",MODE_PRIVATE);
//        int a1=sp.getInt("wendu",10);
//        int a2=sp.getInt("shidu",0);
//        int a3=sp.getInt("guangzhao",0);
//        int a4=sp.getInt("co2",0);
//        int a5=sp.getInt("pm2",0);

        wdeditText.setText(getString(R.string.wendutext, Session.envir.getTemp()+"" ));
        sdeditText.setText(getString(R.string.wendutext,Session.envir.getHumi()+"" ));
        gzeditText.setText(getString(R.string.wendutext,Session.envir.getLight()+"" ));
        coeditText.setText(getString(R.string.wendutext,Session.envir.getCo2()+"" ));
        pmeditText.setText(getString(R.string.wendutext,Session.envir.getPm()+"" ));

    }



}
