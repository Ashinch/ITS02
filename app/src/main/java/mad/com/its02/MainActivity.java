package mad.com.its02;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import mad.com.its02.fragment.BusStationFragment;
import mad.com.its02.fragment.CarValueFragment;
import mad.com.its02.fragment.EnvironmentFragment;
import mad.com.its02.fragment.EtcFragment;
import mad.com.its02.fragment.FragmentHome;
import mad.com.its02.fragment.ParkingFragment;
import mad.com.its02.fragment.SpeedFragment;
import mad.com.its02.fragment.TrafficFragment;


/**
 * @author zhaowei
 *阿斯蒂芬
 */
public class MainActivity extends FragmentActivity
{
    private SlidingPaneLayout slidepanel;

    private Fragment fragment;

    private ListView listView;
    SimpleAdapter simpleAdapter;

    ArrayList<HashMap<String, Object>> actionItems;
    SimpleAdapter actionAdapter;

    TextView tV_title;
    ImageView ivHome;

    String[] actionTexts;
    int[]  actionImages ;

    private android.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
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

        actionTexts = new String[]{
                getString(R.string.res_left_chengshijiaotong),
                getString(R.string.res_left_gongjiaozhandian),
                getString(R.string.res_left_chengshihuanjing),
                getString(R.string.res_left_zhaochewei),
                getString(R.string.res_left_honglvdengguanli),
                getString(R.string.res_left_etcguanli),
                getString(R.string.res_left_gaosuchesujiankong),
                getString(R.string.res_left_zhanghuchongzhi),
                getString(R.string.res_left_chuanyi),
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
                    //  城市交通
                    case 0:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new TrafficFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  公交站点
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new BusStationFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  城市环境
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new EnvironmentFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  找车位
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new ParkingFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  红绿灯管理
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new TrafficFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  ETC管理
                    case 5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new EtcFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  高速车速监控
                    case 6:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new SpeedFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  账户充值
                    case 7:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new CarValueFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;

//                    case 8:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new CarValueFragment()).commit();
//                        tV_title.setText( actionTexts[arg2] );
//                        break;

                    default:
                        break;
                }

            }
        });

        fragmentManager = getFragmentManager();
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


}
