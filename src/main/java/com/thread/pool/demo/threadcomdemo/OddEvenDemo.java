package com.thread.pool.demo.threadcomdemo;

public class OddEvenDemo {
    private int i = 0;

    private Object object = new Object();

    // 奇数线程执行 打印奇数
    public void odd() {
        while (i < 10) {
            synchronized (object) {
                if (i % 2 == 1) {
                    System.out.println("奇数" + i);
                    i++;
                    object.notify();
                } else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void even() {
        while (i < 10) {
            synchronized (object) {
                if (i % 2 == 0) {
                    System.out.println("偶数" + i);
                    i++;
                    object.notify();
                } else {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        OddEvenDemo oddEvenDemo = new OddEvenDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenDemo.odd();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenDemo.even();
            }
        }).start();
    }
}
