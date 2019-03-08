package mad.com.its02.fragment;

import android.graphics.Color;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import mad.com.its02.R;

public class ViolateAnalysis1Fragment extends BaseFragment {
    private int[] colors;
    private String title;
    private LinearLayout linearLayout;
    private double violatedata[]={71.3,28.6};//设置数值
    private String wenzi[]={"无违章：","违章："};

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_violate_analysis1;
    }

    @Override
    protected void initData() {
        title="平台上有违章车辆和没有违章车辆的占比统计";//设置标题

    }

    @Override
    protected void initView() {
        colors=new int[]{
                Color.rgb(170,76,67),Color.rgb(69,114,167)
        };
        DefaultRenderer renderer=buildCatgoryRenderer(colors);//把颜色分给渲染器
        renderer.setZoomButtonsVisible(false);//是否显示放大缩小按钮
        renderer.setZoomEnabled(true);//是否支持放大放小
        renderer.setChartTitleTextSize(20);//设置图表标题字号
        renderer.setInScroll(true);
        renderer.setLabelsColor(Color.BLACK);//设置Y轴的颜色
        linearLayout=(LinearLayout)mView.findViewById(R.id.analysis1_pie_chart);
        //饼状图文字信息和对应百分比
        linearLayout.addView(ChartFactory.getPieChartView(getActivity(),buildCategoryDataset(title,violatedata),renderer));

    }

    //把分布的颜色传给渲染器
    private DefaultRenderer buildCatgoryRenderer(int[]colors)
    {
        DefaultRenderer renderer=new DefaultRenderer();
        renderer.setLabelsTextSize(25);//得到Y轴大小
        renderer.setLegendTextSize(25);//得到图例字号
        renderer.setMargins(new int[]{20,30,15,0});//图表外边框大小
        renderer.setChartTitle(title);//设置图表标题
        renderer.setPanEnabled(false);//是否允许拖动
        renderer.setShowLegend(true);//是否显示图例
        renderer.setInScroll(false);//返回图表是否在滚动视图中且不需要收缩。
        renderer.setClickEnabled(false);//是否启用点击事件
        renderer.setExternalZoomEnabled(false);//返回外部（应用程序实现的）缩放的启用状态。
        renderer.setZoomButtonsVisible(false);
        renderer.setZoomEnabled(false);
        for (int color:colors)
        {
            SimpleSeriesRenderer r=new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
    //饼状图文字信息
    protected   CategorySeries buildCategoryDataset(String title,double[]data)
    {
        CategorySeries series=new CategorySeries(title);
        for (int i=0;i<data.length;i++)
        {//分配文字
            series.add(wenzi[i]+data[i]+"%",data[i]);

        }
        return series;
    }

}