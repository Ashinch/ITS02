package mad.com.its02.adapter;

import android.view.View;
import android.view.View.OnClickListener;

public abstract class IClick implements OnClickListener {
	@Override
	public void onClick(View v) {
		listViewItemClick((Integer) v.getTag(), v);
	}

	public abstract void listViewItemClick(int position, View v);

}
