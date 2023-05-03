public class Task implements Node, Runnable{
    Task previous = null;
    Task next = null;

    String load;

    public Task(String load) {
        this.load = load;
    }

    @Override
    public Node getPrevious() {
        return this.previous;
    }

    @Override
    public Node getNext() {
        return this.next;
    }

    @Override
    public void setPrevious(Node node) {
        this.previous = (Task) node;
    }

    @Override
    public void setNext(Node node) {
        this.next = (Task) node;
    }

    @Override
    public void run() {
        try {
            System.out.println("Running task: " + this.load);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Task task1 = new Task("First Load");
        Task task2 = new Task("Second Load");
        Task task3 = new Task("Third Load");

        task1.setNext(task2);
        task2.setPrevious(task1);
        task2.setNext(task3);
        task3.setPrevious(task2);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
