package ru.job4j.concurrency.nonblockingcache;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

public class Base {

    private final int id;
    private int version;

    public Base(int id) {
        this.id = id;
        this.version = 1;
    }

    public void setVersion() {
       this.version++;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }
}
