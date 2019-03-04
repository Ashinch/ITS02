package mad.com.its02.utils;

import android.content.Context;
import android.widget.Toast;

//  自定义消息框
public class MyToast {
    //  短消息
    public static void getToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    //  长消息
    public static void getToastLong(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
