package com.bb.taold.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.activitiy.HomeActivity;
import com.bb.taold.activitiy.login.LoginActivity;
import com.bb.taold.fragment.RepayFragment;
import com.bb.taold.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fancl 自定义底部导航条.
 */

public class BottomBar extends LinearLayout implements View.OnClickListener {


    private Context mContext;

    private ChangeListener listener;


    private int currentTab = -1;

    @BindView(R.id.tab1)
    LinearLayout tab1;

    @BindView(R.id.tab2)
    LinearLayout tab2;

    @BindView(R.id.tab3)
    LinearLayout tab3;


    @BindView(R.id.ivTab1)
    ImageView ivTab1;


    @BindView(R.id.ivTab2)
    ImageView ivTab2;

    @BindView(R.id.ivTab3)
    ImageView ivTab3;

    @BindView(R.id.tvTab1)
    TextView tvTab1;


    @BindView(R.id.tvTab2)
    TextView tvTab2;

    @BindView(R.id.tvTab3)
    TextView tvTab3;


    public BottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public BottomBar(Context context) {
        this(context, null, 0);
    }

    private void initView() {
        // View bar = View.inflate(mContext, R.layout.bottom_bar, this);

        LinearLayout bar = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.bottom_bar, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(bar, lp);
        ButterKnife.bind(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tab1:
                changeTab(0);
                break;
            case R.id.tab2:
                changeTab(1);
                break;
            case R.id.tab3:
                changeTab(2);
                break;
        }
    }

    private void setbottombcdefail() {

        //图片背景色设置
        ivTab1.setImageResource(R.drawable.btn_loan_nor);
        ivTab2.setImageResource(R.drawable.btn_bill_nor);
        ivTab3.setImageResource(R.drawable.btn_mine_nor);

        //文字背景色设置
        tvTab1.setTextColor(getResources().getColor(R.color.color_hint));
        tvTab2.setTextColor(getResources().getColor(R.color.color_hint));
        tvTab3.setTextColor(getResources().getColor(R.color.color_hint));

    }


    public void changeTab(int tab) {
        if (currentTab != tab) {
            setbottombcdefail();
            switch (tab) {
                case 0:
                    ivTab1.setImageResource(R.drawable.btn_loan_sel);
                    tvTab1.setTextColor(getResources().getColor(R.color.black));
                    break;
                case 1:

                    if(mContext instanceof HomeActivity){
                        if(AppManager.getInstance().isLogin()){
                            ivTab2.setImageResource(R.drawable.btn_bill_sel);
                            tvTab2.setTextColor(getResources().getColor(R.color.black));
                        }else{
                            AppManager.getInstance().showActivity(LoginActivity.class,null);
                            return;
                        }
                    }

                    break;
                case 2:
                    ivTab3.setImageResource(R.drawable.btn_mine_sel);
                    tvTab3.setTextColor(getResources().getColor(R.color.black));
                    break;

                default:
                    break;
            }
            if (listener != null) {
                listener.changeTab2(tab);

            }
            currentTab = tab;
        }
    }


    public interface ChangeListener {

        void changeTab2(int tab);

    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }


}
