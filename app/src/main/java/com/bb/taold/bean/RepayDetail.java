package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/13.
 * Package Name com.bb.taold.bean
 * Class Name RepayDetail
 */

public class RepayDetail {

    //分期期数
    public String period = "";
    //分期时间
    public String time = "";
    //分期金额
    public String amount = "";
    //分期状态
    public String status = "";

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
