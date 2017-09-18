package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/17.
 * Package Name com.bb.taold.bean
 * Class Name BillProductInfo
 */

public class BillProductInfo {

    /**
     * "loanDays": 7,
     "yearRates": "0.12",
     "loanMoney": 7000,
     "totalStages": 1,
     "productName": "小额借款"
     */

    public String loanDays = "";
    public String yearRates = "";
    public String loanMoney = "";
    public String totalStages = "";
    public String productName = "";

    public String getLoanDays() {
        return loanDays;
    }

    public void setLoanDays(String loanDays) {
        this.loanDays = loanDays;
    }

    public String getYearRates() {
        return yearRates;
    }

    public void setYearRates(String yearRates) {
        this.yearRates = yearRates;
    }

    public String getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(String loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getTotalStages() {
        return totalStages;
    }

    public void setTotalStages(String totalStages) {
        this.totalStages = totalStages;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
