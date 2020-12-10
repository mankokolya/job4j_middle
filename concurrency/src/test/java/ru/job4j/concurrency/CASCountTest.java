package ru.job4j.concurrency;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    @Test
    public void when2ThreadsIncrement10And5ThenResult15() throws InterruptedException {
        final AtomicInteger result = new AtomicInteger();
        final CASCount count = new CASCount(0);

        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        count.increment();
                    }
                }
        );
        producer.start();

        Thread producer2 = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        count.increment();
                    }
                }
        );
        producer2.start();

        Thread consumer = new Thread( () -> result.set(count.get()));

        producer.join();
        producer2.join();
        consumer.start();
        consumer.join();

        assertThat(result.get(), is(15));
    }
}