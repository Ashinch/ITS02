package mad.com.its02.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import mad.com.its02.R;
import mad.com.its02.config.AppConfig;
import mad.com.its02.request.BaseRequest;
import mad.com.its02.request.BusStationRequest;

/**
 * Created by Administrator on 2019/3/5.
 */

public class BusStationinfoFragment extends BaseFragment {
    ExpandableListView expandableListView;
    private BusStationRequest busStationRequest;
    private MyExtendableListViewAdapter myExtendableListViewAdapter=new MyExtendableListViewAdapter();
    private String distances[][]=new String[2][2];
    private String minute[][]=new String[2][2];


   private android.os.Handler handler = new android.os.Handler(){

   };
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            try {
                handler.postDelayed(this, 3000);
                getonestationdistance();
                gettwostationdistance();

                myExtendableListViewAdapter.notifyDataSetChanged();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected int setLayoutId() {

        return R.layout.fragment_bus_stationinfo;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus_stationinfo, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
        initView();

    }

    @Override
    protected void initData() {
        busStationRequest=new BusStationRequest(getActivity());
        getonestationdistance();
        gettwostationdistance();
        expandableListView=(ExpandableListView)getActivity().findViewById(R.id.bus_expandable_lv);
        expandableListView.setAdapter(myExtendableListViewAdapter);

        handler.postDelayed(runnable, 0);

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();

    }

private void getonestationdistance() {

    busStationRequest.setStationId(1);
    busStationRequest.connec(new BaseRequest.OnGetDataListener(){


        @Override
        public void onReturn(Object data) {
            if (BusStationinfoFragment.this.isAdded()) {
                List<Integer>list=(List<Integer>)data;
                distances[0][0]=list.get(0).toString();
                distances[0][1]=list.get(1).toString();
                minute[0][0]=Integer.valueOf(list.get(0)/(20000/60)).toString();
                minute[0][1]=Integer.valueOf(list.get(1)/(20000/60)).toString();
            }
        }
    });
    JSONObject jsonObject = new JSONObject();
    try {
        jsonObject.put(AppConfig.KEY_BUS_STATION_ID , 1);
    } catch (JSONException e) {
        e.printStackTrace();
    }

}
private void gettwostationdistance()
{
    busStationRequest.setStationId(2);
    busStationRequest.connec(new BaseRequest.OnGetDataListener() {
        @Override
        public void onReturn(Object data) {
            if (BusStationinfoFragment.this.isAdded()) {
                List<Integer>list=(List<Integer>)data;
                distances[1][0]=list.get(0).toString();
                distances[1][1]=list.get(1).toString();
                minute[1][0]=Integer.valueOf(list.get(0)/(20000/60)).toString();
                minute[1][1]=Integer.valueOf(list.get(1)/(20000/60)).toString();
            }
        }
    });
    JSONObject jsonObject = new JSONObject();
    try {
        jsonObject.put(AppConfig.KEY_BUS_STATION_ID , 2);
    } catch (JSONException e) {
        e.printStackTrace();
    }
}


//适配器
    public class MyExtendableListViewAdapter extends BaseExpandableListAdapter {

        public String[] groupString = {"1号站台", "2号站台"};
        public String[][] childString = {
                {"1号(101人)", "2号(101人)"},
                {"1号(101人)", "2号(101人)"}
        };

        @Override
        // 获取分组的个数
        public int getGroupCount() {
            return groupString.length;
        }

        //获取指定分组中的子选项的个数
        @Override
        public int getChildrenCount(int groupPosition) {
            return childString[groupPosition].length;
        }

        //        获取指定的分组数据
        @Override
        public Object getGroup(int groupPosition) {
            return groupString[groupPosition];
        }

        //获取指定分组中的指定子选项数据
        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childString[groupPosition][childPosition];
        }

        //获取指定分组的ID, 这个ID必须是唯一的
        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        //获取子选项的ID, 这个ID必须是唯一的
        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        //分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们
        @Override
        public boolean hasStableIds() {
            return true;
        }

        /**
         * 获取显示指定组的视图对象
         *
         * @param groupPosition 组位置
         * @param isExpanded    该组是展开状态还是伸缩状态
         * @param convertView   重用已有的视图对象
         * @param parent        返回的视图对象始终依附于的视图组
         */
// 获取显示指定分组的视图
        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.partent_item, parent, false);


            } else {

            }
            TextView textView1 = (TextView) convertView.findViewById(R.id.list_partent);
            textView1.setText(groupString[groupPosition]);
            return convertView;
        }

        /**
         * 获取一个视图对象，显示指定组中的指定子元素数据。
         *
         * @param groupPosition 组位置
         * @param childPosition 子元素位置
         * @param isLastChild   子元素是否处于组中的最后一个
         * @param convertView   重用已有的视图(View)对象
         * @param parent        返回的视图(View)对象始终依附于的视图组
         * @return
         * @see android.widget.ExpandableListAdapter#getChildView(int, int, boolean, View,
         * ViewGroup)
         */

        //取得显示给定分组给定子位置的数据用的视图
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_item, parent, false);


            } else {

            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.bus_list_item_iv);
            TextView station = (TextView) convertView.findViewById(R.id.bus_list_item_tv1);
            imageView.setBackgroundResource(R.mipmap.icon_speed);
            station.setText(childString[groupPosition][childPosition]);
            TextView busdistances=(TextView)convertView.findViewById(R.id.bus_distance);
            TextView time=(TextView)convertView.findViewById(R.id.bus_list_item_tv2);
            time.setText(minute[groupPosition][childPosition]+"分钟后到达");

            busdistances.setText("距离站台"+distances[groupPosition][childPosition]+"米");
            return convertView;
        }

        //指定位置上的子元素是否可选中
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        class GroupViewHolder {
            TextView tvTitle;
        }

        class ChildViewHolder {
            TextView tvTitle;

        }
    }
}
