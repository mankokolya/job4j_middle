package ru.job4j.concurrency;

public class MasterSlaveBarrier {
    private boolean isPrinting = false;

    public void tryMaster() {
        synchronized (this) {
            while (isPrinting) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void trySlave() {
        synchronized (this) {
            while (!isPrinting) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doneMaster() {
        synchronized (this) {
            isPrinting = true;
            this.notifyAll();
        }
    }

    public void doneSlave() {
        synchronized (this) {
            isPrinting = false;
            this.notifyAll();
        }
    }
}
