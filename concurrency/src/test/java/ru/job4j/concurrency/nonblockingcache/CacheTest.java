package ru.job4j.concurrency.nonblockingcache;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class CacheTest {
    @Test
    public void add() throws InterruptedException {
        Cache cache = new Cache();
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        cache.add(new Base(i, 1));
                    }
                    cache.update(new Base(5, 1));

                }
        );
        thread1.start();
        thread1.join();

        Thread thread2 = new Thread(
                () -> {
                    try {
                        cache.update(new Base(5, 1));
                    } catch (RuntimeException e) {
                        ex.set(e);
                    }

                }
        );
        thread2.start();
        thread2.join();

        assertThat(ex.get().getMessage(), is("You are trying to modify old value"));
    }

}