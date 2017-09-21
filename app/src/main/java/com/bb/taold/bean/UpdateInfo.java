package com.bb.taold.bean;

/**
 * 类描述：
 * 创建时间：2017/9/21 0021
 *
 * @author chaochao
 */

public class UpdateInfo {

    /**
     * buildversion : 5
     * downloadurl : ccccccccccccccc
     * descriptionText : 哈喽
     * releaseversion : 5.0
     * forceupdate : 0
     */

    private int buildversion;
    private String downloadurl;
    private String descriptionText;
    private String releaseversion;
    private int forceupdate;

    public int getBuildversion() {
        return buildversion;
    }

    public void setBuildversion(int buildversion) {
        this.buildversion = buildversion;
    }

    public String getDownloadurl() {
        return downloadurl;
    }

    public void setDownloadurl(String downloadurl) {
        this.downloadurl = downloadurl;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getReleaseversion() {
        return releaseversion;
    }

    public void setReleaseversion(String releaseversion) {
        this.releaseversion = releaseversion;
    }

    public int getForceupdate() {
        return forceupdate;
    }

    public void setForceupdate(int forceupdate) {
        this.forceupdate = forceupdate;
    }
}
