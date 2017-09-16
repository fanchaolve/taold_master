package com.bb.taold.bean;

/**
 * Created by zhucheng'an on 2017/9/16.
 * Package Name com.bb.taold.bean
 * Class Name AllBill
 */

public class AllBill {

    /**
     *"rows": [
     {
     "loanTime": "2017-09-14",
     "isOverdue": "0",
     "description": "已还款",
     "totals": "200040",
     "id": 14,
     "status": "20"
     }
     ],
     "total": 2
     */

    public BillInfos rows = null;

    public String total = "";

    public BillInfos getRows() {
        return rows;
    }

    public void setRows(BillInfos rows) {
        this.rows = rows;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
