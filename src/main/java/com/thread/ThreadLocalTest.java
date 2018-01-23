package com.thread;


import java.util.Random;

import static java.util.concurrent.atomic.AtomicIntegerFieldUpdater.newUpdater;

/**
 * @author：Kyle.yangkg
 * @create：2017-09-06 下午 3:41
 * ©copyright：www.aisino.com
 */
public class ThreadLocalTest {


    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    static ThreadLocal<MyDataThreadScopeData> threadLocalMyData = new ThreadLocal<>();
    public static void main(String[] args) {
        newUpdater(MyDataThreadScopeData.class,"age");
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() +" has put data:" + data);
                    threadLocal.set(data);

                    //MyDataThreadScopeData threadScopeData = new MyDataThreadScopeData();
                    //threadScopeData.setName("name"+data);
                    //threadScopeData.setAge(data);
                    MyDataThreadScopeData.getInstance().setName("name"+data);
                    MyDataThreadScopeData.getInstance().setAge(data);

                    threadLocalMyData.set(MyDataThreadScopeData.getInstance());
                    new A().get();
                    new B().get();
                }
            }).start();

        }
    }

    static class  A {
        public void get (){
            int data = threadLocal.get();
            System.out.println("A from " + Thread.currentThread().getName() +" has get data:" + data);
            //MyDataThreadScopeData myData = threadLocalMyData.get();
            MyDataThreadScopeData myData = MyDataThreadScopeData.getInstance();
            System.out.println("A from " + Thread.currentThread().getName() +" has get mydata:" + myData.getName() +myData.getAge());

        }
    }

    static class B{
        public void get (){
            int data = threadLocal.get();
            System.out.println("B from " + Thread.currentThread().getName() +" has get data:" + data);
            //MyDataThreadScopeData myData = threadLocalMyData.get();
            MyDataThreadScopeData myData = MyDataThreadScopeData.getInstance();
            System.out.println("B from " + Thread.currentThread().getName() +" has get mydata:" + myData.getName() +myData.getAge());
        }
    }
}

class MyDataThreadScopeData{

    private String name ;
    private int age ;

    private MyDataThreadScopeData(){

    }
    //懒汉式
    /*public  static MyDataThreadScopeData getInstance(){

        return  instance ;
    }

    private static MyDataThreadScopeData instance = new MyDataThreadScopeData();*/

    //饿汉式
   /* public synchronized static MyDataThreadScopeData getInstance(){

        if (instance == null ){
            instance = new MyDataThreadScopeData();
        }

        return  instance ;
    }

    private static MyDataThreadScopeData instance = null ;*/

    public synchronized static MyDataThreadScopeData getInstance(){
        MyDataThreadScopeData instance = map.get();

        if (instance == null ){
            instance = new MyDataThreadScopeData();
            map.set(instance);
        }

        return  instance ;
    }

    //private static MyDataThreadScopeData instance = null ;


    private static ThreadLocal<MyDataThreadScopeData> map =  new ThreadLocal<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
