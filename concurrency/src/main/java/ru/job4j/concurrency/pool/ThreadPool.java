package ru.job4j.concurrency.pool;

import ru.job4j.concurrency.blockingqueue.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<ThreadImplementation> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;
    private boolean isStopped = false;

    public ThreadPool() {
        final int size = Runtime.getRuntime().availableProcessors();
        this.tasks = new SimpleBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            threads.add(new ThreadImplementation(tasks));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void work(Runnable job) throws InterruptedException {
        if (isStopped){
            throw new IllegalStateException("Thread pool is stopped");
        }
        tasks.offer(job);
    }

    public void shutdown() {
        this.isStopped = true;
        for (ThreadImplementation thread : threads) {
            thread.doStop();
        }
    }
}
