package com.bb.taold.bean;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/17 0017
 *
 * @author chaochao
 */

public class LoanDetail {

    /**
     * loanAmount :
     * periods :
     * bankName :
     * bankNo :
     * lifeCycle : []
     */

    private String loanAmount;
    private String periods;
    private String bankName;
    private String bankNo;
    private List<?> lifeCycle;

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public List<?> getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(List<?> lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    @Override
    public String toString() {
        return "LoanDetail{" +
                "loanAmount='" + loanAmount + '\'' +
                ", periods='" + periods + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankNo='" + bankNo + '\'' +
                ", lifeCycle=" + lifeCycle +
                '}';
    }
}
