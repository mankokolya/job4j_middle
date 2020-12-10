package ru.job4j.concurrency;

import net.jcip.annotations.ThreadSafe;
import org.apache.log4j.spi.ThrowableRenderer;
import ru.job4j.concurrency.blockingqueue.SimpleBlockingQueue;

@ThreadSafe
public class ParallelSearch {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

        final Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {

                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();

        Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 5; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );

        producer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
    }
}
