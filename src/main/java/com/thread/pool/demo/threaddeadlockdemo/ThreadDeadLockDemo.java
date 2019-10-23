package com.thread.pool.demo.threaddeadlockdemo;

public class ThreadDeadLockDemo implements Runnable {

    private int flag;

    private  Object object1 = new Object();
    private  Object object2 = new Object();

    public ThreadDeadLockDemo(int flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag==1){
            synchronized (object1){
                System.out.println(Thread.currentThread().getName()+"已经获取锁obj1"+ "正在尝试获取obj2");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (object2){
                    System.out.println(Thread.currentThread().getName()+"已经获取锁obj1和obj2");
                }
            }

        }else{
            synchronized (object2){
                System.out.println(Thread.currentThread().getName()+"已经获取锁obj2"+ "正在尝试获取obj1");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (object1){
                    System.out.println(Thread.currentThread().getName()+"已经获取锁obj2和obj1");
                }
            }
        }
    }
}
