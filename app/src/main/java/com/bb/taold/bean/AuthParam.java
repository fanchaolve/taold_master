package com.bb.taold.bean;

import java.io.Serializable;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.bean
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/11
 * <p>
 * ==============================================
 */

public class AuthParam implements Serializable {

    /**
     * authKey : 34af7a6e-56fc-406b-aa75-03d74a42cc4f
     * notifyMethod : ocr.notify
     * outOrderId : 93564095697061281704
     * partnerNo : 201709054785842729
     */

    private String authKey;
    private String notifyMethod;
    private String outOrderId;
    private String partnerNo;

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getNotifyMethod() {
        return notifyMethod;
    }

    public void setNotifyMethod(String notifyMethod) {
        this.notifyMethod = notifyMethod;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getPartnerNo() {
        return partnerNo;
    }

    public void setPartnerNo(String partnerNo) {
        this.partnerNo = partnerNo;
    }
}
