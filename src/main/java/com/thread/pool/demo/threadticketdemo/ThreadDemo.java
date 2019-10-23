package com.thread.pool.demo.threadticketdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadDemo {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        FutureTask<String> futureTask = new FutureTask<String>(new MyCallable());

        for (int i = 0; i < 10; i++) {
            System.out.println("主綫程" + Thread.currentThread().getName() + "执行第" + i + "次！");
        }

        executorService.execute(futureTask);

        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
