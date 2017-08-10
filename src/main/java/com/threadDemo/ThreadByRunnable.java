package com.threadDemo;

;
;

/**
 * 实现Runnable借口重写run方法
 *
 * @author：Kyle.yangkg
 * @create：2017-08-02 上午 9:15
 * ©copyright：www.aisino.com
 */
public class ThreadByRunnable implements  Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("I'm a thread that implements Runnable.");
    }
}
