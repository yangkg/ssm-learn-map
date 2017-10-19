package com.yangkg.utilsTest;

;
;

/**
 * @author：Kyle.yangkg
 * @create：2017-10-12 上午 10:24
 * ©copyright：www.aisino.com
 */
public class TestMM {
    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
               pong();
            }
        };

        t.run() ;
        System.out.print("ping");
    }

    static void pong(){
        System.out.print("pong");
    }
}
