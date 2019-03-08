package mad.com.its02.fragment;

import android.view.View;
import android.widget.ImageButton;

import mad.com.its02.R;

public class ViolatePictureFragment extends BaseFragment implements View.OnClickListener{
    private ImageButton igbtn;


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_violate_picture;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
           igbtn=(ImageButton)mView.findViewById(R.id.igbtn_violate_picture);
           igbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.igbtn_violate_picture:
                gotoFragment(R.id.violate_content,new ViolatePictureDetailFragment());
                break;
        }
    }
}
