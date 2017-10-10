package com.thread;

;
;import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 传统的线程同步
 *
 * @author：Kyle.yangkg
 * @create：2017-09-05 下午 2:29
 * ©copyright：www.aisino.com
 */
public class LockTest {
    public static void main(String[] args) {
        LockTest threadSynchronized = new LockTest();
        threadSynchronized.init();

    }

    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("yangkaiguo");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //new Outputer().output("yangliubin");这样用的也不是同一个对象，所以没有同步的效果
                    outputer.output("yangliubin");
                }
            }
        }).start();
    }

    /*class Outputer {
        //直接加在方法上，synchronized在同一段代码上只能用一次，不然会出现死锁
        //方法上的synchronized用的是this对象，所以他和在代码块上加synchronized (this) 能实现同步
        public synchronized void output(String name) {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }*/

    /*class Outputer {
        //互斥的一定是要用同一个对象（同一把锁）才可以，这里用的是yangkaiguo，和yangliubin所以不是同一个对象
        public void output(String name) {
            synchronized (name) {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();

            }
        }
    }*/

    /*class Outputer {
        //互斥的一定是要同一个对象才可以，这里用的是xxx这个对象，不管谁进来用的都是这个对象，所以能起到同步的作用
        String xxx = "";
        public void output(String name) {
            synchronized (xxx) {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();

            }
        }
    }*/

    static class Outputer {
       /* //互斥的一定是要同一个对象才可以，这里用的是this
        String xxx = "";
        public void output(String name) {
            synchronized (Outputer.class) {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }

        //直接加在方法上，synchronized在同一段代码上只能用一次，不然会出现死锁
        //方法上的synchronized用的是this对象，所以他和在代码块上加synchronized (this) 能实现同步
        public synchronized void output2(String name) {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }

        //output3能和output实现同步吗？答案是不行，要实现同步，可以讲output的this改为Outputer.class;
        //因为静态方法只有和字节码那个对象关联才是同一对象
        public static synchronized void output3(String name) {
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }*/

        Lock lock = new ReentrantLock();

        public void output(String name) {
            lock.lock();
            try {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                lock.unlock();
            }


        }
    }
}


