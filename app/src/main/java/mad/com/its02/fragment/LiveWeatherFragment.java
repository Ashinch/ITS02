package mad.com.its02.fragment;

import android.graphics.Color;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

import mad.com.its02.R;

public class LiveWeatherFragment extends BaseFragment {

    // 每个Item的title
    private String itemTitles =  "Water";
    // x轴的值
    private List<double[]> x = new ArrayList<double[]>();
    // y轴的值
    private List<double[]> y = new ArrayList<double[]>();

    private LinearLayout linearView;
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_live_weather;
    }

    @Override
    protected void initData() {
        linearView=(LinearLayout)mView.findViewById(R.id.chart_show);


    }

    @Override
    protected void initView() {
        // 准备数据
        initDateSet();

        // 将数据封装成XYMultipleSeriesDataset
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

            // 设置每条折线的标题
            XYSeries series = new XYSeries(itemTitles);
            // 每条线每个点坐标值，也就是x,y值
//            for (int j = 0; j < x.get(i).length; j++) {
//                series.add(x.get(i)[j], y.get(i)[j]);
//            }

        series.add(3,15);
        series.add(6,30);
        series.add(9,50);
        series.add(12,2);
        series.add(15,1);
        series.add(18,12);
        series.add(21,20);
        series.add(24,15);
        series.add(27,15);
        series.add(30,15);
        series.add(33,15);
        series.add(36,15);
        series.add(39,15);
        series.add(42,15);
        series.add(45,15);
        series.add(48,15);
        series.add(51,15);
        series.add(54,15);
        series.add(57,15);
        series.add(60,15);

            // 数据集里添上一条线
            dataset.addSeries(series);

//        Intent intent = ChartFactory.getLineChartIntent(mContext, dataset,
//                getTemperatureDemoRenderer());
//       startActivity(intent);
        //linearView.addView(ChartFactory.getLineChartIntent(getActivity(),buildCategoryDataset(title,violatedata),renderer));
linearView.addView(ChartFactory.getLineChartView(mContext,dataset,getTemperatureDemoRenderer()));
    }

    private void initDateSet() {

            x.add(new double[] { 3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36,39,42,45,48,51,54,57,60 });

        y.add(new double[] { 15,  17,  19, 21,23, 25 });

    }


    private XYMultipleSeriesRenderer getTemperatureDemoRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();

        // 每条折线的颜色
        int colors = Color.BLACK;
        // 点的样式
        PointStyle pointStyle = PointStyle.CIRCLE ;

            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors);// 折线的颜色
            r.setPointStyle(pointStyle);// 折线点的风格
        //r.setChartValuesTextSize(20);

            r.setFillPoints(true);// 点是否实心
            r.setLineWidth(3);//设置线宽
            renderer.addSeriesRenderer(r);

       renderer.setPointSize(5.0f);//设置点的大小
        renderer.setPanEnabled(false,false);    //曲线是否是可滑动触摸的

           renderer.setShowGrid(true);//是否显示网格线
        renderer.setShowGridX(true);//是否显示X方向的网格线
        renderer.setShowGridY(false);//是否显示Y方向的网格线

//        renderer.setShowLabels(false);//是否显示XY轴的数值和标题
//        renderer.setShowLegend(true);//是否显示图例，就是图表下对图中一些折线或者标识的一些解释



        renderer.setAxisTitleTextSize(25);// 设置轴标题文本大小
        renderer.setChartTitle("过去一分钟");// 设置图表标题
        renderer.setChartTitleTextSize(28);// 图表标题字体大小
        renderer.setFitLegend(false);// 设置是否显示图例
        renderer.setLabelsColor(Color.BLACK); // 主标题、X轴标题、Y轴标题、annoation颜色
         renderer.setLegendTextSize(15); // 曲线说明

       renderer.setXAxisMax(60);
         renderer.setXAxisMin(0);
        renderer.setYAxisMin(0); // 设置Y轴起点
        renderer.setYAxisMax(50); // 设置Y轴终点

        renderer.setInScroll(false);//调整大小
        renderer.setMarginsColor(Color.parseColor("#FFFFFF"));// 设置背景颜色
        renderer.setBackgroundColor(Color.parseColor("#FFFFFF"));  // 图标部分的背景颜色
        renderer.setApplyBackgroundColor(true);
         renderer.setXTitle("(S)"); // x轴说明
        renderer.setYTitle("℃");//Y轴说明

        renderer.setXLabelsColor(Color.parseColor("#000000"));//调整X轴字体的颜色
        renderer.setYLabelsColor(0,Color.parseColor("#000000"));
       // renderer.setXLabelsAlign(Paint.Align.RIGHT);// 设置刻度线与Y轴之间的相对位置关系
        renderer.setLabelsTextSize(19);//数轴字体大小
        renderer.setMargins(new int[] { 30, 30, 30, 30 });//设置四周的距离

        renderer.setZoomButtonsVisible(true);// 显示放大缩小还原的按钮
        // x,y轴显示的单元个数
        renderer.setXLabels(20);
        renderer.setYLabels(10);

        renderer.setXLabels(0); //设置X轴不显示数字（改用自定义的值）
        for (int i=1;i<=20;i++)
        {
          int a=i*3;
            if (a>9) {
                renderer.addXTextLabel(a, "" + a + ""); //设置X轴坐标3显示的值
            }else {
                renderer.addXTextLabel(a, "0" + a + "");
            }
        }


        return renderer;

    }
}
