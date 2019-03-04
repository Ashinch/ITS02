package mad.com.its02.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import mad.com.its02.R;

public class ContentAdapter extends BaseAdapter {

	private static final String TAG = "ContentAdapter";
	private ArrayList<Map<String,Object>> mListItem;
	private LayoutInflater mInflater;
	private IClick mListener;

    public class ViewHolder {
        public ImageView carImageItem;
        public TextView carNumberItem;
        public TextView carBalanceItem;
        public Button carRechargeItem;
    }

	/**
	 * @param context
	 * @param contentList
	 * @param listener
	 */
	public ContentAdapter(Context context, ArrayList<Map<String,Object>> contentList,
                          IClick listener) {
		mListItem = contentList;
		mInflater = LayoutInflater.from(context);
		mListener = listener;
	}

	@Override
	public int getCount() {
		Log.i(TAG, "getCount");
		return mListItem.size();
	}

	@Override
	public Object getItem(int position) {
		Log.i(TAG, "getItem");
		return mListItem.get(position);
	}

	@Override
	public long getItemId(int position) {
		Log.i(TAG, "getItemId");
		return position;
	}

	/**
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.i(TAG, "getView");
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.list_item_carvalue, null);
			holder = new ViewHolder();
			holder.carImageItem = (ImageView) convertView.findViewById(R.id.carImageItem);
			holder.carNumberItem = (TextView) convertView.findViewById(R.id.carNumberItem);
			holder.carBalanceItem = (TextView) convertView.findViewById(R.id.carBalanceItem);
			holder.carRechargeItem = (Button) convertView.findViewById(R.id.carRechargeItem);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.carImageItem.setBackgroundResource((Integer)mListItem.get(position).get("image"));
		holder.carNumberItem.setText(mListItem.get(position).get("number").toString());
		holder.carBalanceItem.setText(mListItem.get(position).get("balance").toString());
		holder.carRechargeItem.setOnClickListener(mListener);
		holder.carRechargeItem.setTag(position);
		return convertView;
	}
}