package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/16.
 * Package Name com.bb.taold.bean
 * Class Name BillDetail
 */

public class BillDetail {

    String id = "";
    //账单ID
    String billId = "";
    //期数
    String stages = "";
    //账单金额
    String billAmount = "";
    //用户ID
    String memberId = "";
    //本金
    String principal = "";
    //管理费
    String manageCost = "";
    //利息
    String loanInterestCost = "";
    //应还金额
    String dueAmount = "";
    //0未逾期 1已逾期
    String isOverdue = "";
    //应还款日
    String dueDate = "";
    //实际还款日
    String repayDate = "";
    //逾期天数
    String overdueDays = "";
    //逾期金额
    String overdueAmt = "";
    //已代扣金额
    String deductAmt = "";
    //减免总金额
    String abateAmt = "";
    //0为还款 1已还款
    String state = "";

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
