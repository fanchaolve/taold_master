package com.bb.taold.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.taold.MyApplication;
import com.bb.taold.R;
import com.bb.taold.utils.AppManager;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhouwan on 2017/9/22.
 */

public class UtilDialog extends Dialog {
    @BindView(R.id.icon_imageView) ImageView iconImageView;
    @BindView(R.id.content_textView) TextView contentTextView;
    @BindView(R.id.tv_no) TextView tvNo;
    @BindView(R.id.tv_ok) TextView tvOk;
    private Context mContext;
    private DialogLister dialogLister;


    public UtilDialog(@NonNull Context context) {
        super(context, R.style.processDialog);
        mContext = context;
        setContentView(R.layout.dialog_util);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindow();
    }

    //配置弹出框的大小和位置
    private void setWindow() {
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = MyApplication.widthPixels - AppManager.getInstance().dp2px(mContext, 60); // 设置宽度
        window.setGravity(Gravity.CENTER);
        setCancelable(false);
    }

    public UtilDialog setMessage(String memo) {
        contentTextView.setText(memo);
        return this;
    }

    public UtilDialog setDialogLister(DialogLister lister) {
        dialogLister = lister;
        return this;
    }

    public UtilDialog setImage(@DrawableRes int imageRes) {
        iconImageView.setImageResource(imageRes);
        return this;
    }

    @OnClick({R.id.tv_no, R.id.tv_ok}) public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_no:
                dismiss();
                if (dialogLister != null) {
                    dialogLister.onCancel();
                }
                break;
            case R.id.tv_ok:
                dismiss();
                if (dialogLister != null) {
                    dialogLister.onConfirm();
                }
                break;
        }
    }

    public interface DialogLister {
        void onCancel();

        void onConfirm();

    }
}
