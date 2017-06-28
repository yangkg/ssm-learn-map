package com.yangkg.ssm.common.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StringUtil {

    private static final Logger log = LoggerFactory.getLogger(StringUtil.class);

    /**
     * 判断字符串是否为空或null
     *
     * @param source
     * @return
     */
    public static boolean checkStrIsEmpty(String source) {
        return StringUtils.isEmpty(source);
    }

    /**
     * 判断字符串是否等于  长度
     *
     * @param source
     * @param length
     * @return
     */
    public static boolean checkStrLength(String source, int length) {
        if (checkStrIsEmpty(source)) {
            return false;
        }
        return source.length() == length;
    }

    /**
     * 判断字符串是否在范围内
     *
     * @param source
     * @param min
     * @param max
     * @return
     */
    public static boolean checkStrLength(String source, int min, int max) {
        if (checkStrIsEmpty(source)) {
            return false;
        }
        int sourceLenth = source.length();
        return sourceLenth >= min && sourceLenth <= max;
    }

    /**
     * 判断字符串不为空时长度是否在范围内
     *
     * @param source
     * @param min
     * @param max
     * @return
     */
    public static boolean checkStrNullOrLength(String source, int min, int max) {
        if (checkStrIsEmpty(source)) {
            return true;
        }
        int sourceLenth = source.length();
        return sourceLenth >= min && sourceLenth <= max;
    }

    /**
     * 获取指定范围内的随机数
     *
     * @param max
     * @return
     */
    public static int getNumRandom(int max) {
        final Random random = new Random();
        return random.nextInt(max);
    }

    /**
     * 获取指定长度的随机数
     *
     * @param length
     * @return
     */
    public static String getNumRandomStr(int length) {
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(getNumRandom(10));
        }
        return result.toString();
    }

    /**
     * 获取带字母的随机数
     *
     * @param length
     * @return
     */
    public static String getCharRandomStr(int length) {
        String randomSource = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] randomChar = randomSource.toCharArray();
        final StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(randomChar[getNumRandom(62)]);
        }
        return result.toString();
    }

    /**
     * 替换字符串内指定的内容
     *
     * @param source
     * @param sourceChar
     * @param targectChar
     * @return
     */
    public static String allReplaceString(String source, String sourceChar, String targectChar) {
        if (!checkStrIsEmpty(source)) {
            return source.replaceAll(sourceChar, targectChar);
        } else {
            log.error("replace source is empty");
            throw new NullPointerException("replace source is empty");
        }
    }

    /**
     * 按长度截取字符串
     *
     * @param source
     * @param start
     * @param end
     * @return
     */
    public static String subString(String source, int start, int end) {
        if (!checkStrIsEmpty(source)) {
            int sourceLength = source.length();
            if (end > sourceLength) {
                log.error("the end length to long of source's length");
                throw new ArrayIndexOutOfBoundsException("the end length to long of source's length");
            }
            return source.substring(start, end);
        } else {
            log.error("subString source is empty");
            throw new NullPointerException("subString source is empty");
        }
    }

    /**
     * Checks if a String is whitespace, empty ("") or null.
     *
     * @param source
     * @return
     */
    public static boolean isBlank(String source) {
        return StringUtils.isBlank(source);
    }

    /**
     * 按照指定字符截取字符串
     *
     * @param source
     * @param regex
     * @return
     */
    public static String[] split(String source, String regex) {
        if (checkStrIsEmpty(source)) {
            throw new NullPointerException("spliit str is null");
        }
        return source.split(regex);
    }

    /**
     * 获取byte内指定长度的内容
     *
     * @param text
     * @param length
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String[] substringToArry(String text, int length) throws UnsupportedEncodingException {
        return substringToArry(text, length, "utf-8");
    }

    /**
     * 截取字符串内容内如数组
     *
     * @param text
     * @param length
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String[] substringToArry(String text, int length, String encode) throws UnsupportedEncodingException {
        if (text == null) {
            return null;
        }
        List<String> strList = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        int currentLength = 0;
        int totalLength = 0;
        for (char c : text.toCharArray()) {
            totalLength += 1;
            currentLength += String.valueOf(c).getBytes(encode).length;
            if (currentLength <= length) {
                sb.append(c);
                if (currentLength == text.getBytes(encode).length || totalLength == text.length()) {
                    strList.add(sb.toString());
                }
            } else {
                strList.add(sb.toString());
                if (currentLength == length) {
                    currentLength = 0;
                } else {
                    sb = new StringBuilder();
                    sb.append(c);
                    currentLength = String.valueOf(c).getBytes(encode).length;
                }
            }

        }
        String[] strs = new String[strList.size()];
        return strList.toArray(strs);

    }

    /**
     * 用gbk编码截取指定长度字符串
     *
     * @param text
     * @param length
     * @return
     */
    public static String substring(String text, int length) {
        try {
            int textLength = text.getBytes("gbk").length;
            if (textLength <= length) {
                return text;
            }
        } catch (Exception e) {
            log.error("截取字符串失败", e);
        }
        return substring(text, length, "gbk");
    }

    /**
     * 传入编码方式截取指定长度字符串
     *
     * @param text
     * @param length
     * @param encode
     * @return
     */
    public static String substring(String text, int length, String encode) {
        if (text == null) {
            return null;
        }
        String desc = "";
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : text.toCharArray()) {
                currentLength += String.valueOf(c).getBytes(encode).length;
                if (currentLength <= length) {
                    sb.append(c);
                    if (currentLength == text.getBytes(encode).length) {
                        desc = sb.toString();
                        break;
                    }
                } else {
                    desc = sb.toString();
                    break;
                }

            }
        } catch (Exception e) {
            log.error("截取字符串失败", e);
        }
        return desc;
    }



}
