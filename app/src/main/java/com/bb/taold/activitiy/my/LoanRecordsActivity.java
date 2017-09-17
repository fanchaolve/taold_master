package com.bb.taold.activitiy.my;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.taold.R;
import com.bb.taold.adapter.LoanRecordsAdapter22;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.LoadRecordResponse;
import com.bb.taold.bean.LoanRecord;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.GsonUtils;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

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
//    @BindView(R.id.title)
//    RelativeLayout mTitle;
//    @BindView(R.id.rv_loan_records)
//    RecyclerView mRvLoanRecords;
//    @BindView(R.id.swiper_refresh)
//    SwipeRefreshLayout mSwiperRefresh;
//    private LoanRecordsAdapter mRecyclerAdapter;
    private PostCallback postCallback;
    private List<LoanRecord> mStrings;
    /**已经获取到多少条数据了*/
    private static int mCurrentCounter = 0;

    private LRecyclerView mRecyclerView = null;
    private LRecyclerViewAdapter recyclerViewAdapter;
    private LoanRecordsAdapter22 adapter22;

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
        mRecyclerView = (LRecyclerView) findViewById(R.id.list);
        adapter22 = new LoanRecordsAdapter22(mContext);
        recyclerViewAdapter = new LRecyclerViewAdapter(adapter22);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(recyclerViewAdapter);
//        mTvTitle.setText("我的借款申请记录");
//        mBtnBack.setVisibility(View.VISIBLE);
//        mStrings = new ArrayList<>();
//        mRecyclerAdapter = new LoanRecordsAdapter(mContext, mStrings, R.layout.item_loan_records);
//        new RecyclerUtils<LoanRecord>(mContext, mStrings, mRvLoanRecords, mRecyclerAdapter, mSwiperRefresh) {
//
//            @Override
//            public void onLoadMore() {
//                if(hasMore){
//                    queryLoanRecords("0", mStrings.size() + "");
//                    Toast.makeText(mContext, "load", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onRefresh() {
//                mStrings.clear();
//                mRecyclerAdapter.notifyDataSetChanged();
//                queryLoanRecords("0", mStrings.size() + "");
//                Toast.makeText(mContext, "refresh", Toast.LENGTH_SHORT).show();
//            }
//        };
    }

    @Override
    public void initListener() {

    }
    public boolean hasMore = true;
    @Override
    public void initdata() {

        postCallback = new PostCallback<LoanRecordsActivity>(this) {


            @Override
            public void successCallback(Result_Api api) {
                mRecyclerView.refreshComplete(0);
                LoadRecordResponse loadRecordResponse = (LoadRecordResponse) api.getT();
                List<LoanRecord> rows = loadRecordResponse.getRows();
                if(rows!=null&&rows.size()>0){
                    for(int i=0;i<10;i++){
                        adapter22.addAll(rows);
                    }
                    adapter22.addAll(rows);
                    mCurrentCounter += rows.size();
                    Toast.makeText(mContext, rows.size() + "" + GsonUtils.toJson(rows.get(0)).toString(), Toast.LENGTH_SHORT).show();
                }else{
                    hasMore = false;
                }

//                    mRecyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void failCallback() {

            }
        };

        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {

                adapter22.clear();
                recyclerViewAdapter.notifyDataSetChanged();//fix bug:crapped or attached views may not be recycled. isScrap:false isAttached:true
                mCurrentCounter = 0;
                queryLoanRecords("0", "1");
            }
        });
        //是否禁用自动加载更多功能,false为禁用, 默认开启自动加载更多功能
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setFooterViewColor(R.color.white,R.color.color_hint,R.color.common_backgroud);
        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                Log.i("count",mCurrentCounter+"");
                if (mCurrentCounter<5) {
                    // loading more
                    queryLoanRecords(0+"","10");
                } else {
                    //the end
                    mRecyclerView.setNoMore(true);
                }
            }
        });
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
