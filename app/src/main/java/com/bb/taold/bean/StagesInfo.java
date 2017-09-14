package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/14.
 * Package Name com.bb.taold.bean
 * Class Name StagesInfo
 */

public class StagesInfo {

    private String id = "";
    //产品Id
    private String productId = "";
    //分期数
    private String stages = "";
    private String stagesNames = "";
    //利息利率
    private String interestRates = "";
    //额度审核费率
    private String creditAuditRates = "";
    //征信查询费率
    private String creditInspectRates = "";
    //贷后管理费率
    private String manageRates = "";
    //介绍费率
    private String introduceRates = "";
    //逾期费率
    private String lateRates = "";
    //状态值
    private String status = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStages() {
        return stages;
    }

    public void setStages(String stages) {
        this.stages = stages;
    }

    public String getStagesNames() {
        return stagesNames;
    }

    public void setStagesNames(String stagesNames) {
        this.stagesNames = stagesNames;
    }

    public String getInterestRates() {
        return interestRates;
    }

    public void setInterestRates(String interestRates) {
        this.interestRates = interestRates;
    }

    public String getCreditAuditRates() {
        return creditAuditRates;
    }

    public void setCreditAuditRates(String creditAuditRates) {
        this.creditAuditRates = creditAuditRates;
    }

    public String getCreditInspectRates() {
        return creditInspectRates;
    }

    public void setCreditInspectRates(String creditInspectRates) {
        this.creditInspectRates = creditInspectRates;
    }

    public String getManageRates() {
        return manageRates;
    }

    public void setManageRates(String manageRates) {
        this.manageRates = manageRates;
    }

    public String getIntroduceRates() {
        return introduceRates;
    }

    public void setIntroduceRates(String introduceRates) {
        this.introduceRates = introduceRates;
    }

    public String getLateRates() {
        return lateRates;
    }

    public void setLateRates(String lateRates) {
        this.lateRates = lateRates;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
