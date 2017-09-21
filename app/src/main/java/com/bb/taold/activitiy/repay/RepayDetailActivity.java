package com.bb.taold.activitiy.repay;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.taold.R;
import com.bb.taold.adapter.RepayDetailAdapter;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.BillInfoDetail;
import com.bb.taold.bean.BillProductInfo;
import com.bb.taold.listener.Callexts;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

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
    @BindView(R.id.tv_loanAmount)
    TextView mTvLoanAmount;
    @BindView(R.id.tv_loanDays)
    TextView mTvLoanDays;
    @BindView(R.id.tv_yearrate)
    TextView mTvYearrate;
    @BindView(R.id.lv_repay_detail)
    ListView mLvRepayDetail;
    @BindView(R.id.swiper_refresh)
    SwipeRefreshLayout mSwiperRefresh;
    //接口返回接受
    private PostCallback postCallback;
    //该笔账单id
    String billId = "";
    //大订单的所有信息
    private BillInfoDetail details;

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
        if (getIntent() != null && getIntent().getExtras() != null) {
            //获取账单id
            billId = getIntent().getExtras().getString("billId");
            if (TextUtils.isEmpty(billId)) {
                return;
            }
            getBillInfoDetail(billId);
        }

        mSwiperRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBillInfoDetail(billId);
            }
        });

    }

    /**
     * 获取账单详情
     *
     * @param billId
     */
    private void getBillInfoDetail(String billId) {
        //获取未还款账单详情
        Call<Result_Api<BillInfoDetail>> call = service.detail(billId);
        Callexts.need_sessionPost(call, new PostCallback() {
            @Override public void successCallback(Result_Api api) {
                mSwiperRefresh.setRefreshing(false);
                if (api.getT() instanceof BillInfoDetail) {
                    //获取账单详情
                    details = (BillInfoDetail) api.getT();
                    //设置借款金额,借款期限,年利率
                    BillProductInfo productInfo = details.getProductInfo();
                    //设置借款金额
                    mTvLoanAmount.setText(productInfo.getLoanMoney());
                    //设置借款期限
                    mTvLoanDays.setText(productInfo.getLoanDays() + "天");
                    //设置年利率
                    mTvYearrate.setText((Double.parseDouble(productInfo.getYearRates()) * 100) + "%");

                    RepayDetailAdapter mAdapter = new RepayDetailAdapter(RepayDetailActivity.this, details);

                    mLvRepayDetail.setAdapter(mAdapter);

                    return;
                }
            }

            @Override public void failCallback() {

            }
        });
    }


    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
