package com.thread;

;
;import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程范围内的共享数据
 *
 * @author：Kyle.yangkg
 * @create：2017-09-05 下午 5:04
 * ©copyright：www.aisino.com
 */
public class ThreadScopeShareData {

    public static int data  = 0 ;
    public static Map<String,Integer> sharedData = new HashMap<>();
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() +" has put data:" + data);
                    sharedData.put(Thread.currentThread().getName(),data);
                    new A().get();
                    new B().get();
                }
            }).start();

        }
    }

    static class  A {
        public void get (){
            int data = sharedData.get(Thread.currentThread().getName());
            System.out.println("A from " + Thread.currentThread().getName() +" has get data:" + data);

        }
    }

    static class B{
        public void get (){
            int data = sharedData.get(Thread.currentThread().getName());
            System.out.println("B from " + Thread.currentThread().getName() +" has get data:" + data);
        }
    }
}
