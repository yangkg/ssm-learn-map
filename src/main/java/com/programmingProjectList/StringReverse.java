package com.programmingProjectList;

;
;

/**
 * 逆转字符串
 *
 * @author：Kyle.yangkg
 * @create：2017-08-07 上午 10:05
 * ©copyright：www.aisino.com
 */
public class StringReverse {
    public static void main(String[] args) {
        String string = "hello world！";
        StringBuffer sb  = new StringBuffer(string);
        String strReverse = sb.reverse().toString();
        System.out.println(strReverse);
    }
}
