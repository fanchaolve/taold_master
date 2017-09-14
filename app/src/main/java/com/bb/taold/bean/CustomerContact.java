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
 * 时间：2017/8/22
 * <p>
 * ==============================================
 */

public class CustomerContact implements Serializable {

    /**
     * relationship : 关系
     * urgentMobile : 电话号码
     * urgentName : 称呼
     */

    private String id="";
    private String relativeType="";
    private String telphone="";
    private String name="";

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

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
