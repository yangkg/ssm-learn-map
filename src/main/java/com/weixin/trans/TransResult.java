package com.weixin.trans;


public class TransResult {
    private String from;
    private String to;
    private Trans_Result[] trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Trans_Result[] getTrans_result() {
        return trans_result;
    }

    public void setTrans_result(Trans_Result[] trans_result) {
        this.trans_result = trans_result;
    }
}
