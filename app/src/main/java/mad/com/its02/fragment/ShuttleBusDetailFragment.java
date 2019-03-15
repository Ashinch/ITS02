package mad.com.its02.fragment;

import android.view.View;
import android.widget.Button;

import mad.com.its02.R;


public class ShuttleBusDetailFragment extends BaseFragment implements View.OnClickListener{
private Button next;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shuttle_detail;
    }

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initView()
    {
      next=(Button)mView.findViewById(R.id.shuttle_bus_next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.shuttle_bus_next:
                gotoFragment(R.id.shuttle_bus_content,new ShuttleBusTimeFragment());

                break;
        }
    }
}
