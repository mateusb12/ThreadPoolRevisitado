public class ThreadPool {
    Queue requests;
    Deck[] tasks;
    Scheduler scheduler;
    Worker[] workers;
    int nThreads;

    public ThreadPool(int nThread) {
        System.out.println("[Main] Instantiating thread pool.");
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

    public void submitNewRequest(String str) {
        Request request = new Request(str);
        this.requests.push(request);
    }

    public static void pushNewRequests(ThreadPool threadPoolInstance, int requestAmount){
        for (int i = 0; i < requestAmount; i++) {
            String requestName = "Request " + (char) ('A' + i);
            threadPoolInstance.submitNewRequest(requestName);
            System.out.println("[Main] new request submitted - (" + requestName + ")");
        }
    }

    public static void main(String[] args) {
        ThreadPool tp = new ThreadPool(4);
        pushNewRequests(tp, 7);
        System.out.println("[Main] Finished instantiating Thread pool.");
    }
}


