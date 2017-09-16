package com.bb.taold.activitiy.my;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.taold.R;
import com.bb.taold.adapter.LoanDetailStagesAdapter;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.widget.recyclerview.RecyclerUtils;

import java.util.ArrayList;

import butterknife.BindView;

public class LoanDetailsActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_loan_money)
    TextView mTvLoanMoney;
    @BindView(R.id.tv_loan_days)
    TextView mTvLoanDays;
    @BindView(R.id.tv_load_card)
    TextView mTvLoadCard;
    @BindView(R.id.rv_loan_stages)
    RecyclerView mRvLoanStages;
    @BindView(R.id.swiper_refresh)
    SwipeRefreshLayout mSwiperRefresh;
    private LoanDetailStagesAdapter mRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_loan_details;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {
        final ArrayList<String> mStrings = new ArrayList<>();
        mStrings.add("1");
        mStrings.add("2");
        mStrings.add("3");
        mStrings.add("3");
        mStrings.add("3");
        mStrings.add("3");
        mStrings.add("3");
        mStrings.add("3");
        mStrings.add("3");
        mRecyclerAdapter = new LoanDetailStagesAdapter(mContext, mStrings, R.layout.item_loan_stages);
        new RecyclerUtils<String>(mContext,mStrings,mRvLoanStages,mRecyclerAdapter,mSwiperRefresh){

            @Override
            public void onLoadMore() {
                for(int i=0;i<10;i++){
                    mStrings.add(i+"");
                }
                mRecyclerAdapter.notifyDataSetChanged();
                Toast.makeText(mContext, "load="+mStrings.size(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRefresh() {
                mStrings.clear();
                for(int i=0;i<10;i++){
                    mStrings.add(i+"");
                }
                mRecyclerAdapter.notifyDataSetChanged();
                Toast.makeText(mContext, "refresh", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
