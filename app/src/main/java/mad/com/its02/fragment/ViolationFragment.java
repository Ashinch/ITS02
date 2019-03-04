package mad.com.its02.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mad.com.its02.R;
import mad.com.its02.adapter.ContentAdapter;
import mad.com.its02.adapter.IClick;
import mad.com.its02.httppost.HttpThread;
import mad.com.its02.httppost.LoadingDialog;
import mad.com.its02.utils.Util;


public class ViolationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private String urlHost, urlHostAction;

    ArrayList<Map<String, Object>> arrayListItem;
    private ContentAdapter contentAdapter;
    private ListView listView;

    private String[] netAction = {
            "transportservice/type/jason/action/GetCarAccountBalance.do",
            "transportservice/type/jason/action/SetCarAccountRecharge.do"};

    //车辆数量
    int carCount = 4;
    //当前车辆号
    int carCurrent = 1;
    //网络请求标识
    int netFlag = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_violation, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        urlHost = Util.loadSetting(getContext());

        listView = (ListView) getActivity().findViewById(R.id.list_carvalue);
        initList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(), "index is"+" && menu text is "+item.getTitle(), Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }

    /**
     *
     */
    private void initList() {
        carCurrent = 1;
        arrayListItem = new ArrayList<Map<String, Object>>();
        urlHostAction = urlHost + netAction[0];
//        String strJson = "{\"CarId\":" + carCurrent + "}";
        JSONObject strJson = new JSONObject();
        try {
            strJson.put("CarId", carCurrent);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getAllCarValue(urlHostAction, strJson.toString());
    }

    /**
     * @param strUrl
     * @param strJson
     */
    private void getAllCarValue(String strUrl, String strJson) {

        netFlag = 0;
        HttpThread jsonThread = new HttpThread(getContext(), mHandler);
        jsonThread.setUrl(strUrl);
        jsonThread.setJsonstring(strJson);
        jsonThread.start();
        LoadingDialog.showDialog(getContext());
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            LoadingDialog.disDialog();
            if (msg.what == 1 || msg.what == 901) {
                switch (netFlag) {
                    case 0:
                        //获取车辆ETC余额
                        parseBalanceData(msg.obj.toString());

                        break;
                    case 1:
                        //ETC账户充值
                        parseRechargeBackData(msg.obj.toString());

                        break;
                    default:
                }
            }
        }
    };

    /**
     * @param balanceData
     */
    private void parseBalanceData( String balanceData){
        String str = null;

        try {
            JSONObject jsonObj = new JSONObject(balanceData);
            str = "第" + carCurrent + "辆车:" + jsonObj.optString("Balance");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", R.mipmap.icon_etc);                            //图片ICON
            map.put("number", "第" + carCurrent + "号车 账户余额");      //车号
            map.put("balance", jsonObj.opt("Balance").toString() + "");    //余额
            arrayListItem.add(map);

            carCurrent++;
            if (carCurrent <= carCount) {
                JSONObject strJson = new JSONObject();
                try {
                    strJson.put("CarId", carCurrent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getAllCarValue(urlHostAction, strJson.toString());

            } else {
                contentAdapter = new ContentAdapter(getContext(), arrayListItem, mListener);
                listView.setAdapter(contentAdapter);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            Log.v("zms", "e:" + e.toString());
            e.printStackTrace();
        }


    }

    /**
     * @param rechargeBackData
     */
    private void parseRechargeBackData( String rechargeBackData){
        try {
            JSONObject jsonObj = new JSONObject(rechargeBackData);
            if (jsonObj.optString("result").toString().equals("ok")) {
                Toast.makeText(getContext(), "充值成功！", Toast.LENGTH_LONG).show();
                initList();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 实现类，响应按钮点击事件
     */
    private IClick mListener = new IClick() {
        @Override
        public void listViewItemClick(int position, View v) {
            Toast.makeText(
                    getContext(),
                    "listview的内部的按钮被点击了！，位置是-->" + position , Toast.LENGTH_SHORT)
                    .show();
            reChange(position + 1);
        }
    };

    /**
     * @param position
     */
    public void reChange(final int position) {

        final Dialog rechargeDialog = new Dialog(getActivity());
        rechargeDialog.show();
        rechargeDialog.setTitle("充值窗口");
        rechargeDialog.getWindow().setContentView(R.layout.car_recharge);
        final EditText edit_car_recharge = (EditText) rechargeDialog.getWindow().findViewById(R.id.car_recharge);
        Button save = (Button) rechargeDialog.getWindow().findViewById(R.id.save);
        Button cancel = (Button) rechargeDialog.getWindow().findViewById(R.id.cancel);
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String carMoney = edit_car_recharge.getText().toString();
                urlHostAction = urlHost + netAction[1];
//                String strJson = "{\"CarId\":" + position + ",\"Money\":"+ Integer.parseInt( edit_car_recharge.getText().toString()) +"}";
                JSONObject strJson = new JSONObject();
                try {
                    strJson.put("CarId", position);
                    strJson.put("Money", Integer.parseInt(edit_car_recharge.getText().toString()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                carRechange(urlHostAction, strJson.toString());

                rechargeDialog.dismiss();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                rechargeDialog.dismiss();
            }
        });
        rechargeDialog.show();
    }

    /**
     * @param strUrl
     * @param strJson
     */
    private void carRechange(String strUrl, String strJson) {
        netFlag = 1;
        HttpThread jsonThread = new HttpThread(getContext(), mHandler);
        jsonThread.setUrl(strUrl);
        jsonThread.setJsonstring(strJson);
        jsonThread.start();
    }

}
