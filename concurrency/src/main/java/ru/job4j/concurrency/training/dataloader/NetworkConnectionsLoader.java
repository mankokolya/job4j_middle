package ru.job4j.concurrency.training.dataloader;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionsLoader implements Runnable {
    @Override
    public void run() {
        System.out.printf("Beginning network data sources loading: %s\n", new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("NetworkData sources loading has finished: %s\n", new Date());
    }
}
