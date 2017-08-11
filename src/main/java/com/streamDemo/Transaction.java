package com.streamDemo;

;
;

/**
 * transaction
 *
 * @author：Kyle.yangkg
 * @create：2017-08-11 上午 8:47
 * ©copyright：www.aisino.com
 */
public class Transaction {
    private Trader trader;
    private int year ;
    private int value ;

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
