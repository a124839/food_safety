package com.ichinait.food.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by IChinait on 2016/6/22.
 */
public abstract class Worker<T> implements Runnable{
    private final BlockingQueue<T> queue;
    private final CountDownLatch latch;

    public Worker() {
        this.queue = null;
        this.latch = null;
    }

    public Worker(BlockingQueue<T> queue, CountDownLatch latch) {
        this.queue = queue;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            while(true){
                doWork(queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public abstract void doWork(T t);
}
