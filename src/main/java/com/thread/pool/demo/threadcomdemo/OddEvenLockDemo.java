package com.thread.pool.demo.threadcomdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class OddEvenLockDemo {

    private int i = 0;

    Lock lock = new ReentrantLock(); // 独占锁

    Condition condition = lock.newCondition();

    // 奇数线程执行 打印奇数
    public void odd() {
        while (i < 10) {
            try {
                lock.lock();
                if (i % 2 == 1) {
                    System.out.println("奇数" + i);
                    i++;
                    condition.signal();
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public void even() {
        while (i < 10) {
            try {
                lock.lock();
                if (i % 2 == 0) {
                    System.out.println("偶数" + i);
                    i++;
                    condition.signal();
                } else {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenLockDemo oddEvenLockDemo = new OddEvenLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenLockDemo.odd();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenLockDemo.even();
            }
        }).start();
    }
}
