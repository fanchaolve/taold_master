package com.bb.taold.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.widget.Toast;

import com.bb.taold.MyApplication;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.utils.AppManager;


/**
 * Created by zhucheng'an on 2017/9/6.
 * Package Name tld.app.tldbeta.base
 * Class Name BaseActivity
 */

public abstract class BaseActivity extends AppCompatActivity{

    public Context mContext;

    public void setContentView(int layoutResID){
        super.setContentView(layoutResID);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        this.setContentView(getLayoutId());
        //保存屏幕尺寸
        setDisplayMetrics();
        mContext = this;
        //初始化控件
        this.initView();
        //初始化listener
        this.initListener();
        //初始化数据
        this.initdata();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity();
    }

    //求屏幕尺寸
    public void setDisplayMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        MyApplication.widthPixels = dm.widthPixels;
        MyApplication.heightPixels = dm.heightPixels;
    }

    /**
     * 提示信息
     *
     * @param aFormatMsg
     * @param aMsgArgs
     */
    public void showTip(String aFormatMsg, Object... aMsgArgs) {
        String outString = String.format(aFormatMsg, aMsgArgs);
        //字符长度大于10则长提示
        int duration = (outString.length() > 10) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(mContext, outString, duration).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //// TODO: 2016/11/25  填写 你需要在底栈的页面
            return (AppManager.getInstance().processBackKey(LoginActivity.class) ?
                    true : super.onKeyDown(keyCode, event));

        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    /**
     * 布局加载
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
    public abstract void initView();


    public abstract void initListener();

    /**
     * 初始数据
     */
    public abstract void initdata();
}
