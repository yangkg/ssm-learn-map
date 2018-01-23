package com.programming.projectlist;

;
;import java.util.Scanner;

/**
 * 判断是否为回文
 *判断用户输入的字符串是否为回文。回文是指正反拼写形式都是一样的词，譬如“racecar”。
 * @author：Kyle.yangkg
 * @create：2017-08-07 下午 1:34
 * ©copyright：www.aisino.com
 */
public class Palindrome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String line = scanner.nextLine();
        System.out.println("是否为回文："+isPalindrome(line));



    }

    private static boolean isPalindrome(String line) {
        boolean result = false ;
        String reverse = new StringBuffer(line).reverse().toString();
        if (line.equals(reverse)){
            result = true ;
        }

        return result ;
    }

}
