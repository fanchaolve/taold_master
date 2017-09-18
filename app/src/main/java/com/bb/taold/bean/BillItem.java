package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/17.
 * Package Name com.bb.taold.bean
 * Class Name BillItem
 */

public class BillItem {

    /**
     *  {
     "statusDesc": "未还",
     "isOverdue": false,
     "amtMoney": 100,
     "overdueStatusDesc": "",
     "billItemId":7,
     "stages": 1,
     "repayDate": 1504773617000,
     "status": 10
     }
     */

    public String statusDesc = "";
    public String isOverdue = "";
    public String amtMoney = "";
    public String overdueStatusDesc = "";
    public String billItemId = "";
    public String stages = "";
    public String repayDate = "";
    public String status = "";

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getAmtMoney() {
        return amtMoney;
    }

    public void setAmtMoney(String amtMoney) {
        this.amtMoney = amtMoney;
    }

    public String getOverdueStatusDesc() {
        return overdueStatusDesc;
    }

    public void setOverdueStatusDesc(String overdueStatusDesc) {
        this.overdueStatusDesc = overdueStatusDesc;
    }

    public String getStages() {
        return stages;
    }

    public void setStages(String stages) {
        this.stages = stages;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillItemId() {
        return billItemId;
    }

    public void setBillItemId(String billItemId) {
        this.billItemId = billItemId;
    }
}
