package com.streamdemo;

;
;import java.util.Arrays;
import java.util.List;

/**
 * 测试
 *
 * @author：Kyle.yangkg
 * @create：2017-08-11 上午 8:50
 * ©copyright：www.aisino.com
 */
public class TestStream {
    //建立对象
    Trader xiaoming = new Trader("小明", "广州");
    Trader xiaohong = new Trader("小红", "广州");
    Trader xiaohei = new Trader("小黑", "广州");
    Trader xiaobai = new Trader("小白", "肇庆");

    //新建一个交易的集合
    List<Transaction> transactions = Arrays.asList(
            new Transaction(xiaoming, 2017, 300),
            new Transaction(xiaohong, 2016, 1000),
            new Transaction(xiaohong, 2017, 400),
            new Transaction(xiaohei, 2016, 710),
            new Transaction(xiaohei, 2016, 700),
            new Transaction(xiaobai, 2016, 950)
    );



}
