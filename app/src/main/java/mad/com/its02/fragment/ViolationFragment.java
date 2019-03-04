package mad.com.its02.fragment;

import android.view.View;
import android.widget.Button;

import mad.com.its02.R;

public class ViolationFragment extends BaseFragment implements View.OnClickListener{
    private Button mBtnViolateVideo;
    private Button mBtnViolatePicture;
    private Button mBtnAnalysis1;
    private Button mBtnAnalysis2;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_violation;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBtnViolateVideo = (Button) mView.findViewById(R.id.btn_violate_video);
        mBtnViolatePicture = (Button) mView.findViewById(R.id.btn_violate_picture);
        mBtnAnalysis1 = (Button) mView.findViewById(R.id.btn_violate_analysis1);
        mBtnAnalysis2 = (Button) mView.findViewById(R.id.btn_violate_analysis2);

        mBtnViolateVideo.setOnClickListener(this);
        mBtnViolatePicture.setOnClickListener(this);
        mBtnAnalysis1.setOnClickListener(this);
        mBtnAnalysis2.setOnClickListener(this);

        gotoFragment(R.id.violate_content,new ViolateVideoFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //  违章视频
            case R.id.btn_violate_video:
                gotoFragment(R.id.violate_content,new ViolateVideoFragment());
                break;
            //  违章图片
            case R.id.btn_violate_picture:
                gotoFragment(R.id.violate_content,new ViolatePictureFragment());
                break;
            //  数据分析1
            case R.id.btn_violate_analysis1:
                gotoFragment(R.id.violate_content,new ViolateAnalysis1Fragment());
                break;
            //  数据分析2
            case R.id.btn_violate_analysis2:
                gotoFragment(R.id.violate_content,new ViolateAnalysis2Fragment());
                break;
        }
    }
}
