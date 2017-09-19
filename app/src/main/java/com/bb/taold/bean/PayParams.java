package com.bb.taold.bean;

/**
 * 类描述：
 * 创建时间：2017/9/19 0019
 *
 * @author chaochao
 */

public class PayParams {
    private String merchantOutOrderNo;
    private String merid;
    private String noncestr;
    private String orderMoney;
    private String orderTime;

    public String getMerchantOutOrderNo() {
        return merchantOutOrderNo;
    }

    public void setMerchantOutOrderNo(String merchantOutOrderNo) {
        this.merchantOutOrderNo = merchantOutOrderNo;
    }

    public String getMerid() {
        return merid;
    }

    public void setMerid(String merid) {
        this.merid = merid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        return "PayParams{" +
                "merchantOutOrderNo='" + merchantOutOrderNo + '\'' +
                ", merid='" + merid + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", orderMoney='" + orderMoney + '\'' +
                ", orderTime='" + orderTime + '\'' +
                '}';
    }
}
