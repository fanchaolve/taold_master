package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/15.
 * Package Name com.bb.taold.bean
 * Class Name CardInfo
 */

public class CardInfo {
    /**
     *  "id": 8,
     "memberId": 28,
     "owner": "朱成安",
     "cardno": "6212261202011425460",
     "idno": "330727199412300057",
     "cardName": "工商银行",
     "cardImg": "102.jpg",
     "cvn2": "",
     "mobile": "18868181884",
     "cardType": 10,
     "status": 20,
     "sortVal": 0,
     "masterFlag": 10,
     "branch": "",
     "branchNo": "",
     "validDate": "",
     "bankCode": "",
     "gmtCreate": 1505353665000,
     "gmtModify": 1505353665000,
     "createName": null,
     "modifyName": null
     */
    //银行卡id
    private String id = "";
    private String memberId = "";
    //用户名
    private String owner = "";
    //卡号
    private String cardno = "";
    //身份证号
    private String idno = "";
    //银行名称
    private String cardName = "";
    //银行图标
    private String cardImg = "";
    //CVN2
    private String cvn2 = "";
    //绑定手机号
    private String mobile = "";
    //卡片类型
    private String cardType = "";
    private String status = "";
    private String sortVal = "";
    private String masterFlag = "";
    private String branch = "";
    private String branchNo = "";
    private String validDate = "";
    private String bankCode = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardImg() {
        return cardImg;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public String getCvn2() {
        return cvn2;
    }

    public void setCvn2(String cvn2) {
        this.cvn2 = cvn2;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSortVal() {
        return sortVal;
    }

    public void setSortVal(String sortVal) {
        this.sortVal = sortVal;
    }

    public String getMasterFlag() {
        return masterFlag;
    }

    public void setMasterFlag(String masterFlag) {
        this.masterFlag = masterFlag;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
