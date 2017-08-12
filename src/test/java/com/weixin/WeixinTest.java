package com.weixin;

import com.weixin.pojo.AccessToken;
import com.weixin.util.WeiXinUtil;

public class WeixinTest {
    public static void main(String[] args) {
        try {
            AccessToken token = WeiXinUtil.getAccessToken();
            System.out.println("票据" + token.getAccess_token());
            System.out.println("有效时间" + token.getExpires_in());

            String path = "D:\\xiuxiu.jpg";
            String mediaId = WeiXinUtil.upload(path, token.getAccess_token(), "thumb");
            System.out.println(mediaId);

            //String result = WeiXinUtil.translate("my name is laobi");
            //String result = WeixinUtil.translateFull("");
            //System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
