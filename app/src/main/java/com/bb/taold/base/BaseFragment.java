package com.bb.taold.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bb.taold.api.ApiService;
import com.bb.taold.api.RetrofitFactory;
import com.bb.taold.base.m.BaseModel;
import com.bb.taold.base.p.BasePresenter;
import com.bb.taold.base.v.BaseView;
import com.bb.taold.utils.InstanceUtil;
import com.bb.taold.utils.LoadingUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment基础类
 * Created by fancl
 */

public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment
 implements BaseView{


    public P mPresenter;
    public Context mContext;
    private Unbinder unbinder;

    public ApiService service = RetrofitFactory.getINSTANCE().create(ApiService.class);

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
        if(getLayoutId()==0){
            return null;
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initdate(savedInstanceState);
    }


    public void onStart() {
        super.onStart();

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    /**
     * 数据填充
     */

    public abstract void initView();

    protected abstract void initdate(Bundle savedInstanceState);

    @Override
    public void showMsg(String msg) {
        showTip(msg);
    }

    @Override
    public void initLoading() {

    }

    @Override
    public void showLoading() {
        LoadingUtil.showLoading(mContext);
    }

    @Override
    public void dissmissLoading() {
        LoadingUtil.hideLoading();
    }

    @Override
    public void updateLoading() {

    }
}
