package mad.com.its02.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.TextView;

import mad.com.its02.R;
import mad.com.its02.bean.Envir;
import mad.com.its02.request.BaseRequest;
import mad.com.its02.request.EnvirRequest;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Thread.sleep;

public class LiveIndexFragment extends BaseFragment {
    private TextView wdtext;
    private TextView sdtext;
    private TextView gztext;
    private TextView cotext;
    private TextView pmtext;
    private TextView suju1;
    private TextView suju2;
    private TextView suju3;
    private TextView suju4;
    private TextView suju5;
    private TextView tishi1;
    private TextView tishi2;
    private TextView tishi3;
    private TextView tishi4;
    private TextView tishi5;

    private RelativeLayout yanse1;
    private RelativeLayout yanse2;
    private RelativeLayout yanse3;
    private RelativeLayout yanse4;
    private RelativeLayout yanse5;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_live_index;
    }

    @Override
    protected void initData() {
        wdtext= (TextView) mView.findViewById(R.id.huanjing1);
        sdtext= (TextView) mView.findViewById(R.id.huanjing2);
        gztext= (TextView) mView.findViewById(R.id.huanjing3);
        cotext= (TextView) mView.findViewById(R.id.huanjing4);
        pmtext= (TextView) mView.findViewById(R.id.huanjing5);

        suju1= (TextView) mView.findViewById(R.id.suju1);
        suju2= (TextView) mView.findViewById(R.id.suju2);
        suju3= (TextView) mView.findViewById(R.id.suju3);
        suju4= (TextView) mView.findViewById(R.id.suju4);
        suju5= (TextView) mView.findViewById(R.id.suju5);

        tishi1= (TextView) mView.findViewById(R.id.tishi1);
        tishi2= (TextView) mView.findViewById(R.id.tishi2);
        tishi3= (TextView) mView.findViewById(R.id.tishi3);
        tishi4= (TextView) mView.findViewById(R.id.tishi4);
        tishi5= (TextView) mView.findViewById(R.id.tishi5);

        yanse1= (RelativeLayout) mView.findViewById(R.id.yanse1);
        yanse2= (RelativeLayout) mView.findViewById(R.id.yanse2);
        yanse3= (RelativeLayout) mView.findViewById(R.id.yanse3);
        yanse4= (RelativeLayout) mView.findViewById(R.id.yanse4);
        yanse5= (RelativeLayout) mView.findViewById(R.id.yanse5);
    }
    @Override
    protected void initView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    getEnvir();
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
    private void getEnvir(){
        EnvirRequest envirRequest = new EnvirRequest(mContext);

        envirRequest.connec(new BaseRequest.OnGetDataListener() {
            @Override
            public void onReturn(Object data) {
                Envir envir = (Envir) data;
                wdtext.setText(envir.getTemp()+"" );
                sdtext.setText( envir.getHumi()+"" );
                gztext.setText( envir.getLight()+"" );
                cotext.setText( envir.getCo2()+"" );
                pmtext.setText( envir.getPm()+"" );

                //判断是否有超阈值
                SharedPreferences sp=mContext.getSharedPreferences("data",MODE_PRIVATE);
                int a1=sp.getInt("wendu",0);
                int a2=sp.getInt("shidu",0);
                int a3=sp.getInt("guangzhao",0);
                int a4=sp.getInt("co2",0);
                int a5=sp.getInt("pm2",0);
                //判断温度超阈值
                if (envir.getTemp()>a1)
                {
                    yanse1.setBackgroundColor(Color.parseColor("#FF0000"));
                }
                else
                {
                    yanse1.setBackgroundColor(Color.parseColor("#00FF00"));
                }
                //判断湿度超阈值
                if (envir.getHumi()>a2)
                {
                    yanse2.setBackgroundColor(Color.parseColor("#FF0000"));
                }
                else
                {
                    yanse2.setBackgroundColor(Color.parseColor("#00FF00"));
                }
                //判断光照超阈值
                if (envir.getLight()>a3)
                {
                    yanse3.setBackgroundColor(Color.parseColor("#FF0000"));
                }
                else
                {
                    yanse3.setBackgroundColor(Color.parseColor("#00FF00"));
                }
                //判断CO2超阈值
                if (envir.getCo2()>a4)
                {
                    yanse4.setBackgroundColor(Color.parseColor("#FF0000"));
                }
                else
                {
                    yanse4.setBackgroundColor(Color.parseColor("#00FF00"));
                }
                //判断PM2.5超阈值
                if (envir.getTemp()>a5)
                {
                    yanse5.setBackgroundColor(Color.parseColor("#FF0000"));
                }
                else
                {
                    yanse5.setBackgroundColor(Color.parseColor("#00FF00"));
                }



                //根据光强判断紫外线指数
                if( envir.getLight()>0&& envir.getLight()<1000)
                {
                    suju1.setText("弱("+envir.getLight()+")" );
                    tishi1.setText("辐射较弱，涂擦SPF12~15、PA+护肤品");
                }
                else if (envir.getLight()>=1000&& envir.getLight()<=3000)
                {
                    suju1.setText("中等("+envir.getLight()+")" );
                    tishi1.setText("涂擦SPF大于15、PA+防嗮护肤品");
                }
                else if (envir.getLight()>3000)
                {
                    suju1.setText("强("+envir.getLight()+")" );
                    tishi1.setText("尽量少外出");
                }
                //根据温度判断感冒
                if( envir.getTemp()<8)
                {
                    suju2.setText("较易发("+envir.getTemp()+")" );
                    tishi2.setText("温度低，风较大，易发生感冒");
                }
                else if (envir.getTemp()>8)
                {
                    suju2.setText("少发("+envir.getTemp()+")" );
                    tishi2.setText("无明显降温，感冒机率低");
                }
                //根据温度判断穿衣
                if( envir.getTemp()<12)
                {
                    suju3.setText("冷("+envir.getTemp()+")" );
                    tishi3.setText("建议穿长袖衬衫");
                }
                else if (envir.getTemp()>=12&& envir.getTemp()<=21)
                {
                    suju3.setText("舒适("+envir.getTemp()+")" );
                    tishi3.setText("建议穿短袖");
                }
                else if (envir.getTemp()>21)
                {
                    suju3.setText("热("+envir.getTemp()+")" );
                    tishi3.setText("适合穿T恤");
                }
                //根据CO2判断运动指数
                if( envir.getCo2()>0&&envir.getCo2()<3000)
                {
                    suju4.setText("适宜("+envir.getCo2()+")" );
                    tishi4.setText("气候适宜，推荐您进行户外运动");
                }
                else if (envir.getCo2()>=3000&& envir.getCo2()<=6000)
                {
                    suju4.setText("中("+envir.getCo2()+")" );
                    tishi4.setText("易感人去应减少室外活动");
                }
                else if (envir.getCo2()>6000)
                {
                    suju4.setText("较不宜("+envir.getCo2()+")" );
                    tishi4.setText("空气氧气含量低，请在室内进行休闲运动");
                }
                //根据PM2.5判断空气污染指数
                if( envir.getPm()>0&&envir.getPm()<30)
                {
                    suju5.setText("优("+envir.getPm()+")" );
                    tishi5.setText("空气质量非常好，适合户外运动");
                }
                else if (envir.getPm()>=30&& envir.getPm()<=100)
                {
                    suju5.setText("良("+envir.getPm()+")" );
                    tishi5.setText("易感人去应减少室外活动");
                }
                else if (envir.getPm()>100)
                {
                    suju5.setText("较不宜("+envir.getPm()+")" );
                    tishi5.setText("空气质量差，请在室内进行休闲运动");
                }
            }   });

    }

//    public   void fabj(){
//        SharedPreferences sp=mContext.getSharedPreferences("data",MODE_PRIVATE);
//        int a1=sp.getInt("wendu",10);
//        int a2=sp.getInt("shidu",0);
//        int a3=sp.getInt("guangzhao",0);
//        int a4=sp.getInt("co2",0);
//        int a5=sp.getInt("pm2",0);
//
//    }

}
