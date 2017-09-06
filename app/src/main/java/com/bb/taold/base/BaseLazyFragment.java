package com.bb.taold.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ViewStubCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.bb.taold.base.m.BaseModel;
import com.bb.taold.base.p.BasePresenter;
import com.bb.taold.base.v.BaseView;
import com.bb.taold.utils.InstanceUtil;

import butterknife.Unbinder;

/**
 * 懒惰式加载fragment基础类
 * Created by fancl
 */

public abstract class BaseLazyFragment<P extends BasePresenter, M extends BaseModel> extends Fragment
        implements BaseView {


    // Fragment的根View
    private View mRootView;
    // 检测声明周期中，是否已经构建视图
    private boolean mViewCreated = false;
    // 占位图
    private ViewStubCompat mViewStub;


    public P mPresenter;
    public Context mContext;
    private Unbinder unbinder;

    private boolean mUserVisible = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mPresenter = InstanceUtil.getInstance(this, 0);
        if (this instanceof BaseView && mPresenter != null)
            mPresenter.setVM(this, InstanceUtil.getInstance(this, 1));
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutId() == 0) {
            return null;
        }

        if (mRootView != null) {
            mViewCreated = true;
            return mRootView;
        }

        final Context context = inflater.getContext();
        FrameLayout root = new FrameLayout(context);
        mViewStub = new ViewStubCompat(context, null);

        mViewStub.setLayoutResource(getLayoutId());
        root.addView(mViewStub, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        root.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.MATCH_PARENT, ViewGroup.MarginLayoutParams.MATCH_PARENT));

        mRootView = root;
        mViewCreated = true;
        if (mUserVisible) {
            realLoad();
        }

        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public void onStart() {
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mViewCreated = false;

        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDetached();

    }

    /**
     * 提示信息
     *
     * @param aFormatMsg
     * @param aMsgArgs
     */
    public void showTip(String aFormatMsg, Object... aMsgArgs) {
        String outString = String.format(aFormatMsg, aMsgArgs);
        int duration = (outString.length() > 10) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(mContext, outString, duration).show();
    }

    /**
     * 布局加载
     *
     * @return
     */
    public abstract int getLayoutId();


    @Override
    public void showMsg(String msg) {
        showTip(msg);
    }

    @Override
    public void initLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dissmissLoading() {

    }

    @Override
    public void updateLoading() {

    }

    @Override
    public final void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mUserVisible = isVisibleToUser;
        if (mUserVisible && mViewCreated) {
            realLoad();
        }
    }


    // 判断是否已经加载
    private boolean mLoaded = false;

    /**
     * 控制只允许加载一次
     */
    private void realLoad() {
        if (mLoaded) {
            return;
        }

        mLoaded = true;
        onRealViewLoaded(mViewStub.inflate());
    }

    /**
     * 当视图真正加载时调用
     */
    protected abstract void onRealViewLoaded(View view);


}
