package com.bb.taold.api;

import com.bb.taold.utils.Constants;

/**
 * Created by hp on 2016/8/9.
 */
public class Result_Api<T> {

    /**
     * {"status":"SUCCESS","text":"成功","description":null,"t":null,"success":true}
     */

    private String status = "";//返回code字段

    private String text = "";//返回msg

    private String description = "";//

    private boolean success;//

    private T t;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (Constants.SUCCESS.equalsIgnoreCase(status))
            setSuccess(true);
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
