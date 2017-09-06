package com.bb.taold.utils;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.listener.DialogListener;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.utils2
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/8/1
 * <p>
 * ==============================================
 */

public class DialogUtils {

    /**
     * 弹出选择对话框-强制更新
     *
     */
    public static void showMsgDialogForce(final Activity activity, final String title, final String content, final DialogListener listener) {
        final Dialog dialog = new Dialog(activity, R.style.myDialogTheme);
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_msg, null);
        layout.findViewById(R.id.tvConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.onConfirm(dialog);
            }
        });
        TextView titleMain = (TextView)layout.findViewById(R.id.tvMainTitle);
        TextView tvTitle = (TextView) layout.findViewById(R.id.tvTitle);
        titleMain.setText(title);
        tvTitle.setText(content);

        dialog.setContentView(layout);

        // 设置对话框的出现位置，借助于window对象
        Window win = dialog.getWindow();
        win.setGravity(Gravity.CENTER);
        // win.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//
        // 弹出对话框时，底部窗体，不变暗。

        WindowManager.LayoutParams lp = win.getAttributes();
        // lp.x = -200;// x=0,y=0时，显示位置是屏幕中心。
        // lp.y = 0;
        // lp.alpha = 0.6f;// 对话框的透明度
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = activity.getWindowManager();
        manager.getDefaultDisplay().getMetrics(metrics);

        lp.width = (int)(metrics.widthPixels * 0.8);
        win.setAttributes(lp);
//		win.setWindowAnimations(R.style.dialoganimstyle);
        dialog.setCancelable(false);



        dialog.show();
    }

    /**
     * 弹出选择对话框-非强制更新
     *
     */
    public static void showMsgDialog(final Activity activity, final String content, final DialogListener listener) {
        final Dialog dialog = new Dialog(activity, R.style.myDialogTheme);
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialog_msg1, null);
        layout.findViewById(R.id.tvConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.onConfirm(dialog);
            }
        });
        layout.findViewById(R.id.tvCancl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                listener.onCancl(dialog);
            }
        });
        TextView tvTitle = (TextView) layout.findViewById(R.id.tvTitle);
        tvTitle.setText(content);


        dialog.setContentView(layout);

        // 设置对话框的出现位置，借助于window对象
        Window win = dialog.getWindow();
        win.setGravity(Gravity.CENTER);
        // win.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//
        // 弹出对话框时，底部窗体，不变暗。

        WindowManager.LayoutParams lp = win.getAttributes();
        // lp.x = -200;// x=0,y=0时，显示位置是屏幕中心。
        // lp.y = 0;
        // lp.alpha = 0.6f;// 对话框的透明度
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = activity.getWindowManager();
        manager.getDefaultDisplay().getMetrics(metrics);

        lp.width = (int)(metrics.widthPixels * 0.8);
        win.setAttributes(lp);
//		win.setWindowAnimations(R.style.dialoganimstyle);
        dialog.setCancelable(false);



        dialog.show();
    }

}
