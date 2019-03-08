package mad.com.its02.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.List;

import mad.com.its02.R;

public class ViolateAnalysis2Fragment extends BaseFragment {
    private PieChart pieChart;
    private List<Fragment> resources=new ArrayList<Fragment>();
//    private ViolateAnalysis2PieChartFragment violateAnalysis2PieChartFragment;
    private ViolateAnalysis2BarChartFragment violateAnalysis2BarChartFragment;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_violate_analysis2;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
//        violateAnalysis2PieChartFragment=new ViolateAnalysis2PieChartFragment();
//        violateAnalysis2BarChartFragment=new ViolateAnalysis2BarChartFragment();
//
//        resources.add(violateAnalysis2PieChartFragment);
//        resources.add(violateAnalysis2BarChartFragment);
//
//        ViolateAnalysis1Fragment violateAnalysis1Fragment=new ViolateAnalysis1Fragment();
//       ViewPager viewPager=(ViewPager) mView.findViewById(R.id.analysis2_viewpager);
//       FragmentAdapter fragmentAdapter=new FragmentAdapter(getFragmentManager(),resources);
//        viewPager.setAdapter(fragmentAdapter);


    }

    public class FragmentAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragmentList;//数据源
        private List<String> titles;//标题

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        //相应页卡设定相应的Fragment
        public Fragment getItem(int arg0) {

            return fragmentList.get(arg0);
        }

        //数据源的数目
        public int getCount() {

            return fragmentList.size();
        }

        /*
39      * 对于FragmentStatePagerAdapter，它的销毁和实例方法，即
40      * destroyItem和instantiateItem方法保持默认即可
41      */
        public void destroyItem(ViewGroup container, int position, Object object) {

            super.destroyItem(container, position, object);
        }

        public Object instantiateItem(ViewGroup arg0, int arg1) {

            return super.instantiateItem(arg0, arg1);
        }

    }
}
