package com.bb.taold.api;

/**
 * Created by hp on 2016/8/9.
 */
public class Result_Api<T> {

    /**
     * {"status":"SUCCESS","text":"成功","description":null,"t":null,"success":true}
     */

    private String status;//返回code字段

    private String text;//返回msg

    private String description;//

    private boolean success;//

    private T data;



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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
