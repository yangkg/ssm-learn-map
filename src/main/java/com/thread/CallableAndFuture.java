package com.thread;

;
;import java.util.Random;
import java.util.concurrent.*;

/**
 * @author：Kyle.yangkg
 * @create：2017-09-07 下午 3:38
 * ©copyright：www.aisino.com
 */
public class CallableAndFuture {

    public static void main(String[] args) {

        //Callable是一个任务，任务执行完后由future获得结果
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "hello world!";
            }
        });
        System.out.println("等待结果");

        try {
            System.out.println("拿到结果"+future.get());
            //System.out.println("拿到结果"+future.get(1,TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }



        //callable和future
        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        CompletionService completionService = new ExecutorCompletionService(threadPool2);
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            completionService.submit(new Callable() {
                @Override
                public Integer call() throws Exception {

                    Thread.sleep(new Random().nextInt(5000));
                    return finalI;
                }
            });

        }

        for (int i = 0; i < 10; i++) {
            try {
                //提交的任务谁先运行完就先取谁
                System.out.println(completionService.take().get());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }



}
