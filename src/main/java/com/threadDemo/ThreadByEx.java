package com.threadDemo;

;
;

/**
 * 继承Thread类重写run方法
 *
 * @author：Kyle.yangkg
 * @create：2017-08-02 上午 9:10
 * ©copyright：www.aisino.com
 */
public class ThreadByEx  extends Thread{
    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     */
    @Override
    public void run() {
        System.out.println("I'm a thread that extends Thread.");
    }
}
