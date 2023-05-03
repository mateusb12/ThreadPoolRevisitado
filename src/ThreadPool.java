public class ThreadPool {
    Queue requests;
    Deck[] tasks;
    Scheduler scheduler;
    Worker[] workers;
    int nThreads;

    public ThreadPool(int nThread) {
        this.nThreads = nThread;
        this.requests = new Deck();
        this.tasks = buildTasks();
        this.scheduler = buildScheduler();
        this.workers = buildWorkers();
    }

    public Deck[] buildTasks() {
        Deck[] tasks = new Deck[this.nThreads];
        for (int i = 0; i < this.nThreads; i++) {
            tasks[i] = new Deck();
        }
        return tasks;
    }

    public Scheduler buildScheduler() {
        Scheduler scheduler = new Scheduler(this.requests, this.tasks);
        scheduler.start();
        return scheduler;
    }

    public Worker[] buildWorkers() {
        Worker[] workers = new Worker[this.nThreads];
        for (int i = 0; i < this.nThreads; i++) {
            workers[i] = new Worker(this.tasks[i]);
            workers[i].start();
        }
        return workers;
    }

    public void submit(String str) {
        Request request = new Request(str);
        this.requests.push(request);
    }
}


