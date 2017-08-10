package com.yangkg.utilsTest;

import java.util.Stack;
import java.util.StringTokenizer;

;
;

/**
 * NanoTime时间测试
 *
 * @author：Kyle.yangkg
 * @create：2017-07-28 下午 1:42
 * ©copyright：www.aisino.com
 */
public class nanoTime {

    public static void main(String[] args) {
        //System.out.println(System.nanoTime());
        //System.out.println(System.currentTimeMillis());

        String str = "whatever string something";

        StringBuffer sb  = new StringBuffer(str);

        String resutlt = sb.reverse().toString();

        //gnihtemos gnirts revetahw
        System.out.println(resutlt);


        String str1 = "reverse this string hello who";

        Stack<Object> stack = new Stack<>();

        StringTokenizer strTok = new StringTokenizer(str1);

        while(strTok.hasMoreTokens()){

            stack.push(strTok.nextElement());
        }

        StringBuffer str1rev = new StringBuffer();

        while(!stack.empty()){

            str1rev.append(stack.pop());
            str1rev.append(" ");


        }
        //who hello string this reverse
        System.out.println(str1rev);


        String strUpper = str.toUpperCase();
        String strLower = str.toLowerCase();
        System.out.println(strUpper);
        System.out.println(strLower);


        String strSpace = "    how old are you???    ";
        String strTrim = strSpace.trim();
        System.out.println(strTrim);

        String strSpace1 = "how old are you???";
        String strReplace =  strSpace.replace(" ","");
        System.out.println(strReplace);
    }



}
