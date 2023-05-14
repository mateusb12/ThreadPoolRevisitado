import java.util.random.RandomGenerator;

public class Scheduler extends Thread {
    Queue requests;
    Deck[] tasks;
    int nThreads;
    boolean isWorking = true;

    public int searchEmpty() {
        /*
        • This method is used to find an index of an idle thread (worker) with no tasks assigned.
        • It randomly selects a thread ID and checks if its corresponding tasks deck has a size less than 1 (empty).
        • If an empty thread is found, it returns the thread ID. Otherwise, it returns -1.
        */
        int threadID = getRandomThread();
        for (int i = 0; i < this.nThreads; i++) {
            if (this.tasks[threadID].size < 1) {
                return threadID;
            }
            threadID = (threadID + 1) % this.nThreads;
        }
        return -1;
    }

    public int searchFull() {
        /*
        • This method is used to find an index of a thread (worker) with more than one task assigned.
        • It randomly selects a thread ID and checks if its corresponding tasks deck has a size greater than 1.
        • If a thread with more than one task is found, it returns the thread ID. Otherwise, it returns -1.
        */
        int threadID = getRandomThread();
        for (int i = 0; i < this.nThreads; i++) {
            if (this.tasks[threadID].size > 1) {
                return threadID;
            }
            threadID = (threadID + 1) % this.nThreads;
        }
        return -1;
    }


    synchronized public boolean workStealing() {
        /*
        • This method implements a work-stealing algorithm, where a task is stolen from a thread with more tasks
         and given to a thread with fewer tasks.
        • It calls searchFull() to find a thread with more than one task (threadID_full).
            • If such a thread is found, it calls searchEmpty() to find an idle thread (threadID_empty).
        • If both an idle thread and a thread with more tasks are found
            • It moves the last task from the full thread's tasks deck to the empty thread's tasks deck using push()
             and popBack() methods.
        • Returns true if a task was successfully stolen and moved, false otherwise.
        */
        int threadID_full = searchFull();
        if (threadID_full >= 0) {
            int threadID_empty = searchEmpty();
            if (threadID_empty >= 0) {
                Task stolenTask = (Task) this.tasks[threadID_full].popBack();
                this.tasks[threadID_empty].push(stolenTask);
                System.out.println("[Scheduler]: task stolen from thread " + threadID_full + " and given to thread " +
                        threadID_empty);
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

    public Request getLastRequest(){
        return (Request) this.requests.pop();
    }

    public void run() {
        System.out.println("[Scheduler] Starting scheduler.");
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
                request = getLastRequest();
                processIncomingRequest(request);
            }
        }
    }

    private void processIncomingRequest(Request request) {
        /* This method is responsible for handling incoming requests.
        It converts the request into a task and assigns it to a random thread (worker) using push() method.
         */
        int threadID;
        Task task;
        System.out.println("[Scheduler] new request received - (" + request.load + ")");
        task = new Task(request.getLoad());
        threadID = getRandomThread();
        synchronized (this.tasks[threadID]) {
            this.tasks[threadID].push(task);
            this.tasks[threadID].notifyAll();
        }
    }

    private int getRandomThread() {
        return RandomGenerator.getDefault().nextInt(this.nThreads);
    }

    private void sleep(int seconds){
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Scheduler createRequests(int requestAmount, int deckAmount){
        Deck[] decks = new Deck[deckAmount];
        for (int i = 0; i < decks.length; i++) {
            decks[i] = new Deck();
        }

        Request[] requests = new Request[requestAmount];
        for (int i = 0; i < requests.length; i++) {
            requests[i] = new Request("Request " + (char) ('A' + i));
        }

        Queue requestsQueue = new Queue();
        for (Request request : requests) {
            requestsQueue.push(request);
        }
        return new Scheduler(requestsQueue, decks);
    }

    public static void main(String[] args) {
        Scheduler scheduler = createRequests(5, 5);
        scheduler.start();
    }


}


