package com.weixin.pojo;

/**
 * 图片消息
 *
 * @author：Kyle.yangkg
 * @create：2017-08-12 22:58
 * ©copyright：www.aisino.com
 */
public class ImageMessage extends BaseMessage {
    private Image Image;

    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }
}

