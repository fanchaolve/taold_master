package com.bb.taold.bean;

/**
 * Created by Administrator on 2017/1/6.
 */

public class User {

    /**
     * output : {"address":"","areaCode":"","backUrl":"","birthday":"","city":"","domainId":"10","email":"","euck":"","gender":"","idNo":"","idType":"","isBClient":"false","lastDevice":"","lastLoginIP":"","lastLoginTime":"20170117150251","loginPwd":"1","logo":"","mobile":"15735920000","realName":"","sessionId":"f7a30f557b0304e5ab72bb41be3b5ba4","userId":"32","userName":"15735920000"}
     * respCode : 000000
     * respMsg : 登录成功
     * success : true
     */


    /**
     * address :
     * areaCode :
     * backUrl :
     * birthday :
     * city :
     * domainId : 10
     * email :
     * euck :
     * gender :
     * idNo :
     * idType :
     * isBClient : false
     * lastDevice :
     * lastLoginIP :
     * lastLoginTime : 20170117150251
     * loginPwd : 1
     * logo :
     * mobile : 15735920000
     * realName :
     * sessionId : f7a30f557b0304e5ab72bb41be3b5ba4
     * userId : 32
     * userName : 15735920000
     */

    private String address = "";
    private String areaCode = "";
    private String backUrl = "";
    private String birthday = "";
    private String city = "";
    private String domainId = "";
    private String email = "";
    private String euck = "";
    private String gender = "";
    private String idNo = "";
    private String idType = "";
    private boolean isBClient;
    private String lastDevice = "";
    private String lastLoginIP = "";
    private String lastLoginTime = "";
    private String loginPwd = "";
    private String logo = "";
    private String mobile = "";
    private String realName = "";
    private String sessionId = "";
    private String userId = "";
    private String userName = "";
    private String paymentPwd = "";
    private String nickName = "";
    private String userQrcode = "";

    private String authority = "0";

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEuck() {
        return euck;
    }

    public void setEuck(String euck) {
        this.euck = euck;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public boolean getIsBClient() {
        return isBClient;
    }

    public void setIsBClient(boolean isBClient) {
        this.isBClient = isBClient;
    }

    public String getLastDevice() {
        return lastDevice;
    }

    public void setLastDevice(String lastDevice) {
        this.lastDevice = lastDevice;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPaymentPwd() {
        return paymentPwd;
    }

    public void setPaymentPwd(String paymentPwd) {
        this.paymentPwd = paymentPwd;
    }

    public String getUserQrcode() {
        return userQrcode;
    }

    public void setUserQrcode(String userQrcode) {
        this.userQrcode = userQrcode;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
