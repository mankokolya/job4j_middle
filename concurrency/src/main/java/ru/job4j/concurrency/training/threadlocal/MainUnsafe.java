package ru.job4j.concurrency.training.threadlocal;

import java.util.concurrent.TimeUnit;

public class MainUnsafe {
    public static void main(String[] args) {
        UnsafeTask task = new UnsafeTask();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
            try{
                TimeUnit.SECONDS.sleep(2);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
