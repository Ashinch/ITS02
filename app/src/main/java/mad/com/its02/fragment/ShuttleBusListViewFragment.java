package mad.com.its02.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import mad.com.its02.R;

/**
 * Created by Administrator on 2019/3/11.
 */

public class ShuttleBusListViewFragment extends BaseFragment {
    private String data[]={"光谷金融街——南湖商厦","光谷金融街——南湖商厦","光谷金融街——南湖商厦","光谷金融街——南湖商厦"};
    private String jiage[]={"票价：￥8.0  里程：20.0km","票价：￥8.0  里程：20.0km","票价：￥8.0  里程：20.0km","票价：￥8.0  里程：20.0km"};
    private String shijia1[]={"06：45","06：45","06：45","06：45"};
    private String shijian2[]={"19：45","19：45","19：45","19：45"};

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shuttle_listview;
    }



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ListView listView=(ListView)mView.findViewById(R.id.shuttle_lv);
        ListAdapter listAdapter=new ListAdapter();
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoFragment(R.id.shuttle_bus_content,new ShuttleBusDetailFragment());
            }
        });
    }
    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return data.length;
        }

        @Override
        public Object getItem(int position) {
            return data[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(getContext(),R.layout.shuttlebus_list,null);
            TextView location=(TextView)view.findViewById(R.id.didian_tv);
            location.setText(data[position]);
            TextView price=(TextView)view.findViewById(R.id.jiage_tv2);
            TextView time1=(TextView)view.findViewById(R.id.time_tv);
            TextView time2=(TextView)view.findViewById(R.id.time_tv2);
            ImageView imageView=(ImageView)view.findViewById(R.id.shuttle_iv) ;

            ImageView image=(ImageView) view.findViewById(R.id.shuttle_iv2);

            price.setText(jiage[position]);
            time1.setText(shijia1[position]);
            time2.setText(shijian2[position]);

            imageView.setBackgroundResource(R.mipmap.ic_location_on_white_24dp);
            image.setBackgroundResource(R.mipmap.ic_tv_white_24dp);
            return view;
        }
    }
}
