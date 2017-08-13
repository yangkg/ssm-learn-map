package com.weixin.util;

import com.thoughtworks.xstream.XStream;
import com.weixin.pojo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


/**
 * 消息转换
 *
 * @author：Kyle.yangkg
 * @create：2017-08-10 上午 10:14
 * ©copyright：www.aisino.com
 */
public class MessageUtil {
    //消息类型常量
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_MUSIC = "music";


    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        HashMap<String, String> map = new HashMap<>();

        SAXReader reader = new SAXReader();
        ServletInputStream input = request.getInputStream();

        Document doc = reader.read(input);
        Element root = doc.getRootElement();
        List<Element> elements = root.elements();

        for (Element e : elements) {
            map.put(e.getName(), e.getText());
        }

        input.close();

        return map;
    }

    public static String TextMessageToXML(TextMessage textMessage) {
        XStream xStream = new XStream();
        //将xml的根元素替换为"xml"，如果不替换的话这是"com.weixin.pojp.TextMessage"
        xStream.alias("xml", textMessage.getClass());
        String xml = xStream.toXML(textMessage);
        return xml;

    }

    public static String initText(String toUserName, String fromUserName, String content) {
        TextMessage message = new TextMessage();

        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        message.setMsgType(MessageUtil.MESSAGE_TEXT);
        message.setCreateTime(new Date().getTime());
        message.setContent(content);
        return MessageUtil.TextMessageToXML(message);

    }

    public static String menueText() {
        StringBuffer sb = new StringBuffer();
        sb.append("欢迎您的关注,请按照菜单提示进行操作！\n\n");
        sb.append("1、课程介绍\n");
        sb.append("2、慕课网介绍\n");
        sb.append("回复？显示此菜单！");

        return sb.toString();
    }

    public static String firstMenue() {
        StringBuffer sb = new StringBuffer();
        sb.append("本门课程是《初识微信公众号开发》的进阶课程，在入门课程的基础上，对Java微信公众号的开发模式进行深入讲解。主要介绍了图文、音乐消息的回复，自定义菜单，最后介绍了百度翻译小案例。");
        return sb.toString();
    }

    public static String secondMenue() {
        StringBuffer sb = new StringBuffer();
        sb.append("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。\n" +
                "慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。以纯干货、短视频的形式为平台特点，为在校学生、职场白领提供了一个迅速提升技能、共同分享进步的学习平台。[1] \n" +
                "4月2日，国内首个IT技能学习类应用——慕课网3.1.0版本在应用宝首发。据了解，在此次上线的版本中，慕课网新增了课程历史记录、相关课程推荐等四大功能，为用户营造更加丰富的移动端IT学习体验。[2] ");
        return sb.toString();
    }


    /**
     * 图文消息转换为XML
     *
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXML(NewsMessage newsMessage) {
        XStream xStream = new XStream();
        //将xml的根元素替换为"xml"，如果不替换的话这是"com.weixin.pojp.TextMessage"
        xStream.alias("xml", newsMessage.getClass());
        xStream.alias("item", new News().getClass());
        String xml = xStream.toXML(newsMessage);
        return xml;

    }

    public static String initNewsMessage(String toUserName, String fromUserName) {
        String message = null;

        List<News> newsList = new ArrayList<>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("慕课网介绍");
        news.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。");
        news.setPicUrl("http://reihx.ngrok.cc/ssm/image/123.png");
        news.setUrl("www.imooc.com");

        newsList.add(news);

        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticleCount(1);
        newsMessage.setArticles(newsList);

        message = newsMessageToXML(newsMessage);
        return message;
    }

    /**
     * 讲图片消息转换为xml
     *
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXML(ImageMessage imageMessage) {
        XStream xStream = new XStream();
        //将xml的根元素替换为"xml"，如果不替换的话这是"com.weixin.pojp.TextMessage"
        xStream.alias("xml", imageMessage.getClass());
        //xStream.alias("item", new News().getClass());
        String xml = xStream.toXML(imageMessage);
        return xml;

    }

    /**
     * 组装图文消息
     *
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initImageMessage(String toUserName, String fromUserName) {
        String message = null;
        Image image = new Image();
        image.setMediaId("P110CNDtw5LFGGovgq1PpDuTJsQtfeKmW23pzvCf5tQgfM76ZXoEs7v6abbxH3_V");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setImage(image);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setToUserName(fromUserName);
        imageMessage.setFromUserName(toUserName);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        message = imageMessageToXML(imageMessage);
        return message;
    }


    public static String musicMessageToXML(MusicMessage musicMessage ) {
        XStream xStream = new XStream();
        //将xml的根元素替换为"xml"，如果不替换的话这是"com.weixin.pojp.TextMessage"
        xStream.alias("xml", musicMessage.getClass());
        //xStream.alias("Music", new Music().getClass());
        String xml = xStream.toXML(musicMessage);
        return xml;

    }

    public static String initMusicMessage(String toUserName, String fromUserName) {
        String message = null;
        Music music = new Music();
        music.setThumbMediaId("zw5UZAVyId5qdB1Y9aAqO1vPo5hRKIbC3bDpjiuLFr1CGUBonF_9Akjvc8XcLv7C");
        music.setTitle("see you again");
        music.setDescription("速度与激情7片尾曲");
        music.setMusicUrl("http://reihx.ngrok.cc/ssm/music/SeeYouAgain.mp3");
        music.setHQMusicUrl("http://reihx.ngrok.cc/ssm/music/SeeYouAgain.mp3");
        MusicMessage musicMessage = new MusicMessage();
        musicMessage.setFromUserName(toUserName);
        musicMessage.setToUserName(fromUserName);
        musicMessage.setMusic(music);
        musicMessage.setCreateTime(new Date().getTime());
        musicMessage.setMsgType(MESSAGE_MUSIC);
        message = musicMessageToXML(musicMessage);
        return message;
    }


}
