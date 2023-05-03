public class Scheduler extends Thread {
    Queue requests;
    Deck[] tasks;
    int nThreads;
    boolean isWorking = true;


    public Scheduler(Queue requests, Deck[] tasks) {
        this.requests = requests;
        this.tasks = tasks;
        this.nThreads = this.tasks.length;
    }

    public void run() {
        while (this.isWorking) {
            while (this.requests.isEmpty()) {
                System.out.println("Scheduler is waiting for requests");
            }
        /*
            Do something
         */
        }
    }
}
