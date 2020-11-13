package ru.job4j.concurrency.nonblockingcache;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final ConcurrentHashMap<Integer, Base> cache;

    public Cache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public void add(Base model) {
        cache.putIfAbsent(model.getId(), model);
    }

    public void update(Base model) {
        int id = model.getId();
        cache.computeIfPresent(id, (key, value) -> {
            model.setVersion(cache.get(id).getVersion());

            return model;
        });
    }

    public void delete(Base model) {
        if (cache.contains(model)) {
            cache.remove(model.getId());
        }
    }

    public Map<Integer, Base> get() {
        return Collections.unmodifiableMap(cache);
    }
}
