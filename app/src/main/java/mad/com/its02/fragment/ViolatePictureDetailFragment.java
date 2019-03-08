package mad.com.its02.fragment;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

import mad.com.its02.R;


public class ViolatePictureDetailFragment extends BaseFragment{
    private ImageView Picture;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_violate_picturedetail;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Picture=(ImageView)mView.findViewById(R.id.picture_detail_iv);
        Picture.setBackgroundResource(R.mipmap.violation_picture);

    }


}
