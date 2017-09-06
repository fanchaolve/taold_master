package com.bb.taold.bean;

/**
 * 版本号
 *
 */

public class VersionBean {
    private String version;// "1.0.1.2",
    private String link;// "http://hao123.com",
    private String explain;// "这里是升级显示内容"
    private String force;   //强制升级开关

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getForce() {
        return force;
    }

    public void setForce(String force) {
        this.force = force;
    }
}
