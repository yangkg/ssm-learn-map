package com.thread;

;
;import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 传统定时器
 *
 * @author：Kyle.yangkg
 * @create：2017-09-05 上午 10:10
 * ©copyright：www.aisino.com
 */
public class TraditionalTimer {
    static int count = 0 ;
    public static void main(String[] args) {
        //new Timer().schedule(new TimerTask() {
        //    @Override
        //    public void run() {
        //        System.out.println("boombing");
        //    }
        //}, 1000,3000);

        class MyTimeTasker extends TimerTask {

            @Override
            public void run() {
                count  = (count + 1 )% 2;
                System.out.println("boombing1");
                new Timer().schedule(/*new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("boombing2");
                    }
                },*/ new MyTimeTasker(), 2000+2000*count);
            }
        }

        new Timer().schedule(new MyTimeTasker(), 2000);



        while (true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
