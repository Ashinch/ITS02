package mad.com.its02.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import mad.com.its02.R;
import mad.com.its02.bean.Envir;
import mad.com.its02.fragment.BusStationFragment;
import mad.com.its02.fragment.FragmentHome;
import mad.com.its02.fragment.LiveFragment;
import mad.com.its02.fragment.ParkFragment;
import mad.com.its02.fragment.RoadFragment;
import mad.com.its02.fragment.ShuttleBusFragment;
import mad.com.its02.fragment.SubwayFragment;
import mad.com.its02.fragment.UserFragment;
import mad.com.its02.fragment.ViolationFragment;
import mad.com.its02.request.BaseRequest;
import mad.com.its02.request.EnvirRequest;
import mad.com.its02.utils.Session;

import static java.lang.Thread.sleep;


/**
 * @author zhaowei
 *
 */
public class MainActivity extends BaseActivity
{
    private SlidingPaneLayout slidepanel;

    private Fragment fragment;

    private ListView listView;
    private String neirong;
    SimpleAdapter simpleAdapter;

    ArrayList<HashMap<String, Object>> actionItems;
    SimpleAdapter actionAdapter;

    TextView tV_title;
    ImageView ivHome;

    String[] actionTexts;
    int[]  actionImages ;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {


        actionTexts = new String[]{
                getString(R.string.res_left_user_info),
                getString(R.string.res_left_bus_station),
                getString(R.string.res_left_subway),
                getString(R.string.res_left_shuttle_bus),
                getString(R.string.res_left_live),
                getString(R.string.res_left_park),
                getString(R.string.res_left_road),
                getString(R.string.res_left_violation),
                getString(R.string.res_left_idea),
                getString(R.string.res_left_exit)
        };
        actionImages = new int[]{
                R.mipmap.btn_l_star,
                R.mipmap.btn_l_book,
                R.mipmap.btn_l_slideshow,
                R.mipmap.btn_l_target,
                R.mipmap.btn_l_download,
                R.mipmap.btn_l_arrows,
                R.mipmap.btn_l_share,
                R.mipmap.btn_l_tag,
                R.mipmap.btn_l_suitcase
        };

    }

