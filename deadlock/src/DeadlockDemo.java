public class DeadlockDemo {
    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public Thread threadA;
    public Thread threadB;

    public void runAllThreads(){
        threadA.start();
        threadB.start();
    }

    public void createThreadA(){
        threadA = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread A: Holding lock 1...");
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException ignored) {}
                System.out.println("Thread A: Waiting for lock 2...");
                synchronized (lock2) {
                    System.out.println("Task finished. Lock 1 and lock 2 are acquired.");
                }
            }
        });
    }

    public void createThreadB(){
        threadB = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread B: Holding lock 2...");
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException ignored) {}
                System.out.println("Thread B: Waiting for lock 1...");
                synchronized (lock1) {
                    System.out.println("Task finished. Lock 1 and lock 2 are acquired.");
                }
            }
        });
    }

    public static void main(String[] args) {
        DeadlockDemo dm = new DeadlockDemo();
        dm.createThreadA();
        dm.createThreadB();
        dm.runAllThreads();
    }
}
