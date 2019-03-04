package mad.com.its02.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.view.WindowManager;

//  对话框 Dialog
public class DialogFactory {
    static Dialog dialog = null;

    //  接口
    public interface OnListener {
        void onAfter();
    }

    /**
     * 公开
     * 显示对话框
     *
     * @param context 上下文对象
     * @param title 标题
     * @param msg 消息
     * @param oAfter
     *
     * @return 网络是否连接
     */
    public static void showDialog(Context context, String title, String msg,
                                  final OnListener oAfter) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_DARK);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setNegativeButton("取消", null);
        //  忽略按钮
        builder.setNeutralButton("忽略", null);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                oAfter.onAfter();
            }
        });
        dialog = builder.create();
        if (!dialog.isShowing()) {

            //  Dialog 透明度
            Window window = dialog.getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.alpha = 0.8f;
            window.setAttributes(lp);

            dialog.show();
        }
    }
}
