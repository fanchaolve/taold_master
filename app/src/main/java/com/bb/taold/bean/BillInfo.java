package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/16.
 * Package Name com.bb.taold.bean
 * Class Name BillInfo
 */

public class BillInfo {

    /**
     * {
     "loanTime": "2017-09-14",
     "isOverdue": "0",
     "description": "已还款",
     "totals": "200040",
     "id": 14,
     "status": "20"
     }
     */

    //借款时间
    public String loanTime = "";
    //是否逾期 0未逾期 1已逾期
    public String isOverdue = "";
    //描述
    public String description = "";
    //总借款金额
    public String totals = "";
    //借款Id
    public String id = "";
    //账单状态 10 还款中 20已还款
    public String status = "";

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTotals() {
        return totals;
    }

    public void setTotals(String totals) {
        this.totals = totals;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
