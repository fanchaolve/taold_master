package com.bb.taold.bean;

import java.io.Serializable;

/**
 * Created by zhucheng'an on 2017/9/13.
 * Package Name com.bb.taold.bean
 * Class Name CardCheck
 */

public class CardCheck implements Serializable{

    /**
     *   "id": 1,
     *   "bankCode": "102",
     *   "bankName": "工商银行",
     *   "status": 10,
     *   "gmtCreate": 1504249005000,
     *   "gmtModify": 1504335410000
     */

    public String id = "";
    public String bankCode = "";
    public String bankName = "";
    public String status = "";
    public String gmtCreate = "";
    public String gmtModify = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(String gmtModify) {
        this.gmtModify = gmtModify;
    }
}
