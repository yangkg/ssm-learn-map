package com.weixin;

import com.alibaba.fastjson.JSON;
import com.weixin.menu.Menu;
import com.weixin.pojo.AccessToken;
import com.weixin.util.WeiXinUtil;

public class WeixinTest {
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

            //int result = WeiXinUtil.deleteMenu(token.getAccess_token());
            //if (0 == result){
            //    System.out.println("菜单删除成功");
            //}else {
            //    System.out.println("菜单创建失败,失败码为："+result);
            //}

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
