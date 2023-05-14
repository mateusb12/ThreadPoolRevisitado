import java.util.random.RandomGenerator;

public class Scheduler extends Thread {
    Queue requests;
    Deck[] tasks;
    int nThreads;
    boolean isWorking = true;

    public int searchEmpty() {
        int threadID = RandomGenerator.getDefault().nextInt(this.nThreads);
        for (int i = 0; i < this.nThreads; i++) {
            if (this.tasks[threadID].size < 1) {
                return threadID;
            }
            threadID = (threadID + 1) % this.nThreads;
        }
        return -1;
    }

    public int searchFull() {
        int threadID = RandomGenerator.getDefault().nextInt(this.nThreads);
        for (int i = 0; i < this.nThreads; i++) {
            if (this.tasks[threadID].size > 1) {
                return threadID;
            }
            threadID = (threadID + 1) % this.nThreads;
        }
        return -1;
    }


    synchronized public boolean workStealing() {
        int threadID_full = searchFull();
        if (threadID_full >= 0) {
            int threadID_empty = searchEmpty();
            if (threadID_empty >= 0) {
                this.tasks[threadID_empty].push(this.tasks[threadID_full].popBack());
                return true;
            }
        }
        return false;
    }


    public Scheduler(Queue requests, Deck[] tasks) {
        this.requests = requests;
        this.tasks = tasks;
        this.nThreads = this.tasks.length;
    }

    public void run() {
        Request request;
        Task task;
        int threadID;
        while (this.isWorking) {
            synchronized (this.requests) {
                while (this.requests.isEmpty()) {
                    while (workStealing()) {
                    }
                    try {
                        this.requests.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                request = (Request) this.requests.pop();
                task = new Task(request.getLoad());
                threadID = RandomGenerator.getDefault().nextInt(this.nThreads);
                synchronized (this.tasks[threadID]) {
                    this.tasks[threadID].push(task);
                    this.tasks[threadID].notifyAll();
                }
            }
        }
    }
}
