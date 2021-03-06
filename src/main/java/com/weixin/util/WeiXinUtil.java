package com.weixin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.weixin.menu.Button;
import com.weixin.menu.ClickButton;
import com.weixin.menu.Menu;
import com.weixin.menu.ViewButton;
import com.weixin.pojo.AccessToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


/**
 * 微信工具类
 *
 * @author：Kyle.yangkg
 * @create：2017-08-11 下午 4:50
 * ©copyright：www.aisino.com
 */
public class WeiXinUtil {
    private static final String APPID = "wxe1a35fea13b9811b";
    //AppSecret = 28277424282c90a5ac20d10e0046f1eb;//瑞和轩
    private static final String APPSECRET = "65541305ec00f1d748e5225d8c77f4f6";

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token";//?grant_type=client_credential&appid=APPID&secret=APPSECRET

    private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";

    private static final String DELETE_MENU_URL ="https://api.weixin.qq.com/cgi-bin/menu/delete";


    /**
     * 获取accessToken
     *
     * @return AccessToken
     */
    public static AccessToken getAccessToken() {
        AccessToken token = new AccessToken();
        String param = "grant_type=client_credential" + "&appid=" + APPID + "&secret=" + APPSECRET;
        String response = HttpRequest.sendGet(ACCESS_TOKEN_URL, param);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject != null) {
            token.setAccess_token(jsonObject.getString("access_token"));
            token.setExpires_in(jsonObject.getIntValue("expires_in"));
        }
        return token;
    }


    /**
     * 文件上传
     *
     * @param filePath
     * @param accessToken
     * @param type
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchProviderException
     * @throws KeyManagementException
     */
    public static String upload(String filePath, String accessToken, String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }

        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);

        URL urlObj = new URL(url);
        //连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);

        //设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");

        //设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        //获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        //输出表头
        out.write(head);

        //文件正文部分
        //把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();

        //结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

        out.write(foot);

        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            //定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        JSONObject jsonObj = JSON.parseObject(result);
        System.out.println(jsonObj);
        String typeName = "media_id";
        if (!"image".equals(type)) {
            typeName = type + "_media_id";
        }
        String mediaId = jsonObj.getString(typeName);
        return mediaId;
    }


    /**
     * 组装菜单
     *
     * @return
     */
    public static Menu initMenu() {
        Menu menu = new Menu();

        ClickButton clickButton = new ClickButton();
        clickButton.setName("click菜单");
        clickButton.setType("click");
        clickButton.setKey("11");

        ViewButton viewButton = new ViewButton();

        viewButton.setName("view菜单");
        viewButton.setType("view");
        //注意：这个URL必须是完整的，包括协议（http）
        viewButton.setUrl("http://www.imooc.com");

        ClickButton clickButton2 = new ClickButton();
        clickButton2.setName("扫码事件");
        clickButton2.setType("scancode_push");
        clickButton2.setKey("31");

        ClickButton clickButton3 = new ClickButton();
        clickButton3.setName("地理位置");
        clickButton3.setType("location_select");
        clickButton3.setKey("32");

        Button button = new Button();
        button.setName("菜单");
        button.setSub_button(new Button[]{clickButton2, clickButton3});

        menu.setButton(new Button[]{clickButton, viewButton, button});
        return menu;

    }

    public static int createMenu(String token, String menu) {
        int result = 0;
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        String response = HttpRequest.sendPost(url, menu);
        JSONObject jsonObject = JSON.parseObject(response);

        if (null != jsonObject) {
            result = jsonObject.getIntValue("errcode");
        }

        return result;

    }

    public static JSONObject queryMenu(String token) {
        String param = "access_token=" + token;
        String response = HttpRequest.sendGet(QUERY_MENU_URL, param);
        JSONObject jsonObject = JSON.parseObject(response);
        return jsonObject;

    }

    public static int deleteMenu(String token) {
        int result = 0 ;
        String param = "access_token=" + token;
        String response = HttpRequest.sendGet(DELETE_MENU_URL, param);
        JSONObject jsonObject = JSON.parseObject(response);
        if (null != jsonObject ){
            result = jsonObject.getIntValue("errcode");
        }
        return result;

    }



}
