package com.bb.taold.activitiy.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.adapter.RecordStageAdapter;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.LoanDetail;
import com.bb.taold.listener.Callexts;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class LoanDetailsActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    ImageButton mBtnBack;
    @BindView(R.id.btn_info)
    ImageButton mBtnInfo;
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.tv_loan_money)
    TextView mTvLoanMoney;
    @BindView(R.id.tv_loan_days)
    TextView mTvLoanDays;
    @BindView(R.id.tv_load_card)
    TextView mTvLoadCard;
    @BindView(R.id.rv_loan_stages)
    LRecyclerView mRvLoanStages;
    private RecordStageAdapter recordStageAdapter;
    private LRecyclerViewAdapter mRecyclerViewAdapter;
    private PostCallback<LoanDetailsActivity> postCallback;
    private String loanId;

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
        mTvTitle.setText("借款详情");
        recordStageAdapter = new RecordStageAdapter(this);
        mRecyclerViewAdapter = new LRecyclerViewAdapter(recordStageAdapter);
        mRvLoanStages.setLayoutManager(new LinearLayoutManager(this));
        mRvLoanStages.setPullRefreshEnabled(false);
        mRvLoanStages.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void initListener() {
    }

    @Override
    public void initdata() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        loanId = extras.getString("loanId");
        postCallback = new PostCallback<LoanDetailsActivity>(this) {

            @Override
            public void successCallback(Result_Api api) {
                LoanDetail loanDetail = (LoanDetail) api.getT();
                if (loanDetail != null && !isFinish()) {
                    setViewData(loanDetail);
                }
            }

            @Override
            public void failCallback() {

            }
        };
        queryRecordDetail(loanId);
    }

    private void setViewData(LoanDetail loanDetail) {
        mTvLoanMoney.setText(loanDetail.getLoanAmount());
        mTvLoanDays.setText(loanDetail.getPeriods()+"天");
        String cardNo = loanDetail.getBankNo();
        if(!TextUtils.isEmpty(cardNo)){
            cardNo = cardNo.substring(cardNo.length() - 4, cardNo.length());
        }
        mTvLoadCard.setText(loanDetail.getBankName() + "(" + cardNo + ")");
        recordStageAdapter.addAll(loanDetail.getLifeCycle());

    }

    public void queryRecordDetail(String loanId) {
        Call<Result_Api<LoanDetail>> result_apiCall = service.loan_recordDetail(loanId);
        Callexts.Unneed_sessionPost(result_apiCall, postCallback);
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
