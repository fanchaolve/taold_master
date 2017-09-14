package com.bb.taold.bean;

import java.io.Serializable;

/**
 * ==============================================
 * <p>
 * 包名：
 * <p>
 * 说明：TODO
 * <p>
 * 作者：fancl
 * <p>
 * 时间：2017/8/11
 * <p>
 * ==============================================
 */

public class Contact implements Serializable{


    /**
     * id :
     * relativeType :
     * name :
     * telphone :
     */

    private String id="";
    private String relativeType="";
    private String name="";
    private String telphone="";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRelativeType() {
        return relativeType;
    }

    public void setRelativeType(String relativeType) {
        this.relativeType = relativeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }
}
