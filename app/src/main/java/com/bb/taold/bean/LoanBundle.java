package com.bb.taold.bean;

import java.io.Serializable;

/**
 * Created by zhouwan on 2017/9/23.
 */

public class LoanBundle implements Serializable {
    private String loanType;
    private String stageId;
    private String loanAmount;
    /**
     * 借款天数
     */
    private String loanDays;

    public String getLoanDays() {
        return loanDays;
    }

    public void setLoanDays(String loanDays) {
        this.loanDays = loanDays;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }


}
