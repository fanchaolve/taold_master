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
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.LoadRecordResponse;
import com.bb.taold.bean.LoanRecord;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.GsonUtils;
import com.bb.taold.widget.recyclerview.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

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
    private PostCallback postCallback;
    private List<LoanRecord> mStrings;

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
        mStrings = new ArrayList<>();
        mRecyclerAdapter = new LoanRecordsAdapter(mContext, mStrings, R.layout.item_loan_records);
        new RecyclerUtils<LoanRecord>(mContext, mStrings, mRvLoanRecords, mRecyclerAdapter, mSwiperRefresh) {

            @Override
            public void onLoadMore() {
                queryLoanRecords("0", mStrings.size() + "");
                Toast.makeText(mContext, "load", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRefresh() {
                mStrings.clear();
                mRecyclerAdapter.notifyDataSetChanged();
                queryLoanRecords("0", mStrings.size() + "");
                Toast.makeText(mContext, "refresh", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

        postCallback = new PostCallback<LoanRecordsActivity>(this) {
            @Override
            public void successCallback(Result_Api api) {
                LoadRecordResponse loadRecordResponse = (LoadRecordResponse) api.getT();
                List<LoanRecord> rows = loadRecordResponse.getRows();
                if (rows != null && rows.size() > 0) {
                    mStrings.addAll(rows);
                    mRecyclerAdapter.notifyDataSetChanged();
                    Toast.makeText(mContext, rows.size() + "" + GsonUtils.toJson(rows.get(0)).toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failCallback() {

            }
        };
        queryLoanRecords("0", "1");
    }

    public void queryLoanRecords(String offset, String count) {
        Call<Result_Api<LoadRecordResponse>> call = service.loan_recordList(offset, count);
        Callexts.Unneed_sessionPost(call, postCallback);
    }

    @OnClick({R.id.btn_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

}
