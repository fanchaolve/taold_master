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
 * 时间：2017/9/13
 * <p>
 * ==============================================
 */

public class UserInfo implements Serializable {

    /**
     * authentication : 90
     * bindBankcardFlag : 10
     * bindCreditcardFlag : 10
     * consumeFlag : 0
     * firstConsumeTime : 1504775551000
     * gmtModify : 1504775551000
     * headPortrait :
     * id : 16
     * idCard : 330621198903134673
     * lastLoginTime : 1505296048000
     * mobile : 13656714459
     * nickName :
     * openId :
     * realName : 范超略
     * regIp : 122.224.92.122
     * regLonLat : 116.405994-39.93242
     * regTime : 1504775551000
     * sex : 10
     * startLevel : 0
     * status : 10
     */

    private int authentication;
    private int bindBankcardFlag;
    private int bindCreditcardFlag;
    private int consumeFlag;
    private long firstConsumeTime;
    private long gmtModify;
    private String headPortrait;
    private int id;
    private String idCard;
    private long lastLoginTime;
    private String mobile;
    private String nickName;
    private String openId;
    private String realName;
    private String regIp;
    private String regLonLat;
    private long regTime;
    private int sex;
    private int startLevel;
    private int status;
    private int unReadMessageCount;

    public int getAuthentication() {
        return authentication;
    }

    public void setAuthentication(int authentication) {
        this.authentication = authentication;
    }

    public int getBindBankcardFlag() {
        return bindBankcardFlag;
    }

    public void setBindBankcardFlag(int bindBankcardFlag) {
        this.bindBankcardFlag = bindBankcardFlag;
    }

    public int getBindCreditcardFlag() {
        return bindCreditcardFlag;
    }

    public void setBindCreditcardFlag(int bindCreditcardFlag) {
        this.bindCreditcardFlag = bindCreditcardFlag;
    }

    public int getConsumeFlag() {
        return consumeFlag;
    }

    public void setConsumeFlag(int consumeFlag) {
        this.consumeFlag = consumeFlag;
    }

    public long getFirstConsumeTime() {
        return firstConsumeTime;
    }

    public void setFirstConsumeTime(long firstConsumeTime) {
        this.firstConsumeTime = firstConsumeTime;
    }

    public long getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(long gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getRegLonLat() {
        return regLonLat;
    }

    public void setRegLonLat(String regLonLat) {
        this.regLonLat = regLonLat;
    }

    public long getRegTime() {
        return regTime;
    }

    public void setRegTime(long regTime) {
        this.regTime = regTime;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStartLevel() {
        return startLevel;
    }

    public void setStartLevel(int startLevel) {
        this.startLevel = startLevel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUnReadMessageCount() {
        return unReadMessageCount;
    }

    public void setUnReadMessageCount(int unReadMessageCount) {
        this.unReadMessageCount = unReadMessageCount;
    }
}
