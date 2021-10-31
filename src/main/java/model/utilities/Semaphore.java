package model.utilities;

public class Semaphore {
    private boolean repair = true;

    public synchronized void startRepair() throws InterruptedException {
        while (!repair) wait();
    }

    public synchronized void stopRepair() {
        repair = false;
        notifyAll();
    }

    public synchronized void startClean() throws InterruptedException {
        while (repair) wait();
    }

    public synchronized void stopClean() {
        repair = true;
        notifyAll();
    }
}
