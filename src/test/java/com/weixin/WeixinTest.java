package com.weixin;

import com.alibaba.fastjson.JSON;
import com.weixin.menu.Menu;
import com.weixin.pojo.AccessToken;
import com.weixin.util.TransApi;
import com.weixin.util.WeiXinUtil;

public class WeixinTest {
    // 在平台申请的APP_ID 详见 http://api.fanyi.baidu.com/api/trans/product/desktop?req=developer
    private static final String APP_ID = "20170814000072485";
    private static final String SECURITY_KEY = "_VfcCfTfnQzt8TpF7Aah";

    public static void main(String[] args) {
        try {
            AccessToken token = WeiXinUtil.getAccessToken();
            System.out.println("票据" + token.getAccess_token());
            System.out.println("有效时间" + token.getExpires_in());

            //String path = "D:\\xiuxiu.jpg";
            //String mediaId = WeiXinUtil.upload(path, token.getAccess_token(), "thumb");
            //System.out.println(mediaId);

            //String result = WeiXinUtil.translate("my name is laobi");
            //String result = WeixinUtil.translateFull("");
            //System.out.println(result);
            //
            Menu menu = WeiXinUtil.initMenu();
            String menuJson = JSON.toJSONString(menu);
            int result = WeiXinUtil.createMenu(token.getAccess_token(), menuJson);
            if (0 == result){
                System.out.println("创建菜单成功");

            }else {
                System.out.println("创建菜单失败,失败码为："+result);
            }

            //JSONObject jsonObject = WeiXinUtil.queryMenu(token.getAccess_token());
            //System.out.println(jsonObject);
            //
            //int result = WeiXinUtil.deleteMenu(token.getAccess_token());
            //if (0 == result){
            //    System.out.println("菜单删除成功");
            //}else {
            //    System.out.println("菜单创建失败,失败码为："+result);
            //}

            //TransApi api = new TransApi(APP_ID, SECURITY_KEY);
            //
            //String query = "中国足球";
            //System.out.println(api.getTransResult(query, "auto", "en"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
