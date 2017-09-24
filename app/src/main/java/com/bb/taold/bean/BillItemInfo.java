package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/18.
 * Package Name com.bb.taold.bean
 * Class Name BillItemInfo
 */

public class BillItemInfo {
    public BillItemDetail billInfo = null;
    private RepayChannelInfo repayChannelInfo;

    public BillItemDetail getBillInfo() {
        return billInfo;
    }

    public void setBillInfo(BillItemDetail billInfo) {
        this.billInfo = billInfo;
    }

    public RepayChannelInfo getRepayChannelInfo() {
        return repayChannelInfo;
    }

    public void setRepayChannelInfo(RepayChannelInfo repayChannelInfo) {
        this.repayChannelInfo = repayChannelInfo;
    }
}
