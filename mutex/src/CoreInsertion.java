import java.util.ArrayList;
import java.util.Random;

public class CoreInsertion {
    public void run(SharedListManager processor) throws InterruptedException {
        int threadAmount = 2;
        ArrayList<Thread> threadPot = new ArrayList<Thread>();
        for (int i = 0; i < threadAmount; i++) {
            Thread t = new Thread(() -> {
                randomInsertion(processor);
            });
            threadPot.add(t);
        }
        for (Thread t : threadPot) {
            t.start();
        }
        for (Thread t : threadPot) {
            t.join();
        }
        System.out.println("Shared list size: " + processor.getSharedList().size());
    }

    private static void randomInsertion(SharedListManager processor) {
        for (int i = 0; i < 5000; i++) {
            int num = new Random().nextInt(100);
            processor.addToSharedList(num);
        }
    }
}
