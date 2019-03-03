package mad.com.its02.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mad.com.its02.MainActivity;
import mad.com.its02.R;
import mad.com.its02.util.Util;


public class UserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private MainActivity mMainActivity;
    private String urlHttp;

    private TextView describTvTitle;
    private TextView describTvInterfaceUrl;
    private TextView describTvInterfaceParameter;
    private TextView describTvContent;
    private Spinner carSpinner;
    private Button queryBt;
    private Button backBt;
    private TextView resultTv;
    private List<String> data_list;

    private ArrayAdapter<String> arr_adapter;
    private int carNumber=1;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    /**
     * 描述：初始化控件
     */
    private  void initView(){
        mMainActivity = (MainActivity) getActivity();

        describTvTitle = (TextView)getActivity().findViewById(R.id.trafic_textViewDescribe1);
        describTvInterfaceUrl = (TextView)getActivity().findViewById(R.id.trafic_textViewDescribe2);
        describTvInterfaceParameter = (TextView)getActivity().findViewById(R.id.trafic_textViewDescribe3);
        describTvContent = (TextView)getActivity().findViewById(R.id.trafic_textViewDescribe4);
        carSpinner = (Spinner)getActivity().findViewById(R.id.trafic_spinner);
        queryBt = (Button) getActivity().findViewById(R.id.trafic_query);
        resultTv = (TextView) getActivity().findViewById(R.id.trafic_result);

    }

    /**
     * 描述：初始化数据
     */
    private  void initData(){
        urlHttp = Util.loadSetting(getContext());

        describTvTitle.setText("查询道路状态");
        describTvInterfaceUrl.setText(urlHttp + "transportservice/type/jason/action/GetRoadStatus.do");
        describTvInterfaceParameter.setText("{\"RoadID \": ID }");
        describTvContent.setText( "成功\n" +
                "{\"Status\":xx} ：Status ：表示道路状态，拥挤状态（ 1，2，3，4，5），如果返回其它值表示查询失败 .\n" +
                "失败\n" +
                "{'result':'failed'}");

        //数据
        data_list = new ArrayList<String>();
        data_list.add("1");
        data_list.add("2");
        data_list.add("3");
        data_list.add("4");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        carSpinner.setAdapter(arr_adapter);

        queryBt.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                carNumber = Integer.parseInt( carSpinner.getSelectedItem().toString());
                getData(carNumber);
            }

        });

    }

    /**
     * @param carNumber
     */
    private void getData(int carNumber){
        final String strUrl;
        strUrl = urlHttp + "transportservice/type/jason/action/GetRoadStatus.do";
        String strJson = "{\"RoadId\":" + Integer.valueOf(carNumber) + "}";
        describTvInterfaceParameter.setText( strJson );

        mad.com.its02.httppost.HttpThread jsonThread = new mad.com.its02.httppost.HttpThread(getContext(), mHandler);
        jsonThread.setUrl(strUrl);
        jsonThread.setJsonstring(strJson);
        jsonThread.start();
    }

    /**
     *
     */
    android.os.Handler mHandler = new android.os.Handler() {
        public void handleMessage(Message msg) {

            resultTv.setText( "" );
            // if (msg.what == 1) {
            if (msg.what == 1 || msg.what == 901) {
                resultTv.setText( (String) msg.obj );
            }
        }
    };
}
