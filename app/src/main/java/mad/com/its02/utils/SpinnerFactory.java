package mad.com.its02.utils;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import mad.com.its02.R;

//  下拉控件 Spinner
public class SpinnerFactory {
    //  项目选中事件接口
    public interface OnItemSelected {
        void onSelected(int position);
    }

    //  初始化
    public static void getSpinner(Context context, Spinner spinner, String[] datas,
                                  final OnItemSelected listener) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                R.layout.spinner_item, datas);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //  项目选中事件
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onSelected(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }
}
