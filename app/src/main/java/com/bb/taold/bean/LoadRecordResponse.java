package com.bb.taold.bean;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2017/9/16 0016
 *
 * @author chaochao
 */

public class LoadRecordResponse {

    /**
     * rows : []
     * total : 0
     */

    private int total;
    private List<LoanRecord> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<LoanRecord> getRows() {
        return rows;
    }

    public void setRows(List<LoanRecord> rows) {
        this.rows = rows;
    }
}
