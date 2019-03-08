package mad.com.its02.fragment;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import mad.com.its02.R;

public class ViolateAnalysis2BarChartFragment extends  BaseFragment {
    private HorizontalBarChart barChart;
    private float values[]={3.37f,3.87f,4.31f,4.80f,4.82f,7.43f,9.97f,12.87f,22.10f,26.46f};
    private String label[]={"机动车逆向行驶","违规使用专用车道","违反禁令标志指示","不按规定系安全带","机动车不走机动车道","违反信号灯规定","违反禁止标线指示","违规停车拒绝驶离","违规驶入导向车道","超速行驶"};
    private int Order[]={0,1,2,3,4,5,6,7,8,9};
    @Override
    protected int setLayoutId()
    {
        return R.layout.fragment_violate_analysis2barchart;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //设置控件相关属性
        barChart=(HorizontalBarChart)mView.findViewById(R.id.violate_analysis2_bar_chart);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(60);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);


//x轴属性
        XAxis x1=barChart.getXAxis();
        x1.setPosition(XAxis.XAxisPosition.BOTTOM);
        x1.setDrawAxisLine(true);
        x1.setDrawGridLines(false);
        x1.setAxisLineWidth(10f);
//Y轴属性
        YAxis y1=barChart.getAxisLeft();
        y1.setDrawAxisLine(true);
        y1.setDrawGridLines(true);
        y1.setAxisMinValue(0f);

        //设置数据
        setData();
        barChart.animateY(2500);

        Legend l=barChart.getLegend();


    }
    private  void  setData()
    {
        ArrayList<BarEntry>yValues=new ArrayList<BarEntry>();
        for (int i=0;i<values.length;i++) {

            yValues.add(new BarEntry(values[i], Order[i], label[i]));
        }
        BarDataSet bardataset;
        bardataset=new BarDataSet(yValues,"DataSet1");
        ArrayList<BarDataSet>dataSets=new ArrayList<BarDataSet>();
        dataSets.add(bardataset);
        BarData data=new BarData();
        ;
        for (int i=0;i<dataSets.size();i++) {
            data.addDataSet(dataSets.get(i));
        }
        barChart.setData(data);
    }

}
