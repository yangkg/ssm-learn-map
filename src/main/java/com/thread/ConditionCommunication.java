package com.thread;

;
;import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统线程间的通讯
 *
 * @author：Kyle.yangkg
 * @create：2017-09-05 下午 4:09
 * ©copyright：www.aisino.com
 */
public class ConditionCommunication {

    public static void main(String[] args) {
        Business business = new ConditionCommunication.Business();
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

    static class Business {
        boolean bsShouldSbu = true;

        final Lock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        //final Condition notEmpty = lock.newCondition();


        public void sub(int i) {
            lock.lock();

            try {
                while (!bsShouldSbu) {
                    //this.wait();
                    try {
                        condition.await();
                        //i = i / 2;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 0; j < 10; j++) {
                    System.out.println("sub thread sequence of :" + j + ",loop of " + i);
                }
                bsShouldSbu = false;
                //this.notify();
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void main(int i) {
            lock.lock();
            try {
                while (bsShouldSbu) {
                    //this.wait();
                    try {
                        condition.await();
                        //i = i / 3;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                for (int j = 0; j < 10; j++) {
                    System.out.println("main thread sequence of :" + j + ",loop of " + i);
                }

                bsShouldSbu = true;
                //this.notify();
                condition.signalAll();
            } finally {
                lock.unlock();

            }
        }
    }

}

/**
 * 缓冲实现
 *
 */
class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}




