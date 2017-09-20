package com.bb.taold.bean;

/**
 * Created by zhouwan on 2017/9/19.
 */

public class MessageResult {


    /**
     * id : 2
     * title : 借款
     * content : 您好，您的借款申请已审核通过，请尽快到“app名称”App点击确认借款，请在YYYY-MM-DD前确认借款，否则系统会关闭您的借款申请。如有任何疑问，欢迎拨打客服热线400-xxxx－xxxx咨询。
     * senderId : 10004
     * receiverId : 30
     * msgType : 20
     * status : 10
     * gmtCreate : 1504766958000
     * gmtModify : 1505803761000
     * createName : null
     * modifyName : null
     */

    private long id;
    private String title;
    private String content;
    private int msgType;
    private int status;
    private String gmtCreate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
