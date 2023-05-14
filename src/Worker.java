import java.util.UUID;

public class Worker extends Thread{
    private final String id;
    boolean isWorking = true;
    Deck tasks;

    public Worker(Deck tasks) {
        this.tasks = tasks;
        this.id = generateUniqueID();
    }

    public String getID() {
        return this.id;
    }

    private String generateUniqueID() {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().replace("-", ""); // Remove hyphens
        return uuidString.substring(0, 4); // Take the first 4 characters
    }

    public void run() {
        while (this.isWorking) {
            if (!tasks.isEmpty()) {
                Task task = (Task) tasks.popFront();
                System.out.println("[Worker " + this.id + "] is processing task: " + task.getTaskDescription());
            } else {
                System.out.println("[Worker " + this.id + "] is waiting for tasks.");
                isWorking = false;
            }
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
