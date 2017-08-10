package com.threadDemo;

;
;

/**
 * 1.什么是线程
 *  在现代操作在运行一个程序时,会为其创建一个进程，例如启动一个QQ程序，操作系统会为其创建一个进程，而
 *  操作系统中调度的最小单元就是线程，也叫轻量级进程，在一个程序里可以创建多个线程，这些线程都有各自的
 *  计数器，堆栈和局部变量等属性，并且能够访问共享的内存变量。处理器在这些线程上高速切换，让使用者感觉
 *  这些线程在同时执行，因此我们可以这样理解：
 *  1）进程：正在运行的程序，是系统进行资源分配和调用的独立单位，每个进程都有它独立的内存空间和系统资源。
 *  2）线程：是进程中的单个执行流，是一条执行路径，一个程序如果只有一个执行流，则称为单线程程序。一个程
 *      序如果有多个执行路径，则称为多线程程序。
 *
 * 2.创建线程有两种方法，一种是集成Thread类，重写run方法，另一种是集成Runnable借口重写run方法，
 *
 * @author：Kyle.yangkg
 * @create：2017-07-28 下午 2:30
 * ©copyright：www.aisino.com
 */
public class ThreadDemo {
    public static void main(String[] args) {
        //继承Thread启动的方法
        ThreadByEx threadByEx = new ThreadByEx();
        //启动线程
        threadByEx.start();

        //实现Runnable启动线程的方法
        ThreadByRunnable runnable = new ThreadByRunnable();
        Thread thread = new Thread(runnable);
        //启动线程
        thread.start();

        //这是匿名内部类启动线程的方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是匿名内部类启动线程的方法");
            }
        }).start();
        





    }

}
