package com.bb.taold.bean;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.bean
 * <p>
 * 说明：用户认证信息
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/8
 * <p>
 * ==============================================
 */

public class AuthInfo {

    /**
     * cardBind : false
     * faceAuth : false
     * identity : false
     * mobileOperators : false
     * personalInfo : false
     * sesameCredit : false
     */

    private boolean cardBind;
    private boolean faceAuth;//人脸识别
    private boolean identity;
    private boolean mobileOperators;
    private boolean personalInfo;
    private boolean sesameCredit;

    public boolean isCardBind() {
        return cardBind;
    }

    public void setCardBind(boolean cardBind) {
        this.cardBind = cardBind;
    }

    public boolean isFaceAuth() {
        return faceAuth;
    }

    public void setFaceAuth(boolean faceAuth) {
        this.faceAuth = faceAuth;
    }

    public boolean isIdentity() {
        return identity;
    }

    public void setIdentity(boolean identity) {
        this.identity = identity;
    }

    public boolean isMobileOperators() {
        return mobileOperators;
    }

    public void setMobileOperators(boolean mobileOperators) {
        this.mobileOperators = mobileOperators;
    }

    public boolean isPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(boolean personalInfo) {
        this.personalInfo = personalInfo;
    }

    public boolean isSesameCredit() {
        return sesameCredit;
    }

    public void setSesameCredit(boolean sesameCredit) {
        this.sesameCredit = sesameCredit;
    }
}
