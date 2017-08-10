package com.jsoup_500px.demo;

import com.jsoup_500px.utils.JsoupUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

;
;

/**
 * 爬取500px上的图片
 *
 * @author：Kyle.yangkg
 * @create：2017-07-11 上午 9:54
 * ©copyright：www.aisino.com
 */
public class DownloadImages {
    //The url of the website. This is just an example
    private static final String webSiteURL = "https://500px.com/popular";

    //The path of the folder that you want to save the images to
    private static final String folderPath = "d:\\vggee";

    public static void main(String[] args) throws Exception {
        int i = 0;
        Document doc = JsoupUtils.getHtmlDocument(webSiteURL);
        ////获取html div 标签
        //Elements els = doc.getElementsByTag("div");
        //for (Element el : els) {
        //    //获取div内容展示class 中的内容
        //    String className = "photo_thumbnail jg-entry entry-visible";
        //    if(el.attr("class").equals(className)){
        //        Elements imgs = el.getElementsByTag("img");
        //        //获取全部img
        //        for (Element img : imgs) {
        //            i++;
        //            String imgSrc = img.attr("src");
        //            JsoupUtils.outPutStreamImg(imgSrc,folderPath, DateUtilServer.dateToString(new Date(), "yyyyMMddHHmmss")+i);
        //        }
        //    }
        //}

        Element content = doc.getElementById("content");

        Elements allElements = content.getAllElements();

    }
}
