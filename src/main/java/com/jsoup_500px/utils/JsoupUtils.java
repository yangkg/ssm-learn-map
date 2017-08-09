package com.jsoup_500px.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

;
;

/**
 * jsoup工具类
 *
 * @author：Kyle.yangkg
 * @create：2017-07-11 上午 9:50
 * ©copyright：www.aisino.com
 */
public class JsoupUtils {


    private static int count = 0;

    /**
     * 通过URL地址获得浏览器对象
     * @param htmlUrl
     * @return
     */
    public static Document getHtmlDocument(String htmlUrl) {
        Document doc = null;
        try {
            int x = htmlUrl.indexOf("https://");
            if (x < 0) {
                htmlUrl = "https://" + htmlUrl;
            }
            doc = Jsoup.connect(htmlUrl).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    /**
     * 通过图片地址将图片保存到本地磁盘，默认图片格式为jpg
     * @param Url 图片路径
     * @param savaAddress 保存地址
     * @param ImgName 图片名称
     * @throws Exception
     */
    public static void outPutStreamImg(String Url, String savaAddress, String ImgName)
            throws Exception {
        checkFile(savaAddress);
        URL url = new URL(Url);
        // 打开网络输入流
        DataInputStream dis = new DataInputStream(url.openStream());
        String newImageName = savaAddress + "/" + ImgName + ".jpg";
        // 建立一个新的文件
        FileOutputStream fos = new FileOutputStream(new File(newImageName));

        byte[] buffer = new byte[1024];
        int length;
        // 开始填充数据
        while ((length = dis.read(buffer)) > 0) {
            fos.write(buffer, 0, length);
        }
        count++;
        System.out.println("当前是第：" + count + "张图片，名称：" + ImgName);
        dis.close();
        fos.close();
    }

    /**
     * 检查文件夹是否存在，不存在则自动创建
     * @param fileUrl
     */
    public static void checkFile(String fileUrl) {
        File file = new File(fileUrl);
        //如果文件夹不存在则创建
        if (!file.exists() && !file.isDirectory()) {
            System.out.println("//不存在");
            file.mkdir();
        }
    }

}

