package com.common.utils;

;
;import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 *
 * @author：Kyle.yangkg
 * @create：2017-09-26 下午 3:54
 * ©copyright：www.aisino.com
 */
public class MD5Utils {
    public static String digest(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digests = md5.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte temp : digests) {
                // 去掉负数
                int c = temp & 0xff;// 负数转换成正数
                String result = Integer.toHexString(c);// 把十进制数转换成十六进制数
                if (result.length() < 2) {
                    sb.append(temp);// 让十六进制数全部都是2位数
                }
            }
            return sb.toString();// 加密后的密文
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

            return "";
        }


    }
}
