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
import mad.com.its02.fragment.LiveFragment;
import mad.com.its02.fragment.ViolationFragment;
import mad.com.its02.fragment.SubwayFragment;
import mad.com.its02.fragment.ParkFragment;
import mad.com.its02.fragment.FragmentHome;
import mad.com.its02.fragment.ShuttleBusFragment;
import mad.com.its02.fragment.RoadFragment;
import mad.com.its02.fragment.UserFragment;


/**
 * @author zhaowei
 *
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
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new UserFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  公交站点
                    case 1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new BusStationFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  地铁信息
                    case 2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new SubwayFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  班车预定
                    case 3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new ShuttleBusFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  生活助手
                    case 4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new LiveFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  停车场
                    case 5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new ParkFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  道路监测
                    case 6:
                        getSupportFragmentManager().beginTransaction().replace(R.id.maincontent, new RoadFragment()).commit();
                        tV_title.setText( actionTexts[arg2] );
                        break;
                    //  违章查询
                    case 7:
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
