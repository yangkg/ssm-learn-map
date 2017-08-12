package com.weixin.pojo;

;
;

/**
 * @author：Kyle.yangkg
 * @create：2017-08-10 上午 10:33
 * ©copyright：www.aisino.com
 */
public class TextMessage extends BaseMessage {
    private String Content;
    private String MsgId;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
