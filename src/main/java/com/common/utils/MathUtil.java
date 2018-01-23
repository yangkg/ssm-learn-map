package com.common.utils;


import java.math.BigDecimal;

/**
 * Created by sarah.ren on 4/28/2016.
 * <p/>
 * 计算
 */
public final class MathUtil {

    private MathUtil() {
    }

    /**
     * <p>加法运算</p>
     *
     * @param value1
     * @param value2
     * @return double
     */
    public static double add(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.add(b2).doubleValue();
    }

    /**
     * <p>减法运算</p>
     *
     * @param value1
     * @param value2
     * @return double
     */
    public static double subtract(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * <p>乘法运算</p>
     *
     * @param value1
     * @param value2
     * @return double
     */
    public static double multiply(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * <p>放大税率专用</p>
     *
     * @param value1
     * @param value2
     * @return double
     */
    public static int multiply2(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.multiply(b2).intValue();
    }

    /**
     * <p>除法运算</p>
     *
     * @param value1
     * @param value2
     * @param len
     * @return double
     */
    public static double divide(String value1, String value2, int len) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * <p>四舍五入</p>
     *
     * @param d
     * @param len
     * @return double
     */
    public static double round(double d, int len) {
        // 操作
        BigDecimal b1 = BigDecimal.valueOf(d);
        BigDecimal b2 = BigDecimal.ONE;
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，
        // 表示进行四舍五入的操作
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

