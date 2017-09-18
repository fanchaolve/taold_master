package com.bb.taold.bean;

import java.io.Serializable;

/**
 * Created by zhucheng'an on 2017/9/17.
 * Package Name com.bb.taold.bean
 * Class Name BillInfoDetail
 */

public class BillInfoDetail implements Serializable{

    BillItems billItems = null;

    BillProductInfo productInfo = null;

    public BillItems getBillItems() {
        return billItems;
    }

    public void setBillItems(BillItems billItems) {
        this.billItems = billItems;
    }

    public BillProductInfo getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(BillProductInfo productInfo) {
        this.productInfo = productInfo;
    }
}
