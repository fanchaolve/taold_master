package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/18.
 * Package Name com.bb.taold.bean
 * Class Name BillItemDetail
 */

public class BillItemDetail {
    /**
     * "t": {
     "id": 7,
     "billId": 13,
     "stages": 1,
     "billAmount": 3000,
     "memberId": 28,
     "principal": 3000,
     "manageCost": 125,
     "loanInterestCost": 17,
     "dueAmount": 100,
     "isOverdue": 1,
     "dueDate": 1504773617000,
     "repayDate": 1505270706000,
     "overdueDays": 10,
     "overdueAmt": 100,
     "deductAmt": 100,
     "repayId": null,
     "abateAmt": 12,
     "status": 1,
     },
     */

    public String id = "";
    public String billId = "";
    public String stages = "";
    public String billAmount = "";
    public String memberId = "";
    public String principal = "";
    public String manageCost = "";
    public String loanInterestCost = "";
    public String dueAmount = "";
    public String isOverdue = "";
    public String dueDate = "";
    public String repayDate = "";
    public String overdueDays = "";
    public String overdueAmt = "";
    public String deductAmt = "";
    public String abateAmt = "";
    public String state = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getStages() {
        return stages;
    }

    public void setStages(String stages) {
        this.stages = stages;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getManageCost() {
        return manageCost;
    }

    public void setManageCost(String manageCost) {
        this.manageCost = manageCost;
    }

    public String getLoanInterestCost() {
        return loanInterestCost;
    }

    public void setLoanInterestCost(String loanInterestCost) {
        this.loanInterestCost = loanInterestCost;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public String getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(String overdueDays) {
        this.overdueDays = overdueDays;
    }

    public String getOverdueAmt() {
        return overdueAmt;
    }

    public void setOverdueAmt(String overdueAmt) {
        this.overdueAmt = overdueAmt;
    }

    public String getDeductAmt() {
        return deductAmt;
    }

    public void setDeductAmt(String deductAmt) {
        this.deductAmt = deductAmt;
    }

    public String getAbateAmt() {
        return abateAmt;
    }

    public void setAbateAmt(String abateAmt) {
        this.abateAmt = abateAmt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
