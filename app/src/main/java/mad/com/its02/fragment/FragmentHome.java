/**
 *
 */
package mad.com.its02.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import mad.com.its02.R;
import mad.com.its02.httppost.HttpThread;
import mad.com.its02.httppost.LoadingDialog;


/**
 * @author zhaowei
 *	asdf
 */
public class FragmentHome extends Fragment
{
    private Button btQueryDefaultHttpClient,btQueryVolley,btQueryAsync;
    private TextView twInfo;

    private TextView twResult;
    private String strUrl;
    private String strJson;
    private EditText etUrl;
    private EditText etPort;

	JSONObject params;
	String stringJson3 = "[{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:02:44\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:02:58\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:04:39\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:04:50\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:06:31\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:06:42\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:08:23\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:08:34\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:10:15\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:10:26\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:12:07\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:12:18\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:13:59\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:14:11\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:16:04\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:16:05\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:17:43\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:18:00\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:19:19\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:19:52\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:20:55\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:21:44\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:22:31\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:23:36\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:24:07\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:25:28\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:25:43\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:27:20\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:27:32\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:29:12\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:29:24\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:31:04\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:31:16\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:32:57\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:33:08\\\"}\",\"{\\\"Addr\\\":\\\"parkout\\\",\\\"Cost\\\":5,\\\"CarId\\\":1,\\\"Time\\\":\\\"2017-04-17  12:34:49\\\"}\",\"{\\\"Addr\\\":\\\"etcout\\\",\\\"Cost\\\":5,\\\"CarId\\\":0,\\\"Time\\\":\\\"2017-04-17  12:35:00\\\"}]";



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_home, container, false);
		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



		twResult = (TextView) view.findViewById(R.id.textview_result);
		etUrl = (EditText) view.findViewById(R.id.edit_eg_url);
		etPort = (EditText) view.findViewById(R.id.edit_eg_port);
		twResult.setText(R.string.info_result);

		twInfo = (TextView) view.findViewById(R.id.textview_info);
		twInfo.setText(R.string.info_app);

		return view;
	}

    /* (non-Javadoc)
     * @see android.support.v4.app.Fragment#onActivityCreated(android.os.Bundle)
     */
    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

	/**
	* @Title: initView
	* @Description: TODO
	* @return void
	* @throws
	*/
	private void initView() {

		btQueryDefaultHttpClient =(Button) getActivity().findViewById(R.id.button_query_DefaultHttpClient);
		btQueryVolley =(Button) getActivity().findViewById(R.id.button_query_Volley);

		btQueryDefaultHttpClient.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	queryDefaultHttpClient();
            }
        });
		btQueryVolley.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	queryVolley();
            }
        });

		strUrl = "http://"+ etUrl.getText() +":" +etPort.getText() + "/transportservice/type/jason/action/GetCarAccountBalance.do";
//		strJson = "{\"CarId\":" + Integer.valueOf("1") + "}";
		strJson = "{\"CarId\":4}";
//		strJson = "{\"TrafficLightId\":" + "1-1" + "}";


		params = new JSONObject();
		try {
			params.put("CarId", 1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void queryDefaultHttpClient(){

//        strJson = "{\"RoadId\":" + Integer.valueOf("13") + "}";

        System.out.println("start url:" + strUrl);
        System.out.println("start strJson:" + strJson);

		HttpThread jsonThread = new HttpThread(getActivity(), mHandler);
        jsonThread.setUrl(strUrl);
        jsonThread.setJsonstring(strJson);
        jsonThread.start();
		LoadingDialog.showDialog(getActivity());


	}

	/**
	* @Title: queryVolley
	* @Description: TODO
	* @return void
	* @throws
	*/
	private void queryVolley(){

		LoadingDialog.showDialog(getActivity());

		RequestQueue mQueue = Volley.newRequestQueue(getActivity());
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.POST, strUrl, params, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				// TODO Auto-generated method stu
				Log.d("TAG", response.toString());
				String str = response.toString();
				resultSettext(str);

				LoadingDialog.disDialog();
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				LoadingDialog.disDialog();
			}
		});
		mQueue.add(jsonObjectRequest);


	}

	/**
	 *
	 */
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
        	twResult.setText( "" );
			LoadingDialog.disDialog();

			if (msg.what == 1 || msg.what == 901) {
                Log.i("result---  1",(String) msg.obj);

//				ArrayList<Map<String, Object>> arrayListItem = new ArrayList<Map<String, Object>>();
//				JSONArray jsonArrayItem = null;
//				try {
//					Log.i("result--- :", "1");
//					jsonArrayItem = new JSONArray(stringJson3);
//					for (int i = 0; i < jsonArrayItem.length();i++) {
//						Log.i("result--- :", ""+i);
//						JSONObject jsonObject = jsonArrayItem.optJSONObject(i);
//						Map<String, Object> map = new HashMap<String, Object>();
//						map.put("Addr", jsonObject.optInt("Addr"));
//						map.put("Cost", jsonObject.optInt("Cost"));
//						map.put("CarId", jsonObject.optInt("CarId"));
//						map.put("Time", jsonObject.optInt("Time"));
//						arrayListItem.add(map);
//					}
//				} catch (JSONException e) {
//					e.printStackTrace();
//					Log.i("result--- e:", e.toString());
//				}
//
//				Log.i("result--- map:", arrayListItem.toString());
            	twResult.setText( (String) msg.obj );
            }
        }
    };

    /**
    * @Title: resultSettext
    * @Description: TODO
    * @param @param stContent
    * @return void
    * @throws
    */
    private void resultSettext(String stContent) {
    	twResult.setText( "" );
        Log.i("result---:",stContent);
    	twResult.setText( stContent );
	}

}
