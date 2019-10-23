package com.thread.pool.demo.threadticketdemo;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("线程" + Thread.currentThread().getName() + "执行第" + i + "次！");
        }
        return "MyCallable执行完成";
    }
}
