package com.bb.taold.activitiy.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.taold.events.LoginEvent;
import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.activitiy.HomeActivity;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.Location;
import com.bb.taold.bean.Session;
import com.bb.taold.bean.UserInfo;
import com.bb.taold.listener.Callexts;
import com.bb.taold.listener.exts.Act1;
import com.bb.taold.utils.AppManager;
import com.bb.taold.utils.CacheUtils;
import com.bb.taold.utils.Constants;
import com.bb.taold.utils.DeviceUtils;
import com.bb.taold.utils.PermissionUtil;
import com.bb.taold.utils.PreferenceUtil;
import com.bb.taold.utils.gps.GPSUtil;
import com.igexin.sdk.PushManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_getcode)
    TextView mTvGetcode;
    @BindView(R.id.tv_confirm)
    TextView mTvConfirm;

    //定时器
    public Timer mTimer;
    //定时器时间
    public int countLen = 60;

    private PostCallback postCallback;//接口返回接受

    private String mobile_bding_code;//获取验证码之后的手机号码

    private PermissionUtil.onPermissionGentedListener listener;   //权限获取

    private int home_tab = 0;//首页卡片


    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        String phone = PreferenceUtil.getSharedPreference(mContext, PreferenceUtil.PHONE);
        if (!TextUtils.isEmpty(phone)) {
            mEtMobile.setText(phone);
            mTvConfirm.setAlpha(1f);
            mTvConfirm.setClickable(true);
        } else {
            mTvConfirm.setAlpha(0.6f);
            mTvConfirm.setClickable(false);
        }
        //设置"立即登录"按钮的背景透明度

        //设置editText监听
        mEtMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //判断验证码是否为空
                if (!(TextUtils.isEmpty(mEtCode.getText().toString()) || TextUtils.isEmpty(mEtMobile.getText().toString()))) {
                    mTvConfirm.setAlpha(1f);
                    mTvConfirm.setClickable(true);
                } else {
                    mTvConfirm.setAlpha(0.6f);
                    mTvConfirm.setClickable(false);
                }
            }
        });

        mEtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //判断验证码是否为空
                if (!(TextUtils.isEmpty(mEtCode.getText().toString()) || TextUtils.isEmpty(mEtMobile.getText().toString()))) {
                    mTvConfirm.setAlpha(1f);
                    mTvConfirm.setClickable(true);
                } else {
                    mTvConfirm.setAlpha(0.6f);
                    mTvConfirm.setClickable(false);
                }
            }
        });

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        //6.0以下系统在欢迎页未同意权限 则在登录按钮时再次获取定位
        GPSUtil.tryStart(new Act1<Location>() {
            @Override
            public void run(Location location) {
                if (location != null) {
                    MyApplication.latitude = location.latitude + "";
                    MyApplication.longitude = location.lontitude + "";
                    GPSUtil.tryStop();
                }
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Bundle b = intent.getExtras();
            if (b != null && b.containsKey("card")) {
                home_tab = b.getInt("card");
            }
        }


        MyApplication.getInstance().saveSession("");
        postCallback = new PostCallback<BaseActivity>(this) {

            @Override
            public void successCallback(Result_Api api) {
                if (api.getT() instanceof String) {//验证码
                    showTip("验证码已发送");
                    return;
                }

                if (api.getT() instanceof Session) {//登录
                    Session session = (Session) api.getT();
                    if (session != null) {
                        MyApplication.getInstance().saveSession(session.getSession());
                        PreferenceUtil.saveSharedPreference(mContext, PreferenceUtil.PHONE, mEtMobile.getText().toString());
                        //成功后，获取用户信息
                        Call<Result_Api<UserInfo>> call = service.user_info();
                        Callexts.need_sessionPost(call, postCallback);
                    }
                } else if (api.getT() instanceof UserInfo) {
                    UserInfo info = (UserInfo) api.getT();
                    EventBus.getDefault().post(new LoginEvent(true));
                    PushManager.getInstance().bindAlias(LoginActivity.this, info.getId() + "");


                    Bundle bundle = new Bundle();
                    bundle.putInt("card", home_tab);
                    AppManager.getInstance().showActivity(HomeActivity.class, bundle);

                    //缓存用户信息，以便该登录用户全局使用。下次登录覆盖更新。
                    if (info != null) {
                        CacheUtils.saveDataToDiskLruCache(Constants.USER_INFO, info);
                    }
                    finish();
                }

            }

            @Override
            public void failCallback() {

            }
        };
    }


    /**
     * 请求登录时判断条件
     */
    public void doSubmit() {
        //判断手机号是否填写
        if (TextUtils.isEmpty(mEtMobile.getText().toString())) {
            ToastView("请先填写手机号");
            return;
        }
        //判断验证码是否填写
        if (TextUtils.isEmpty(mEtCode.getText().toString())) {
            ToastView("请先填写验证码");
            return;
        }
        //判断手机号格式是否正确
        String etMobileStr = mEtMobile.getText().toString();
        if (!isverTel(etMobileStr)) {
            ToastView("请输入正确的手机号");
            return;
        }
        //判断短信验证码是否正确
        if (mEtCode.getText().length() != 4) {
            ToastView("请输入正确的短信验证码");
            return;
        }

        Call<Result_Api<Session>> call = service.user_login(
                etMobileStr, mEtCode.getText().toString(), MyApplication.longitude + "-" + MyApplication.latitude,
                "android", DeviceUtils.getDeviceIdentification(this),
                DeviceUtils.getCurrVersionCode(this) + "", DeviceUtils.getManufacturer(),
                DeviceUtils.getModel(), "TL", DeviceUtils.isRoot() + "", DeviceUtils.getNetworkStateName(mContext));
        Callexts.Unneed_sessionPost(call, postCallback);


    }

    /**
     * 验证码点击后效果
     */
    public void changeCode() {
        mTimer = new Timer();
        //定时器时间
        countLen = 60;
        //设置定时器
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {      // UI thread
                    @Override
                    public void run() {
                        countLen--;
                        mTvGetcode.setClickable(false);
                        mTvGetcode.setText(countLen + "s");
                        if (countLen == 0) {
                            mTimer.cancel();
                            mTvGetcode.setText("收取验证码");
                            mTvGetcode.setClickable(true);
                        }
                    }
                });
            }
        };
        mTimer.schedule(task, 0, 1000);

        Call<Result_Api> call = service.sms_sendLoginSmsCode(mEtMobile.getText().toString().trim());
        postCallback.setFlag(0);
        mobile_bding_code = mEtMobile.getText().toString().trim();
        Callexts.Unneed_sessionPost(call, postCallback);
    }

    /**
     * 自定义Toast
     *
     * @param text
     */
    public void ToastView(String text) {
        Toast toast = null;
        LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
        View view = inflater.inflate(R.layout.dialog_login_tip, null);
        RelativeLayout rl_bgd = (RelativeLayout) view.findViewById(R.id.rl_bgd);
        rl_bgd.setBackgroundResource(R.drawable.bg_login_tip);
        rl_bgd.setAlpha(0.8f);
        TextView t = (TextView) view.findViewById(R.id.tv_tiptext);
        t.setText(text);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(LoginActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, -120);
        toast.show();
    }

    /**
     * 控件点击事件
     *
     * @param view
     */
    @OnClick({R.id.rl_close, R.id.tv_getcode, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.rl_close:
                // ToastView("测试");
                finish();
                break;
            case R.id.tv_getcode:
                //判断手机号格式
                if (TextUtils.isEmpty(mEtMobile.getText().toString()) || !isverTel(mEtMobile.getText().toString())) {
                    ToastView("手机号输入有误");
                    return;
                }
                changeCode();
                break;
            case R.id.tv_confirm:
                doSubmit();
                break;
        }
    }


    public void loginSuccess() {

    }

    /**
     * 判断手机号格式 不能有连续5为数字相同,最后4位不能是同一数字
     *
     * @param phoneNum
     * @return
     */

    public boolean isverTel(String phoneNum) {
        if (mEtMobile.getText().length() != 11) {
            //长度不为11
            return false;
        }
        //必须以13、15、17、18开头
        if (!(phoneNum.startsWith("13") || phoneNum.startsWith("15") ||
                phoneNum.startsWith("17") || phoneNum.startsWith("18"))) {
            return false;
        }
        //将字符拆分判断
        ArrayList<String> listStr;
        ArrayList<String> listChar;
        String firstStr;
        //判断5位数字是否相同
        for (int i = 0; i < 7; i++) {
            listStr = new ArrayList<>();
            listChar = new ArrayList<>();
            listStr.add(phoneNum.substring(i, i + 5));
            //判断5位数字是否相同
            for (int j = 0; j < 5; j++) {
                listChar.add(listStr.get(0).substring(j, j + 1));
            }
            firstStr = listChar.get(0);
            if (firstStr.equals(listChar.get(1)) && firstStr.equals(listChar.get(2))
                    && firstStr.equals(listChar.get(3)) && firstStr.equals(listChar.get(4))) {
                return false;
            }
            listStr = null;
            listChar = null;
        }
        //判断最后4位是否相同
        listChar = new ArrayList<>();
        for (int i = 7; i < 11; i++) {
            listChar.add(phoneNum.substring(i, i + 1));
        }
        firstStr = listChar.get(0);
        //判断最后四位数字是否相同
        for (int i = 0; i < 4; i++) {
            if (firstStr.equals(listChar.get(1)) && firstStr.equals(listChar.get(2))
                    && firstStr.equals(listChar.get(3))) {
                return false;
            }
        }
        return true;
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }
}
