package com.thread.pool.demo.threaddeadlockdemo;

public class MainDemo {
    public static void main(String[] args) {
        ThreadDeadLockDemo threadDeadLockDemo1 = new ThreadDeadLockDemo(1);
        ThreadDeadLockDemo threadDeadLockDemo2 = new ThreadDeadLockDemo(2);

        new Thread(threadDeadLockDemo1,"runnable1").start();
        new Thread(threadDeadLockDemo2,"runnable2").start();

    }
}
