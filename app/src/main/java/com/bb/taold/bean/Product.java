package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/14.
 * Package Name com.bb.taold.bean
 * Class Name Product
 */

public class Product {
    private String id = "";
    //产品编号
    private String productCode = "";
    //最大金额
    private String maxAmt = "";
    //最小金额
    private String minAmt = "";
    //产品类型 10,按天 20,按月
    private String productType = "";
    private String defaultAmt = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(String maxAmt) {
        this.maxAmt = maxAmt;
    }

    public String getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(String minAmt) {
        this.minAmt = minAmt;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getDefaultAmt() {
        return defaultAmt;
    }

    public void setDefaultAmt(String defaultAmt) {
        this.defaultAmt = defaultAmt;
    }
}
