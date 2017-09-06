package com.bb.taold.api;

/**
 * Created by hp on 2016/8/9.
 */
public class Result_Api<T> {

    private String error;//返回code字段

    private String msg;//返回msg

    private boolean success;//

    private T data;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
