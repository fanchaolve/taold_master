package com.bb.taold.activitiy.my;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.taold.R;
import com.bb.taold.adapter.MessagesAdapter;
import com.bb.taold.api.ApiServiveImpl;
import com.bb.taold.api.PostCallback;
import com.bb.taold.api.Result_Api;
import com.bb.taold.base.BaseActivity;
import com.bb.taold.bean.MessageResult;
import com.bb.taold.listener.Callexts;
import com.bb.taold.utils.AppManager;
import com.bb.taold.widget.recyclerview.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;

public class MyMessagesActivity extends BaseActivity {

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
    @BindView(R.id.rv_messages)
    RecyclerView mRvMessages;
    @BindView(R.id.swiper_refresh)
    SwipeRefreshLayout mSwiperRefresh;
    private MessagesAdapter mMessagesAdapter;
    List<MessageResult> listMessage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_messages;
    }

    @Override
    public void initView() {
        mIvRight.setVisibility(View.VISIBLE);
        mTvTitle.setText("系统消息");
        mMessagesAdapter = new MessagesAdapter(mContext, listMessage, R.layout.item_messages);
        new RecyclerUtils<MessageResult>(mContext, listMessage, mRvMessages, mMessagesAdapter, mSwiperRefresh) {

            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefresh() {
                queryMessageInfo();
            }
        };
        queryMessageInfo();

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initdata() {

    }

    @OnClick({R.id.btn_back, R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.iv_right:
                AppManager.getInstance().showActivity(FeedbackActivity.class, null);
                break;
        }
    }

    /**
     * 提交反馈
     */
    private void queryMessageInfo() {
        Call<Result_Api<List<MessageResult>>> call = new ApiServiveImpl().queryMessageInfo();
        Callexts.Unneed_sessionPost(call, new PostCallback<MyMessagesActivity>(this) {
            @Override
            public void successCallback(Result_Api api) {
                if (api.getT() == null) {
                    return;
                }
                listMessage.clear();
                listMessage.addAll((List<MessageResult>) api.getT());
                mMessagesAdapter.notifyDataSetChanged();
            }

            @Override
            public void failCallback() {

            }
        });

    }
}
