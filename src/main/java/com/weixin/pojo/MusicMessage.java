package com.weixin.pojo;

;
;

/**
 * 音乐消息
 *
 * @author：Kyle.yangkg
 * @create：2017-08-13 16:58
 * ©copyright：www.aisino.com
 */
public class MusicMessage extends BaseMessage{

    private Music Music;

    public com.weixin.pojo.Music getMusic() {
        return Music;
    }

    public void setMusic(com.weixin.pojo.Music music) {
        Music = music;
    }
}
