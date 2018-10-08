package com.hundsun.votesystem.model;

/**
 * 返回给前端格式
 */
public class ReturnData {
    private String returnMsg;//success or error
    private String returnMsgDetail;//返回详细信息

    public ReturnData(){
        returnMsg="success";
        returnMsgDetail="null msg";
        returnObject="null object";
    }
    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public String getReturnMsgDetail() {
        return returnMsgDetail;
    }

    public void setReturnMsgDetail(String returnMsgDetail) {
        this.returnMsgDetail = returnMsgDetail;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    private Object returnObject;
}
