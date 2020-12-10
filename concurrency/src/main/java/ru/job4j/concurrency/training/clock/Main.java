package ru.job4j.concurrency.training.clock;

import ru.job4j.concurrency.pool.ThreadImplementation;

public class Main {
    public static void main(String[] args) {
        ConsoleClock clock = new ConsoleClock();
        Thread thread = new Thread(clock);
        thread.start();
        thread.interrupt();
    }
}
