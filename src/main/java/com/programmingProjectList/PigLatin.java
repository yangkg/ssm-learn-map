package com.programmingProjectList;

;
;

/**
 * 拉丁猪文字游戏——这是一个英语语言游戏。
 * 基本规则是将一个英语单词的第一个辅音音素的字母移动到词尾并且加上后缀-ay
 * （譬如“banana”会变成“anana-bay”）:除了a、e、i、o、u,其余都是辅音
 *
 * @author：Kyle.yangkg
 * @create：2017-08-07 上午 10:16
 * ©copyright：www.aisino.com
 */
public class PigLatin {
    public static void main(String[] args) {
        String string = "banana";
        String pigLatin = changeStr(string);
        System.out.println(pigLatin);

    }

    private static String changeStr(String string) {
        StringBuilder result = new  StringBuilder(string);
        char[] orgCharArr = string.toCharArray();
        char[] standerArr = {'a', 'e', 'i', 'o', 'u'};
        boolean flag = true;

        for (int i = 0; i < standerArr.length; i++) {
            if (orgCharArr[0] == standerArr[i]) {
                flag = false;
                break ;
            }
        }

        if (flag) {
            StringBuilder tail = new StringBuilder().append("-").append(orgCharArr[0]).append("ay");
            StringBuilder head = new StringBuilder(string);

            result = head.append(tail) ;


        }

        String  finalresult = result.substring(1,result.length());

        return finalresult;
    }
}
