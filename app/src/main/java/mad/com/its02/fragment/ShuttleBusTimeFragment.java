package mad.com.its02.fragment;

import android.view.View;
import android.widget.Button;

import mad.com.its02.R;

/**
 * Created by Administrator on 2019/3/12.
 */

public class ShuttleBusTimeFragment extends BaseFragment implements View.OnClickListener{

    private Button next;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_shuttle_time;
    }

    @Override
    protected void initData()
    {

    }

    @Override
    protected void initView()
    {
        next=(Button)mView.findViewById(R.id.shuttle_bus_next2);
next.setOnClickListener(this);
    }
   @Override
    public void onClick(View view)
   {
       switch (view.getId())
       {
           case R.id.shuttle_bus_next2:
               gotoFragment(R.id.shuttle_bus_content,new ShuttleBusConfirm());
               break;

       }
   }
}
