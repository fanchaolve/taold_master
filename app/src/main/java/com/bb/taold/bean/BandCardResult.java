package com.bb.taold.bean;

/**
 * ==============================================
 * <p>
 * 包名：绑定银行卡返回的结果
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/9/18
 * <p>
 * ==============================================
 */

public class BandCardResult {


    /**
     * no_order : 38
     * oid_partner : 201408071000001543
     * user_id : 28
     * token : 6F2E673A1326C46A4025ED2C6C7DF4B3
     */

    private int no_order;
    private String oid_partner;
    private int user_id;
    private String token;

    public int getNo_order() {
        return no_order;
    }

    public void setNo_order(int no_order) {
        this.no_order = no_order;
    }

    public String getOid_partner() {
        return oid_partner;
    }

    public void setOid_partner(String oid_partner) {
        this.oid_partner = oid_partner;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
