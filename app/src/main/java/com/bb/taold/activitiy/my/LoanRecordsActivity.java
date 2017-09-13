package com.bb.taold.activitiy.my;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.taold.R;
import com.bb.taold.adapter.LoanRecordsAdapter;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.widget.recyclerview.RecyclerUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class LoanRecordsActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.rv_loan_records)
    RecyclerView mRvLoanRecords;
    @BindView(R.id.swiper_refresh)
    SwipeRefreshLayout mSwiperRefresh;
    private LoanRecordsAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_loan_records;
    }

    @Override
    public void initView() {
        mTvTitle.setText("我的借款申请记录");
        mBtnBack.setVisibility(View.VISIBLE);
        final ArrayList<String> mStrings = new ArrayList<>();
        mStrings.add("1");
        mStrings.add("2");
        mStrings.add("3");
        mRecyclerAdapter = new LoanRecordsAdapter(mContext, mStrings, R.layout.item_loan_records);
        new RecyclerUtils<String>(mContext,mStrings,mRvLoanRecords,mRecyclerAdapter,mSwiperRefresh){

            @Override
            public void onLoadMore() {
                Toast.makeText(mContext, "load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRefresh() {
                Toast.makeText(mContext, "refresh", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }
    @OnClick({R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }

}
