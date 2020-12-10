package ru.job4j.concurrency.nonblockingcache;

public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
