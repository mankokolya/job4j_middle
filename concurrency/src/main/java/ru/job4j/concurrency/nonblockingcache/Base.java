package ru.job4j.concurrency.nonblockingcache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class Base {

    private final int id;
    private final AtomicInteger version;

    public Base(int id) {
        this.id = id;
        this.version = new AtomicInteger(1);
    }

    public void setVersion() {
       this.version.getAndIncrement();
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version.get();
    }
}
