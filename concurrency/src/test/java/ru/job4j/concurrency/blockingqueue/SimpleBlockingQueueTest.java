package ru.job4j.concurrency.blockingqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class SimpleBlockingQueueTest {

    @Test
    public void fetchListOfString() throws InterruptedException {
        final CopyOnWriteArrayList<String> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>();
        final List<String> example = List.of("Nick", "Ira", "Bill", "John");

        Thread producer = new Thread(
                () -> {
                    for (String s : example) {
                        try {
                            queue.offer(s);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        producer.start();

        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
//                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(example));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            queue.offer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
//                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }


    @Test
    public void test2() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        AtomicInteger result = new AtomicInteger();
        Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        Thread consumer = new Thread(
                () -> {
                    try {
                        result.set(queue.poll());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        consumer.start();
        producer.start();
        producer.join();

        assertEquals(5, result.get());

    }
}