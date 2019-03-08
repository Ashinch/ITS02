package mad.com.its02.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import mad.com.its02.R;

/**
 * Created by Administrator on 2019/3/5.
 */

public class BusDetailFragment extends BaseFragment {
     private String order[]={"序号"};
     private String BusId[]={"车号"};
     private String Number[]={"承载人数"};

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_bus_zaike;
    }

    @Override
    protected void initData() {
        BusListAdapter busListAdapter=new BusListAdapter();
        ListView listView=(ListView)mView.findViewById(R.id.bus_lv);
        listView.setAdapter(busListAdapter);
    }

    @Override
    protected void initView() {

    }



    class BusListAdapter extends BaseAdapter {
        @Override
        public int getCount(){
            return order.length;
        }

        @Override
        public Object getItem(int position) {
            return order[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view=View.inflate(getContext(), R.layout.bus_zaike_item,null);
            TextView busorder=(TextView)view.findViewById(R.id.bus_order_tv);
            TextView busid=(TextView)view.findViewById(R.id.bus_id_tv);
            TextView busnumber=(TextView)view.findViewById(R.id.bus_number_tv);

            busorder.setText(order[position]);
            busid.setText(BusId[position]);
            busnumber.setText(Number[position]);

            busid.setBackgroundResource(R.drawable.bus_table);
            busorder.setBackgroundResource(R.drawable.bus_table);
            busnumber.setBackgroundResource(R.drawable.bus_table);

            return view;
        }
    }
}
