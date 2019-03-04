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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mad.com.its02.activity.MainActivity;
import mad.com.its02.R;
import mad.com.its02.utils.Util;


/**
 *
 */
public class ShuttleBusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters


    private MainActivity mMainActivity;
    private String urlHttp;

    private TextView describTvTitle;
    private TextView describTvInterfaceUrl;
    private TextView describTvInterfaceParameter;
    private TextView describTvContent;
    private Button settingBt;
    private Button queryRateBt;
    private Button queryPlaceBt;
    private Button backBt;
    private TextView resulSettingTv;
    private TextView resultQueryTv;
    private TextView resultPalceTv;
    private Spinner parkingTypeSpinner;
    private EditText parkingTypeValueEt;

    private int iType=0;
    private ArrayAdapter<String> arr_adapter;
    private List<String> mDataTypeName;
    private List<String> mDataTypeValue;

    public ShuttleBusFragment() {
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
        return inflater.inflate(R.layout.fragment_shuttle_bus, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMainActivity = (MainActivity) getActivity();
        urlHttp = Util.loadSetting(getContext());

        describTvTitle = (TextView)getActivity().findViewById(R.id.parking_textViewDescribe1);
        describTvInterfaceUrl = (TextView)getActivity().findViewById(R.id.parking_textViewDescribe2);
        describTvInterfaceParameter = (TextView)getActivity().findViewById(R.id.parking_textViewDescribe3);
        describTvContent = (TextView)getActivity().findViewById(R.id.parking_textViewDescribe4);

        parkingTypeSpinner = (Spinner)getActivity().findViewById(R.id.parking_spinner);
        parkingTypeValueEt = (EditText) getActivity().findViewById(R.id.parking_money_edittext);
        settingBt = (Button) getActivity().findViewById(R.id.parking_setting_button);
        resulSettingTv = (TextView) getActivity().findViewById(R.id.parking_setting_result);

        queryRateBt = (Button) getActivity().findViewById(R.id.parking_rate_query);
        resultQueryTv = (TextView) getActivity().findViewById(R.id.parking_rate_result);

        queryPlaceBt = (Button) getActivity().findViewById(R.id.parking_place_query);
        resultPalceTv = (TextView) getActivity().findViewById(R.id.parking_place_result);

        describTvTitle.setText("停车场：设置费率、查询费率和车位数量");
        describTvInterfaceUrl.setText(urlHttp + "transportservice/type/jason/action/SetParkRate.do\n"+
                urlHttp + "transportservice/type/jason/action/GetParkRate.do\n"+
                urlHttp + "transportservice/type/jason/action/GetParkFree.do"  );
        describTvInterfaceParameter.setText("");
        describTvContent.setText( " " );


        //数据
        mDataTypeName = new ArrayList<String>();
        mDataTypeName.add("按小时计费");
        mDataTypeName.add("按次计费路");

        mDataTypeValue = new ArrayList<String>();
        mDataTypeValue.add("Hour");
        mDataTypeValue.add("Count");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, mDataTypeName);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        parkingTypeSpinner.setAdapter(arr_adapter);


        settingBt.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                String strUrl = urlHttp + "transportservice/type/jason/action/SetParkRate.do";
                iType = 0;
                String parkingType = mDataTypeValue.get( Integer.parseInt(String.valueOf(parkingTypeSpinner.getSelectedItemId())));
                String parkingValue =parkingTypeValueEt.getText().toString();
                String strJson = "{\"RateType\":\"" + parkingType +"\",\"Money\":" + parkingValue +"}";

                resulSettingTv.setText( "" );
                getDate( strUrl , strJson);
            }
        });

        queryRateBt.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                String strUrl = urlHttp + "transportservice/type/jason/action/GetParkRate.do";
                iType = 1;
                String strJson = "";
                resultQueryTv.setText( "" );
                getDate( strUrl , strJson);
            }
        });

        queryPlaceBt.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                String strUrl = urlHttp + "transportservice/type/jason/action/GetParkFree.do";
                iType = 2;
                String strJson = "";
                resultPalceTv.setText( "" );
                getDate( strUrl , strJson);
            }
        });

    }

    /**
     * @param strUrl
     */
    private void getDate(String strUrl , String strJson){
//        final String strUrl;
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
                    resulSettingTv.setText( (String) msg.obj );
                }else if( iType == 1) {
                    resultQueryTv.setText( (String) msg.obj );
                }else if( iType == 2) {
                    resultPalceTv.setText( (String) msg.obj );
                }
            }
        }
    };

}
