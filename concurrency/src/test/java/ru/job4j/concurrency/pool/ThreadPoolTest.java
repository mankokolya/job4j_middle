package ru.job4j.concurrency.pool;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void threadPool() throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            pool.work(() -> System.out.println("Running task " + finalI)
            );
        }
        pool.shutdown();
    }
}