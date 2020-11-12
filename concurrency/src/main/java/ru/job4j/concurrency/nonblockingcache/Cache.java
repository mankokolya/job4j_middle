package ru.job4j.concurrency.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final ConcurrentHashMap<Integer, Base> cache;

    public Cache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public void add(Base model) {
       cache.computeIfAbsent(model.getId(), )
    }

    public void update(Base model) {

    }

    public void delete(Base model) {
        cache.computeIfPresent(model.getId(), )

    }
}
