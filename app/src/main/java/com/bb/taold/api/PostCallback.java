package com.bb.taold.api;

import android.util.Log;


import com.bb.taold.base.v.BaseView;
import com.bb.taold.utils.Constants;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/12/2.
 */

public abstract class PostCallback<V extends BaseView> implements Callback<Result_Api> {


    private final static String TAG = PostCallback.class.getSimpleName();

    private final static String ERROR_DATA = "获取数据异常";
    private final static String NET_ERROR = "请检查网络是否正常";
    private final static String NET_OK = "数据请求正常";

    private boolean isTip = true;

    private V view;

//    private Class<T> clazz;

    public PostCallback(V view) {
        this.view = view;
//        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType) {
//            clazz = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//
//        }
    }

    public PostCallback(V view, boolean isTip) {
        this.view = view;
        this.isTip = isTip;
    }


    public PostCallback() {

    }

    public abstract void successCallback(Result_Api api);

    public abstract void failCallback();

    @Override
    public void onResponse(Call<Result_Api> call, Response<Result_Api> response) {
        if (view != null) {
            view.dissmissLoading();
        }
        Result_Api api = response.body();
        if (api != null) {
            if (Constants.SUCCESS.equalsIgnoreCase(api.getError())) {
//                if (api.getOutput() == null) {
//                    failCallback();
//                    throw new JsonIOException("解析出错或者数据格式返回错误");
//
//
//                }

                successCallback(api);
            } else if (view != null) {
                failCallback();
                if (isTip)
                    view.showMsg(api.getMsg());

            }else {
                failCallback();
            }
        } else if (view != null) {
            failCallback();
            view.showMsg(ERROR_DATA);
        }


    }

    @Override
    public void onFailure(Call<Result_Api> call, Throwable t) {
        if (view != null) {
            view.dissmissLoading();
            view.showMsg(NET_ERROR);
        }
        Log.i("fancl", t.getMessage());
        failCallback();
    }
}
