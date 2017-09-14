package com.bb.taold.activitiy.repay;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.adapter.RepayDetailAdapter;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.RepayDetail;

import java.util.ArrayList;

import butterknife.BindView;
/**
 * Created by zhucheng'an on 2017/9/13.
 * Package Name com.bb.taold.activitiy.repay
 * Class Name RepayDetailActivity
 * 还款详情页面
 */

public class RepayDetailActivity extends BaseActivity {
    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_loanDays)
    TextView mTvLoanDays;
    @BindView(R.id.tv_yearrate)
    TextView mTvYearrate;
    @BindView(R.id.lv_repay_detail)
    ListView mLvRepayDetail;

    //接口返回还款详情结果
    private ArrayList<RepayDetail> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_repay_detail;
    }

    @Override
    public void initView() {
        //设置标题
        mTvTitle.setText("还款详情");
        //返回按钮设置可见
        mBtnBack.setVisibility(View.VISIBLE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

        initList();

        RepayDetailAdapter mAdapter = new RepayDetailAdapter(RepayDetailActivity.this,mList);

        mLvRepayDetail.setAdapter(mAdapter);

    }

    public void initList(){
        for(int i=0;i<10;i++){
            RepayDetail mRepay = new RepayDetail();
            mRepay.setAmount("1000");
            mRepay.setPeriod("1/12");
            mRepay.setStatus("已还清");
            mRepay.setTime("2017/09/20 17:20:18");
            mList.add(mRepay);
        }

    }
}
