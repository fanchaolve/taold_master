package com.bb.taold.bean;

import java.io.Serializable;

/**
 * 类描述：
 * 创建时间：2017/9/17 0017
 *
 * @author chaochao
 */

public class LoanRecord implements Serializable{
    /**
     * loanAmount : 1.00
     * periods : 7
     * applyTime : 1505636766000
     */

    private String loanAmount;
    private int periods;
    private String applyTime;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }
}
