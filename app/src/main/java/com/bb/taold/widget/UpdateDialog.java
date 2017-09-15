package com.bb.taold.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ==============================================
 * <p>
 * 包名：更新的弹出框
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/15
 * <p>
 * ==============================================
 */

public class UpdateDialog extends Dialog {

    private Context mContext;

    @BindView(R.id.tv_memo)
    TextView tv_memo;

    @BindView(R.id.tv_no)
    TextView tv_no;

    @BindView(R.id.tv_ok)
    TextView tv_ok;


    public UpdateDialog(Context context) {
        super(context, R.style.processDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.dialog_update);
        setWindow();



    }

    //配置弹出框的大小和位置
    private void  setWindow(){
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = MyApplication.widthPixels- AppManager.getInstance().dp2px(mContext,60); // 设置宽度
        window.setGravity(Gravity.CENTER);
        setCancelable(false);
    }

    /**
     * 添加提示语
     * @param memo
     */
    public void setMemo(String memo){
        tv_memo.setText(memo);
    }

    //点击回调
    public void setOnNegativeListener(View.OnClickListener paramOnClickListener)
    {
        this.tv_no.setOnClickListener(paramOnClickListener);
    }

    //点击回调
    public void setOnPositiveListener(View.OnClickListener paramOnClickListener)
    {
        this.tv_ok.setOnClickListener(paramOnClickListener);
    }

}
