package mad.com.its02.fragment;

import android.net.Uri;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.net.URI;
import java.net.URL;

import mad.com.its02.R;


public class ViolateVideoPlayFragment extends BaseFragment {
    private VideoView videoView;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_violate_playvideo;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
       videoView=(VideoView)mView.findViewById(R.id.video);
        PlayVideo();
    }
    public void PlayVideo()
    {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        videoView.setLayoutParams(layoutParams);
        String uri="android.resource://"+getActivity().getPackageName()+"/"+R.raw.demo;
        videoView.setVideoURI(Uri.parse(uri));
        MediaController mc = new MediaController(getActivity());
        //设置控制器 控制的是那一个videoview
        mc.setAnchorView(videoView);
        //设置videoview的控制器为mc
        videoView.setMediaController(mc);
        videoView.start();
    }
}
