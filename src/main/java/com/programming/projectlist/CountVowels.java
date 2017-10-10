package com.programming.projectlist;

import java.util.Scanner;

;
;

/**
 * 统计元音字母
 * 输入一个字符串，统计处其中元音字母的数量。更复杂点的话统计出每个元音字母的数量。
 *
 * @author：Kyle.yangkg
 * @create：2017-08-07 上午 11:24
 * ©copyright：www.aisino.com
 */
public class CountVowels {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String line = scanner.nextLine();
        System.out.println(countVowels(line));
    }

    private static int countVowels(String line) {
        final char[] standerChar = {'a', 'e', 'i', 'o', 'u'};
        int count = 0;
        char[] array = line.toCharArray();

        for (int i = 0; i <array.length; i++) {

            switch (array[i]) {
                case 'a':
                    count++;
                    break;
                case 'e':
                    count++;
                    break ;
                case 'i':
                    count++;
                    break ;
                case 'o':
                    count++;
                    break ;
                case 'u':
                    count++;
                    break;

            }
        }

        return count;

    }
}
