//package mad.com.its02.fragment;
//
//import android.graphics.Color;
//import android.view.LayoutInflater;
//
//import com.github.mikephil.charting.charts.PieChart;
//import com.github.mikephil.charting.data.Entry;
//import com.github.mikephil.charting.data.PieData;
//import com.github.mikephil.charting.data.PieDataSet;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import mad.com.its02.R;
//
///**
// * Created by Administrator on 2019/3/8.
// */
//
//public class ViolateAnalysis2PieChartFragment extends  BaseFragment {
//    private PieChart pieChart;
//    protected int setLayoutId() {
//        return R.layout.fragment_violate_analysis2_piechart;
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initView() {
//        pieChart=(PieChart)mView.findViewById(R.id.violate_analysis2_pie_chart);
////        pieChart.setDescription("平台上有违章车辆和没有违章车辆的统计占比");
//
//        List<String> labelList = new ArrayList<>();
//        List<Entry>  valueList = new ArrayList<>();
//
//
//        valueList.add(new Entry(71.3f,0));
//        valueList.add(new Entry(28.6f,1));
//        labelList.add("无违章："+71.3+"%");
//        labelList.add("违章："+28.6+"%");
//        PieDataSet dataSet = new PieDataSet(valueList,"%");
//        List<Integer>  colors = new ArrayList<>();
//        colors.add(Color.rgb(170,76,67));
//        colors.add(Color.rgb(69,114,167));
//        dataSet.setColors(colors);
//        dataSet.setValueTextSize(20f);
//        PieData pieData = new PieData(labelList,dataSet);
//        pieChart.setData(pieData);
//
//
//    }
//
//}
