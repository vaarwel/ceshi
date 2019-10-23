package com.thread.pool.demo.threadpooldemo;

import java.util.concurrent.CountDownLatch;

public class TestThreadPool {
    public static void main(String[] args) {
        FixedSizeThreadPool pool = new FixedSizeThreadPool(3, 6);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 3; i++) {
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("有一个线程被放入了仓库中");
                    try {
                        Thread.sleep(2000L);
                    } catch (Exception e) {
                        System.out.println("有一个线程被唤醒了");
                    }
                }
            });
        }
        pool.shutDown();
    }
}
