package com.bb.taold.activitiy.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.taold.R;
import com.bb.taold.base.BaseActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_mobile)
    EditText mEtMobile;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_getcode)
    TextView mTvGetcode;

    //定时器
    public Timer mTimer;
    //定时器时间
    public int countLen = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }

    public void doSubmit(){
        //判断手机号是否填写
        if(mEtMobile.getText().toString().equals("")){
            showTip("请先填写手机号");
            return;
        }
        //判断验证码是否填写
        if(mEtCode.getText().toString().equals("")){
            showTip("请先填写验证码");
            return;
        }
        //判断手机号格式是否正确
        String etMobileStr = mEtMobile.getText().toString();
        if(!isPhone(etMobileStr)){
            showTip("请输入正确的手机号");
            return;
        }
        //判断短信验证码是否正确
        if(mEtCode.getText().length()!=6){
            showTip("请输入正确的短信验证码");
            return;
        }
    }

    /**
     * 判断手机号格式 不能有连续5为数字相同,最后4位不能是同一数字
     * @param phoneNum
     * @return
     */
    public boolean isPhone(String phoneNum){
        if(mEtMobile.getText().length()!=11){
            //长度不为11
            return false;
        }
        //必须以13、15、17、18开头
        if(!(phoneNum.startsWith("13") || phoneNum.startsWith("15") ||
                phoneNum.startsWith("17") || phoneNum.startsWith("18"))){
            return false;
        }
        //将字符拆分判断
        ArrayList<String> listStr;
        ArrayList<String> listChar;
        String firstStr;
        //判断5位数字是否相同
        for(int i=0;i<7;i++){
            listStr = new ArrayList<>();
            listChar = new ArrayList<>();
            listStr.add(phoneNum.substring(i,i+5));
            //判断5位数字是否相同
            for(int j=0;j<5;j++){
                listChar.add(listStr.get(i).substring(j,j+1));
            }
            firstStr = listChar.get(0);
            if(firstStr.equals(listChar.get(1)) && firstStr.equals(listChar.get(2))
                    && firstStr.equals(listChar.get(3)) && firstStr.equals(listChar.get(4))){
                return false;
            }
        }
        //判断最后4位是否相同
        listChar = new ArrayList<>();
        for(int i=7;i<11;i++){
            listChar.add(phoneNum.substring(i,i+1));
        }
        for(int i=0;i<3;i++){
            for(int j=i+1;j<4;j++){
                if(listChar.get(i).equals(listChar.get(j))){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 验证码点击后效果
     */
    public void changeCode(){
        //定时器时间
        countLen = 60;
        //设置定时器
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {      // UI thread
                    @Override
                    public void run() {
                        Log.i("定时器时间",countLen+"");
                        countLen--;
                        mTvGetcode.setClickable(false);
                        mTvGetcode.setText(countLen+"s");
                        if(countLen == 0){
                            mTimer.cancel();
                            mTvGetcode.setText("收取验证码");
                            mTvGetcode.setClickable(true);
                        }
                    }
                });
            }
        };
        mTimer.schedule(task,0,1000);
    }

    public void ToastView(String text) {
        Toast toast = null;
        LayoutInflater inflater = LayoutInflater.from(LoginActivity.this);
        View view = inflater.inflate(R.layout.dialog_login_tip, null);
        TextView t = (TextView) view.findViewById(R.id.tv_tiptext);
        t.setText(text);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(LoginActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.TOP, 0, 60);
        toast.show();
    }

    @OnClick({R.id.im_close, R.id.tv_getcode, R.id.tv_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_close:
                ToastView("测试");
                break;
            case R.id.tv_getcode:
                changeCode();
                break;
            case R.id.tv_confirm:
                doSubmit();
                break;
        }
    }
}
