package com.bb.taold.bean;

/**
 * 类描述：
 * 创建时间：2017/9/18 0018
 *
 * @author chaochao
 */

public class LifeCycleBean {
    /**
     * title : 借款已提交
     * date : 1505636766000
     * description : 您的借款已提交至自动化审批系统，我们会尽快完成审核并通知您审批结果。
     */

    private String title;
    private long date;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
