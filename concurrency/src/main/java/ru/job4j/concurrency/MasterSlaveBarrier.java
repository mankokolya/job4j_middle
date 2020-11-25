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

           isPrinting = true;
        }
    }

    public void trySlave() {
        synchronized (this) {
            while (isPrinting) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isPrinting = true;
        }
    }

    public void doneMaster() {
        synchronized (this) {
            isPrinting = false;
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
