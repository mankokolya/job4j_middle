package ru.job4j.concurrency.pool;

import ru.job4j.concurrency.blockingqueue.SimpleBlockingQueue;

public class ThreadImplementation extends Thread {
    private final SimpleBlockingQueue<Runnable> queue;

    public ThreadImplementation(SimpleBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Runnable runnable = queue.poll();
                runnable.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void doStop() {
        this.interrupt();
    }
}
