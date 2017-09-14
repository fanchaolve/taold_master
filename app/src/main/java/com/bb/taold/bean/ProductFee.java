package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/14.
 * Package Name com.bb.taold.bean
 * Class Name ProductFee
 */

public class ProductFee {

    /**
     *  "interestRates": "0.05",
     "manageRates": "0.03",
     "totalMoney": "1.08",
     "theActualToAccount": "0.79",
     "creditInspectRates": "0.04",
     "creditAuditRates": "0.08",
     "introduceRates": "0.09"
     */

    //利息费
    private String interestRates = "";
    //贷后管理费
    private String manageRates = "";
    //总计
    private String totalMoney = "";
    //实际到账金额
    private String theActualToAccount = "";
    //征信查询费
    private String creditInspectRates = "";
    //额度审核费
    private String creditAuditRates = "";
    //介绍费
    private String introduceRates = "";

    public String getInterestRates() {
        return interestRates;
    }

    public void setInterestRates(String interestRates) {
        this.interestRates = interestRates;
    }

    public String getManageRates() {
        return manageRates;
    }

    public void setManageRates(String manageRates) {
        this.manageRates = manageRates;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getTheActualToAccount() {
        return theActualToAccount;
    }

    public void setTheActualToAccount(String theActualToAccount) {
        this.theActualToAccount = theActualToAccount;
    }

    public String getCreditInspectRates() {
        return creditInspectRates;
    }

    public void setCreditInspectRates(String creditInspectRates) {
        this.creditInspectRates = creditInspectRates;
    }

    public String getCreditAuditRates() {
        return creditAuditRates;
    }

    public void setCreditAuditRates(String creditAuditRates) {
        this.creditAuditRates = creditAuditRates;
    }

    public String getIntroduceRates() {
        return introduceRates;
    }

    public void setIntroduceRates(String introduceRates) {
        this.introduceRates = introduceRates;
    }
}
