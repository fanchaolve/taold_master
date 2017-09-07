package com.bb.taold.bean;

/**
 * ==============================================
 * <p>
 * 包名：com.bb.taold.bean
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/7
 * <p>
 * ==============================================
 */

public class Session {


    /**
     * expiresIn : 1800000
     * session : C11AAADD-4E70-44D1-B474-586E0A47DEC5
     */

    private int expiresIn;
    private String session;

    private String memberId;

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
