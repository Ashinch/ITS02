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
import android.widget.TextView;

import java.util.List;

import mad.com.its02.MainActivity;
import mad.com.its02.R;
import mad.com.its02.util.Util;


/**
 *
 */
public class SubwayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters


    private MainActivity mMainActivity;
    private String urlHttp;

    private TextView describTvTitle;
    private TextView describTvInterfaceUrl;
    private TextView describTvInterfaceParameter;
    private TextView describTvContent;
    private Button queryBt;
    private Button queryallBt;
    private Button backBt;
    private TextView resultallTv;
    private TextView resultTv;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private int carNumber=1;
    private int iType=0;

    public SubwayFragment() {
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
        return inflater.inflate(R.layout.fragment_subway, container, false);
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMainActivity = (MainActivity) getActivity();
        urlHttp = Util.loadSetting(getContext());

        describTvTitle = (TextView)getActivity().findViewById(R.id.environment_textViewDescribe1);
        describTvInterfaceUrl = (TextView)getActivity().findViewById(R.id.environment_textViewDescribe2);
        describTvInterfaceParameter = (TextView)getActivity().findViewById(R.id.environment_textViewDescribe3);
        describTvContent = (TextView)getActivity().findViewById(R.id.environment_textViewDescribe4);
        queryBt = (Button) getActivity().findViewById(R.id.environment_query);
        resultTv = (TextView) getActivity().findViewById(R.id.environment_result);

        queryallBt = (Button) getActivity().findViewById(R.id.environmentall_query);
        resultallTv = (TextView) getActivity().findViewById(R.id.environmentall_result);


        describTvTitle.setText("查询“所有传感器”的当前值,以及光照传感器阀值");
        describTvInterfaceUrl.setText(urlHttp + "transportservice/type/jason/action/GetAllSense.do\n"+
                urlHttp + "transportservice/type/jason/action/GetLightSenseValve.do");
        describTvInterfaceParameter.setText("无");
        describTvContent.setText( " 成功\n" +
                "{\"pm2.5\":4,\"co2\":813,\"temp\":19,\"LightIntensity\":0,\"humidity\":40}\n" +
                "失败失败\n" +
                "{ 'result':'failed'}\n" +
                "\n" +
                "成功\n" +
                "{\"Down\":\"xxxx\",\" Up\":\" xxxxxxxxxxxxxxxx \"}\n" +
                "失败\n" +
                "{ 'result':'failed'} " );



        queryBt.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                String strUrl = urlHttp + "transportservice/type/jason/action/GetAllSense.do";
                iType = 0;
                getDate( strUrl);
            }

        });

        queryallBt.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                String strUrl = urlHttp + "transportservice/type/jason/action/GetLightSenseValve.do";
                iType = 1;
                getDate( strUrl);
            }

        });



    }

    /**
     * @param strUrl
     */
    private void getDate(  String strUrl ){
//        final String strUrl;
        String strJson = "";
        describTvInterfaceParameter.setText( strJson );

        System.out.println("urlDebug-url为：" + strUrl);
        System.out.println("urlDebug-strJson为：" + strJson);
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
            // if (msg.what == 1) {
            if (msg.what == 1 || msg.what == 901) {
                if ( iType == 0){
                    resultTv.setText( "" );
                    resultTv.setText( (String) msg.obj );
                }else{
                    resultallTv.setText( "" );
                    resultallTv.setText( (String) msg.obj );
                }
            }
        }
    };

}
