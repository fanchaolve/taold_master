package com.bb.taold.utils;

import android.content.Context;

import com.bb.taold.R;
import com.bb.taold.widget.MyProgressDialog;

/**
 * ==============================================
 * <p>
 * 包名：加载框
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/19
 * <p>
 * ==============================================
 */

public class LoadingUtil {

    private static MyProgressDialog loading;
    public synchronized static void  showLoading(Context context){
        showLoading(context, "正在加载");
    }
    public synchronized static void  showLoading(Context context, String msg){
        showLoading(context, msg, R.drawable.anim_wait_load);
    }


    public synchronized static void  showLoading(Context context, String msg,int animDrawable){
        try {
            if(context == null || (loading != null && loading.isShowing())){
                return;
            }
            loading = new MyProgressDialog(context,msg,animDrawable);
            loading.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static void hideLoading(){
        try {
            if(loading != null && loading.isShowing()){
                loading.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
