package com.common.utils;

;
;

/**
 * 错误信息枚举类
 *
 * @author：Kyle.yangkg
 * @create：2017-12-15 上午 10:21
 * ©copyright：www.aisino.com
 */
public enum ErrorEnum {
    UNKNOW_ERROR("9999", "未知异常"),
    SUCCESS("0000", "操作成功");

    private String retCode;

    private String retMsg;

    ErrorEnum(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public String getRetCode() {
        return retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public static void main(String[] args) {
        System.out.println(UNKNOW_ERROR.getRetCode() == "9999");
    }
}
