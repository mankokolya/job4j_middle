package ru.job4j.concurrency.waitnotify;

public class CountBarrier {
    private final Object monitor = this;
    private final int total;
    private int count = 0;
    private boolean flag = false;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            if (count++ == total) {
                flag = true;
                monitor.notifyAll();
            }
        }
    }

    public void await() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
