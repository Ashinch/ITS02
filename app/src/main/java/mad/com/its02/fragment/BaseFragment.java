package mad.com.its02.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mad.com.its02.R;

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(setLayoutId(), container, false);
        mContext = mView.getContext();
        initData();
        initView();
        return mView;
    }

    protected abstract int setLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected void gotoFragment(int content,Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(content, fragment)
                .commit();
    }
}
