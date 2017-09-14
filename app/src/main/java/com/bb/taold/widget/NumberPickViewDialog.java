package com.bb.taold.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;


/**
 * ==============================================
 * <p>
 * 包名：
 * <p>
 * 说明：数字滚轮弹出框控件
 * 自定义 底部效果
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/4
 * <p>
 * ==============================================
 */

public class NumberPickViewDialog extends Dialog implements
        NumberPickerView.OnScrollListener, NumberPickerView.OnValueChangeListener{

    private Context mContext;

    private NumberPickerView mNumberPickerView;//滚轮控件
    private String[] mDisplayValues;
    private TextView negativeButton;//取消
    private TextView positiveButton;//确定



    public NumberPickViewDialog(Context context) {
        super(context, R.style.processDialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_number_picker);
        setWindow();
        mNumberPickerView = (NumberPickerView) this.findViewById(R.id.picker);
        mNumberPickerView.setOnScrollListener(this);
        mNumberPickerView.setOnValueChangedListener(this);
        negativeButton= (TextView) findViewById(R.id.negativeButton);
        positiveButton= (TextView) findViewById(R.id.positiveButton);


    }

    private void  setWindow(){
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = MyApplication.widthPixels; // 设置宽度
        lp.y = 0;//距离底部的高度
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.dialog_in_and_out);  //添加动画

    }

    //给滚轮填充数据
    public void initData(String[] mDisplayValues){
        this.mDisplayValues=mDisplayValues;
        mNumberPickerView.refreshByNewDisplayedValues(mDisplayValues);
    }



    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {
        String[] content = picker.getDisplayedValues();
        Log.i("fancl",content[newVal - picker.getMinValue()]);

    }

    //当前内容
    public String getCurrentContent(){
        String[] content = mNumberPickerView.getDisplayedValues();
        if (content != null){
           return content[getCurrentIndex() - mNumberPickerView.getMinValue()];
        }

        return null;
    }

    //当前下标
    public int getCurrentIndex(){

        return mNumberPickerView.getValue();
    }

    @Override
    public void onScrollStateChange(NumberPickerView view, int scrollState) {

    }

    public void setOnNegativeListener(View.OnClickListener paramOnClickListener)
    {
        this.negativeButton.setOnClickListener(paramOnClickListener);
    }

    public void setOnPositiveListener(View.OnClickListener paramOnClickListener)
    {
        this.positiveButton.setOnClickListener(paramOnClickListener);
    }
    public NumberPickerView getNumberPickerView(){
        return this.mNumberPickerView;
    }


}
