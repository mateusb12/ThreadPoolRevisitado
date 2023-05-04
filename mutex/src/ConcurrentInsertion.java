import java.util.Random;

public class ConcurrentInsertion {
    public void run(SharedListManager processor) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            randomInsertion(processor);
        });

        Thread t2 = new Thread(() -> {
            randomInsertion(processor);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Shared list size: " + processor.getSharedList().size());
    }

    private static void randomInsertion(SharedListManager processor) {
        for (int i = 0; i < 2500; i++) {
            int num = new Random().nextInt(100);
            processor.addToSharedList(num);
        }
    }
}
