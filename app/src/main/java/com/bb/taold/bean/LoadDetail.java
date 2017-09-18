package com.bb.taold.bean;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/18 0018
 *
 * @author chaochao
 */

public class LoadDetail {

    /**
     * loanAmount : 1.00
     * periods : 7
     * bankName :
     * bankNo :
     * lifeCycle : [{"title":"借款已提交","date":1505636766000,"description":"您的借款已提交至自动化审批系统，我们会尽快完成审核并通知您审批结果。"}]
     */

    private String loanAmount;
    private int periods;
    private String bankName;
    private String bankNo;
    private List<LifeCycleBean> lifeCycle;

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

    public List<LifeCycleBean> getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(List<LifeCycleBean> lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

}
