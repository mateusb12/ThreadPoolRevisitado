public class Worker extends Thread{
    boolean isWorking = true;
    Deck tasks;

    public Worker(Deck tasks) {
        this.tasks = tasks;
    }

    public void run() {
        while (this.isWorking) {

        }
    }

    public static void main(String[] args) {
        Task task1 = new Task("First task");
        Task task2 = new Task("Second task");
        Task task3 = new Task("Third task");

        Deck deck = new Deck();
        deck.push(task1);
        deck.push(task2);
        deck.push(task3);

        Worker worker = new Worker(deck);
        worker.start();
    }

}
