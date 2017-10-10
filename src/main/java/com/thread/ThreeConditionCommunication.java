package com.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

;
;

/**
 * 传统线程间的通讯
 *
 * @author：Kyle.yangkg
 * @create：2017-09-05 下午 4:09
 * ©copyright：www.aisino.com
 */
public class ThreeConditionCommunication {

    public static void main(String[] args) {
        Business business = new ThreeConditionCommunication.Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    business.one(i);

                }
            }
        }).start();

        for (int i = 0; i < 20; i++) {
            business.tow(i);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    business.three(i);

                }
            }
        }).start();
    }

    static class Business {
        boolean bsShouldSbu = true;

        final Lock lock = new ReentrantLock();
        final Condition condition1 = lock.newCondition();
        final Condition condition2 = lock.newCondition();
        final Condition condition3 = lock.newCondition();

        private int condition = 1;

        public void tow(int i) {
            lock.lock();

            try {
                while (condition != 2) {
                    //this.wait();
                    try {
                        condition2.await();
                        //i = i / 2;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 5; j++) {
                    System.out.println("tow thread sequence of :" + j + ",loop of " + i);
                }
                condition = 3;
                //this.notify();
                condition3.signal();
            } finally {
                lock.unlock();
            }
        }

        public void three(int i) {
            lock.lock();
            try {
                while (condition != 3) {
                    //this.wait();
                    try {
                        condition3.await();
                        //i = i / 3;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                for (int j = 0; j < 10; j++) {
                    System.out.println("three thread sequence of :" + j + ",loop of " + i);
                }

                condition = 1;
                //this.notify();
                condition1.signal();
            } finally {
                lock.unlock();

            }
        }

        public void one(int i) {
            lock.lock();
            try {
                while (condition != 1) {
                    //this.wait();
                    try {
                        condition1.await();
                        //i = i / 3;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                for (int j = 0; j < 15; j++) {
                    System.out.println("one thread sequence of :" + j + ",loop of " + i);
                }

                condition = 2;
                //this.notify();
                condition2.signal();
            } finally {
                lock.unlock();

            }
        }
    }

}




