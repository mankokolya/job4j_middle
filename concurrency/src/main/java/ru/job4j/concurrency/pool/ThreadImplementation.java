package ru.job4j.concurrency.pool;

import ru.job4j.concurrency.blockingqueue.SimpleBlockingQueue;

public class ThreadImplementation extends Thread {
    private final SimpleBlockingQueue<Runnable> queue;
    private boolean isStopped = false;

    public ThreadImplementation(SimpleBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!isStopped) {
            try {
                Runnable runnable = queue.poll();
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doStop() {
        this.isStopped = true;
        this.interrupt();
    }

    public synchronized boolean isStopped() {
        return this.isStopped;
    }
}
