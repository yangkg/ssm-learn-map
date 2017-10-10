package com.thread;

;
;

/**
 * 传统线程间的通讯
 *
 * @author：Kyle.yangkg
 * @create：2017-09-05 下午 4:09
 * ©copyright：www.aisino.com
 */
public class TraditionalThreadCommunication {

    public static void main(String[] args) {
        Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    business.sub(i);

                }
            }
        }).start();

        for (int i = 0; i < 20; i++) {
            business.main(i);
        }
    }


}


class Business {
    boolean bsShouldSbu = true;

    public synchronized void sub(int i) {
        while (!bsShouldSbu) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 0; j < 10; j++) {
            System.out.println("sub thread sequence of :" + j + ",loop of " + i);
        }
        bsShouldSbu = false;
        this.notify();
    }

    public synchronized void main(int i) {
        while (bsShouldSbu) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        for (int j = 0; j < 10; j++) {
            System.out.println("main thread sequence of :" + j + ",loop of " + i);
        }

        bsShouldSbu = true;
        this.notify();
    }
}
