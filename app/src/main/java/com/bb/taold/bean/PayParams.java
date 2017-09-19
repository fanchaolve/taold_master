package com.bb.taold.bean;

/**
 * 类描述：
 * 创建时间：2017/9/19 0019
 *
 * @author chaochao
 */

public class PayParams {

    /**
     * orderTime : 20170919112449
     * orderMoney : 1.00
     * merId : 100100102
     * merchantOutOrderNo : 298630847284455422
     * noncestr : 378775912677148
     */

    private String orderTime;
    private String orderMoney;
    private String merId;
    private String merchantOutOrderNo;
    private String noncestr;

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getMerchantOutOrderNo() {
        return merchantOutOrderNo;
    }

    public void setMerchantOutOrderNo(String merchantOutOrderNo) {
        this.merchantOutOrderNo = merchantOutOrderNo;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }
}
