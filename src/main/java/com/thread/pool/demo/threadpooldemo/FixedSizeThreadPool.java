package com.thread.pool.demo.threadpooldemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class FixedSizeThreadPool {

    // 定义一个任务仓库 BlockingQueue
    private BlockingQueue<Runnable> blockingQueue;

    // 线程池
    private List<Thread> workers;

    // 一个人去做事
    private static class Worker extends Thread {
        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            // 希望他能不断的去拿东西
            while (this.pool.isWorking || this.pool.blockingQueue.size() >= 0) {
                Runnable task = null;
                try {
                    if (this.pool.isWorking) {
                        task = pool.blockingQueue.take();
                    } else {
                        task = pool.blockingQueue.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (null != task) {
                    // 如果有任务，工作
                    task.run();
                    System.out.println("线程：" + Thread.currentThread().getName() + "执行完毕!");
                }
            }
        }
    }

    // 初始化线程池
    public FixedSizeThreadPool(int poolSize, int taskSize) {

        if (poolSize <= 0 || taskSize <= 0)
            throw new IllegalArgumentException("参数非法");

        this.blockingQueue = new LinkedBlockingDeque<>(taskSize);   //  仓库大小
        this.workers = Collections.synchronizedList(new ArrayList<>());   // 綫程大小（安全）

        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    // 需要向仓库中放任务(状态：非阻塞)
    public boolean submit(Runnable task) {
        if (isWorking) {
            return this.blockingQueue.offer(task);
        } else {
            return false;
        }
    }

    // 需要向仓库中放任务(状态：阻塞)
    public void execute(Runnable task) {
        try {
            this.blockingQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 需要有一个关闭的方法
    // a 关闭的时候，仓库要停止有新的任务进来
    // b  仓库如果还有东西，要执行完
    // c 如果再去仓库拿东西，就不能阻塞了
    // d 如果还有线程被阻塞，要强行中断
    private volatile boolean isWorking = true;

    public void shutDown() {
        this.isWorking = false;

        for (Thread thread : workers) {
            if (thread.getState().equals(Thread.State.BLOCKED) ||
                    thread.getState().equals(Thread.State.WAITING)){
                thread.interrupt(); // 中断
            }
        }
    }

    public void test1(){

    }
}
