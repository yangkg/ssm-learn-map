package com.thread;

;
;

/**
 * 传统的线程
 *
 * @author：Kyle.yangkg
 * @create：2017-09-05 上午 9:38
 * ©copyright：www.aisino.com
 */
public class TraditionalThread {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1" + Thread.currentThread().getName());
                    System.out.println("2" + this.getName());
                }
            }
        };
        thread.start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1" + Thread.currentThread().getName());
                }
            }
        });

        thread2.start();

        /**
         * 如果不覆盖父类的run方法就不会找runnable的run方法
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Runnable" + Thread.currentThread().getName());
                }
            }
        }) {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread" + Thread.currentThread().getName());
                }
            }
        }.start();


    }
}
