package com.bb.taold.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bb.taold.MyApplication;
import com.bb.taold.api.ApiService;
import com.bb.taold.api.RetrofitFactory;
import com.bb.taold.base.m.BaseModel;
import com.bb.taold.base.p.BasePresenter;
import com.bb.taold.base.v.BaseView;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.InstanceUtil;
import com.bb.taold.utils.LoadingUtil;
import com.bb.taold.utils.PermissionUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class
BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity
        implements BaseView {


    public P mPresenter;
    public Context mContext;
    private Unbinder unbinder;

    private boolean isCheck = true;//检测是否第一次
    public ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);

    protected PermissionUtil permissionUtil = PermissionUtil.getInstance();

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        this.setContentView(getLayoutId());
        setDisplayMetrics();
        unbinder = ButterKnife.bind(this);
        mContext = this;
        mPresenter = InstanceUtil.getInstance(this, 0);
        this.initView();
        this.initListener();
        if (this instanceof BaseView && mPresenter != null)
            mPresenter.setVM(this, InstanceUtil.getInstance(this, 1));
        this.initdata();


    }

    @Override
    protected void onStart() {
        super.onStart();

    }




    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDetached();
        if (unbinder != null)
            unbinder.unbind();
        AppManager.getInstance().finishActivity(this);

    }

    /**
     * 提示信息
     *
     * @param aFormatMsg
     * @param aMsgArgs
     */
    public void showTip(String aFormatMsg, Object... aMsgArgs) {
        String outString = String.format(aFormatMsg, aMsgArgs);
        int duration = (outString.length() > 10) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(mContext, outString, duration).show();
    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            //// TODO: 2016/11/25  填写 你需要在底栈的页面
//            return (AppManager.getInstance().processBackKey(LoginActivity.class) ?
//                    true : super.onKeyDown(keyCode, event));
//
//        } else {
//            return super.onKeyDown(keyCode, event);
//        }
//    }

    //求屏幕尺寸
    public void setDisplayMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        MyApplication.widthPixels = dm.widthPixels;
        MyApplication.heightPixels = dm.heightPixels;
    }

    /**
     * 动态的设置状态栏  实现沉浸式状态栏
     */
    public void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);

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


    @Override
    public void showMsg(String msg) {
        showTip(msg);
    }

    @Override
    public void initLoading() {

    }

    @Override
    public void showLoading() {
        LoadingUtil.showLoading(this);
    }

    @Override
    public void dissmissLoading() {
        LoadingUtil.hideLoading();
    }

    @Override
    public void updateLoading() {

    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
