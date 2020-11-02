package ru.job4j.concurrency;

public class DCLSingleton {
    private volatile static DCLSingleton inst;

    public static DCLSingleton instOf() {
        if (inst == null) {
            inst = new DCLSingleton();
        }
        return inst;
    }

    private DCLSingleton() {
    }
}
