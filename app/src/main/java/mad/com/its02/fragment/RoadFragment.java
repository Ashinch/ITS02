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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mad.com.its02.MainActivity;
import mad.com.its02.R;
import mad.com.its02.util.Util;


/**
 *
 */
public class RoadFragment extends Fragment {
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
    private Button queryMoneyBt;
    private Button backBt;
    private TextView resulSettingTv;
    private TextView resultQueryTv;

    private Switch speedCarSwitch;
    private Spinner speedCar1Spinner;
    private Spinner speedCar2Spinner;

    private int iType = 0;
    private ArrayAdapter<String> arr_car1_adapter;
    private ArrayAdapter<String> arr_car2_adapter;
    private List<String> data_car_list;

    private String carContrl;


    public RoadFragment() {
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
        return inflater.inflate(R.layout.fragment_road, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mMainActivity = (MainActivity) getActivity();
        urlHttp = Util.loadSetting(getContext());

        describTvTitle = (TextView) getActivity().findViewById(R.id.speed_textViewDescribe1);
        describTvInterfaceUrl = (TextView) getActivity().findViewById(R.id.speed_textViewDescribe2);
        describTvInterfaceParameter = (TextView) getActivity().findViewById(R.id.speed_textViewDescribe3);
        describTvContent = (TextView) getActivity().findViewById(R.id.speed_textViewDescribe4);

        speedCar1Spinner = (Spinner) getActivity().findViewById(R.id.speed_car1_spinner);
        speedCarSwitch = (Switch) getActivity().findViewById(R.id.switch_car);
        settingBt = (Button) getActivity().findViewById(R.id.speed_setting_button);
        resulSettingTv = (TextView) getActivity().findViewById(R.id.speed_setting_result);

        speedCar2Spinner = (Spinner) getActivity().findViewById(R.id.speed_car2_spinner);
        queryMoneyBt = (Button) getActivity().findViewById(R.id.speed_rate_query);
        resultQueryTv = (TextView) getActivity().findViewById(R.id.speed_rate_result);

        describTvTitle.setText("speed充值和查询");
        describTvInterfaceUrl.setText(urlHttp + "transportservice/type/jason/action/SetCarMove.do\n" +
                urlHttp + "transportservice/type/jason/action/GetCarSpeed.do");
        describTvInterfaceParameter.setText("");
        describTvContent.setText(" ");

        //数据
        data_car_list = new ArrayList<String>();
        data_car_list.add("1");
        data_car_list.add("2");
        data_car_list.add("3");
        data_car_list.add("4");

        carContrl = "Stop";
        speedCarSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 开启switch，设置提示信息
                    carContrl = "Start";
                } else {
                    // 关闭swtich，设置提示信息
                    carContrl = "Stop";
                }
            }
        });

        //适配器
        arr_car1_adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, data_car_list);
        //设置样式
        arr_car1_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        speedCar1Spinner.setAdapter(arr_car1_adapter);

        //适配器
        arr_car2_adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, data_car_list);
        //设置样式
        arr_car2_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        speedCar2Spinner.setAdapter(arr_car2_adapter);

        settingBt.setOnClickListener(new Button.OnClickListener() {//创建监听
            public void onClick(View v) {
                String strUrl = urlHttp + "transportservice/type/jason/action/SetCarMove.do";
                iType = 0;
                int carNumber = Integer.parseInt(speedCar1Spinner.getSelectedItem().toString());

                String strJson = "{\"CarId\":" + carNumber +",\"CarAction\":\"" + carContrl + "\" }";

                resulSettingTv.setText("");
                getDate(strUrl, strJson);
            }
        });

        queryMoneyBt.setOnClickListener(new Button.OnClickListener() {//创建监听
            public void onClick(View v) {
                String strUrl = urlHttp + "transportservice/type/jason/action/GetCarSpeed.do";
                int carNumber = Integer.parseInt(speedCar2Spinner.getSelectedItem().toString());
                String strJson = "{\"CarId\":" + carNumber + "}";
                iType = 1;

                resultQueryTv.setText("");
                getDate(strUrl, strJson);
            }
        });

    }

    /**
     * @param strUrl
     */
    private void getDate(String strUrl, String strJson) {
//        final String strUrl;
        describTvInterfaceParameter.setText(strJson);

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
                if (iType == 0) {
                    resulSettingTv.setText((String) msg.obj);
                } else if (iType == 1) {
                    resultQueryTv.setText((String) msg.obj);

                }
            }
        }

        ;

    };
}