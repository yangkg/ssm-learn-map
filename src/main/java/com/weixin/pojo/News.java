package com.weixin.pojo;

;
;

/**
 * 图文消息
 *
 * @author：Kyle.yangkg
 * @create：2017-08-11 下午 2:28
 * ©copyright：www.aisino.com
 */
public class News {
    //标题
    private String Title;
    //描述
    private String Description;
    //图片地址
    private String PicUrl;
    //转发的url
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
