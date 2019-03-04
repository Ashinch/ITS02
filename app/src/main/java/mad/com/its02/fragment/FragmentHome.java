package mad.com.its02.fragment;

import android.view.View;
import android.widget.Button;

import mad.com.its02.R;

public class FragmentHome extends BaseFragment implements View.OnClickListener{


	@Override
	protected int setLayoutId() {
		return R.layout.fragment_home;
	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initView() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		}
	}
}
