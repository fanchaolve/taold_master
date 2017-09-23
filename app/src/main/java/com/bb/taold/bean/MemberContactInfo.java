package com.bb.taold.bean;

import java.io.Serializable;

/**
 * Created by zhouwan on 2017/9/23.
 */

public class MemberContactInfo implements Serializable {

    /**
     * id :
     * relativeType :
     * name :
     * telphone :
     */

    private String id;
    private int relativeType;
    private String name;
    private String telphone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRelativeType() {
        return relativeType;
    }

    public void setRelativeType(int relativeType) {
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
