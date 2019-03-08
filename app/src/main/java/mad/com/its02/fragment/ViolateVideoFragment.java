package mad.com.its02.fragment;

import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;

import mad.com.its02.R;

public class ViolateVideoFragment extends BaseFragment implements View.OnClickListener{
    private ImageButton igbtn;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_violate_video;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
            igbtn=(ImageButton)mView.findViewById(R.id.igbtn_violate_video);
        igbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.igbtn_violate_video:
                gotoFragment(R.id.violate_content,new ViolateVideoPlayFragment());
                break;
        }
    }
}