    @Override
    protected void initView() {


            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        SharedPreferences sp=MainActivity.this.getSharedPreferences("data",MODE_PRIVATE);
                        sp.getBoolean("kaiguan",true);//获取switch的开关状态
                        if (sp.getBoolean("kaiguan",true)==true) {//自动报警打开的时候才会发送通知
                            tongzhi();
                        }
                        try {
                            sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        slidepanel = (SlidingPaneLayout) findViewById(R.id.slidingPL);
        listView = (ListView)findViewById(R.id.listView1);
        ivHome = (ImageView)findViewById(R.id.imageView_home);
        ivHome.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setHome();
            }
        });

        tV_title = (TextView)findViewById(R.id.tv_title);
        tV_title.setText(getString(R.string.app_title));

        tV_title.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (slidepanel.isOpen()) {
                    slidepanel.closePane();
                } else {
                    slidepanel.openPane();
                }
            }
        });



        actionItems = new ArrayList<HashMap<String, Object>>();
        actionItems = getData();
        actionAdapter = new SimpleAdapter(getApplicationContext(), actionItems, R.layout.left_list_fragment_item,
                new String[]{"action_icon", "action_name"},
                new int[]{R.id.recharge_method_icon, R.id.recharge_method_name});
        listView.setAdapter(actionAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                switch (arg2) {
                    //  个人中心
                    case 0:
                        slidepanel.closePane();
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new UserFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  公交站点
                    case 1:
                        slidepanel.closePane();
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new BusStationFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  地铁信息
                    case 2:
                        slidepanel.closePane();
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new SubwayFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  班车预定
                    case 3:
                        slidepanel.closePane();
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new ShuttleBusFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  生活助手
                    case 4:
                        slidepanel.closePane();
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new LiveFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  停车场
                    case 5:
                        slidepanel.closePane();
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new ParkFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  道路监测
                    case 6:
                        slidepanel.closePane();
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new RoadFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  违章查询
                    case 7:
                        slidepanel.closePane();
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new ViolationFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;

//                    case 8:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new ViolationFragment()).commit();
//                        tV_title.setText( actionTexts[arg2] );
//                        break;

                    default:
                        break;
                }

            }
        });

        setHome();
    }

    public void setHome() {
        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new FragmentHome()).commit();
        tV_title.setText(R.string.app_title);
    }

    private ArrayList<HashMap<String, Object>> getData() {
        ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
        for(int i = 0; i < actionImages.length; i++) {
            HashMap<String, Object> item1 = new HashMap<String, Object>();
            item1.put("action_icon", actionImages[i]);
            item1.put("action_name", actionTexts[i]);
            items.add(item1);
        }
        return items;
    }



    public void tongzhi()    {

       //获取传感器当前值与设置的阈值进行判断
        EnvirRequest envirRequest = new EnvirRequest(MainActivity.this);

        envirRequest.connec(new BaseRequest.OnGetDataListener() {
            @Override
            public void onReturn(Object data) {
                Envir envir = (Envir) data;



                    //创建并发送通知
                    NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notification=new Notification();



                    Notification.Builder builder=new Notification.Builder(MainActivity.this)//创建一个通知对象 
                            .setAutoCancel(true)
                            .setContentTitle("通知")
                            .setContentText(neirong)
                            .setSmallIcon(R.mipmap.icon_etc)
                            .setWhen(System.currentTimeMillis())
                            .setOngoing(true);

                    if (envir.getTemp()>Session.envir.getTemp())
                    {
                        neirong="温度报警，阈值"+Session.envir.getTemp()+",当前值"+envir.getTemp()+"。";
                        builder .setContentText(neirong);
                        notification=builder.getNotification();
                        notificationManager.notify(1,notification);
                    }else
                    {
                        notificationManager.cancel(1);

                    }
                    if (envir.getHumi()>Session.envir.getHumi())
                    {
                        neirong="湿度报警，阈值"+Session.envir.getHumi()+",当前值"+envir.getHumi()+"。";
                        builder .setContentText(neirong);
                        notification=builder.getNotification();
                        notificationManager.notify(2,notification);
                    }else
                    {
                        notificationManager.cancel(2);
                    }

                    if (envir.getLight()>Session.envir.getLight())
                    {
                        neirong="光照报警，阈值"+Session.envir.getLight()+",当前值"+envir.getLight()+"。";
                        builder .setContentText(neirong);
                        notification=builder.getNotification();
                        notificationManager.notify(3,notification);
                    }
                    else
                    {
                        notificationManager.cancel(3);
                    }
                    if (envir.getCo2()>Session.envir.getCo2())
                    {
                        neirong="CO2报警，阈值"+Session.envir.getCo2()+",当前值"+envir.getCo2()+"。";
                        builder .setContentText(neirong);
                        notification=builder.getNotification();
                        notificationManager.notify(4,notification);
                    }else
                    {
                        notificationManager.cancel(4);
                    }
                    if (envir.getPm()>Session.envir.getPm())
                    {
                        neirong="PM2.5报警，阈值"+Session.envir.getPm()+",当前值"+envir.getPm()+"。";
                        builder .setContentText(neirong);
                        notification=builder.getNotification();
                        notificationManager.notify(5,notification);
                    }else
                    {
                        notificationManager.cancel(5);
                    }

//        Notification notification=new Notification(R.mipmap.icon_etc,"来通知了",System.currentTimeMillis());
//               Intent intent=new Intent(MainActivity.this, UserThresholdFragment.class);
//       PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,intent,0);
//        //设置事件信息
//        notification.setLatestEventInfo(MainActivity.this, "My Title", "My Content", pi);
//        //发出通知
//        notification.notify(1,notification);



//            Notification.Builder notificationa=new Notification.Builder(MainActivity.this)//创建一个通知对象
//        .setAutoCancel(true)//设置通知打开后,自动消失
//        .setSmallIcon(R.mipmap.icon_etc)//设置通知图标
//        .setContentTitle("通知")
//        .setContentText("湿度报警:阈值80，当前值85")
//        .setWhen(System.currentTimeMillis());//设置发送时间
//        notification.build();
//        Intent intent=new Intent(MainActivity.this, UserThresholdFragment.class);
//        PendingIntent pi=PendingIntent.getActivity(MainActivity.this,0,intent,0);
//        notification.setContentIntent(pi);
 //       notificationManager.notify();
       // notification.notify();
        //notificationManager.notify(NOTIFICATION_SERVICE,notification.build());//发送通知
      //  notificationManager.notify(NOTIFY_ID,notification.build());

            }   });

    }


}
