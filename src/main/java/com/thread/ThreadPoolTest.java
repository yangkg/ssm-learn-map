package com.thread;

;
;import javax.xml.crypto.Data;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 *
 * @author：Kyle.yangkg
 * @create：2017-09-07 下午 2:34
 * ©copyright：www.aisino.com
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //ExecutorService threadPool = Executors.newCachedThreadPool();
        //如何实现线程死掉后重新启动，使用单线程（创建一个线程，当线程死掉后，创建一个线程代替他）
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() +" loop of " + j +" for task of " + finalI);

                    }
                }
            });

        }
        System.out.println("all of 10 task hava commited");
        threadPool.shutdown();

        //定时器
        Executors.newScheduledThreadPool(3).schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("boombing");
            }
        },10, TimeUnit.SECONDS);

        //固定频率的定时器 注意：new Date().getTime() - System.currentTimeMillis();能实现定点时间定时器。
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("boombing");
            }
        },6,2, TimeUnit.SECONDS);

    }

}