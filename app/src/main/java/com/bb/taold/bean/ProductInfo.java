package com.bb.taold.bean;

import java.util.ArrayList;

/**
 * Created by zhucheng'an on 2017/9/14.
 * Package Name com.bb.taold.bean
 * Class Name ProductInfo
 */

public class ProductInfo {

    /**
     *  "stagesInfo": [
     {
     "id": 1,
     "productId": 1,
     "stages": 7,
     "stagesName": "7天",
     "interestRates": 0.05,
     "creditAuditRates": 0.08,
     "creditInspectRates": 0.04,
     "manageRates": 0.033,
     "introduceRates": 0.09,
     "lateRates": 0.087,
     "status": 10,
     "gmtCreate": 1504850094000,
     "gmtModify": 1505195701000
     },
     {
     "id": 2,
     "productId": 1,
     "stages": 14,
     "stagesName": "14天",
     "interestRates": 1,
     "creditAuditRates": 1,
     "creditInspectRates": 1,
     "manageRates": 1,
     "introduceRates": 1,
     "lateRates": 1,
     "status": 10,
     "gmtCreate": 1504680462000,
     "gmtModify": 1505198866000
     }
     ],
     "productInfo": {
     "id": 1,
     "productCode": "mini_loan",
     "maxAmt": 100000,
     "minAmt": 200,
     "productType": 10,
     "defaultAmt": 1,
     "gmtCreate": 1504247253000,
     "gmtModify": 1504765661000
     }
     */

    ArrayList<StagesInfo> stagesInfo = null;
    Product productInfo = null;

    public ArrayList<StagesInfo> getStagesInfo() {
        return stagesInfo;
    }

    public void setStagesInfo(ArrayList<StagesInfo> stagesInfo) {
        this.stagesInfo = stagesInfo;
    }

    public Product getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(Product productInfo) {
        this.productInfo = productInfo;
    }
}
