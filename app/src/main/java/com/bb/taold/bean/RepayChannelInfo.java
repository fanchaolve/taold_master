package com.bb.taold.bean;

import java.io.Serializable;

/**
 * Created by zhouwan on 2017/9/24.
 */

public class RepayChannelInfo implements Serializable {


    /**
     * paySuccessDate : 1506235270000
     * payChannel : 支付宝
     */

    private String paySuccessDate;
    private String payChannel;

    public String getPaySuccessDate() {
        return paySuccessDate;
    }

    public void setPaySuccessDate(String paySuccessDate) {
        this.paySuccessDate = paySuccessDate;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }
}
