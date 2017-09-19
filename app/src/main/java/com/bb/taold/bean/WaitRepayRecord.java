package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/18.
 * Package Name com.bb.taold.bean
 * Class Name WaitRepayRecord
 */

public class WaitRepayRecord {
    /**
     * "shouldPayAmt": 6060,
     * "waitPayAmt": 7000,
     * "stages": 1,
     * "billItems": [
     * {
     * "statusDesc": "未还",
     * "isOverdue": false,
     * "amtMoney": 100,
     * "overdueStatusDesc": "",
     * "billItemId": 7,
     * "stages": 1,
     * "repayDate": 1504773617000,
     * "status": 10
     * }
     * ]
     * },
     */
    private String billId;
    private String shouldPayAmt = "";
    private String waitPayAmt = "";
    private String stages = "";
    private BillItems billItems = null;


    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getShouldPayAmt() {
        return shouldPayAmt;
    }

    public void setShouldPayAmt(String shouldPayAmt) {
        this.shouldPayAmt = shouldPayAmt;
    }

    public String getWaitPayAmt() {
        return waitPayAmt;
    }

    public void setWaitPayAmt(String waitPayAmt) {
        this.waitPayAmt = waitPayAmt;
    }

    public String getStages() {
        return stages;
    }

    public void setStages(String stages) {
        this.stages = stages;
    }

    public BillItems getBillItems() {
        return billItems;
    }

    public void setBillItems(BillItems billItems) {
        this.billItems = billItems;
    }
}
