import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MutexSolution {
    private static final List<Integer> sharedList = new ArrayList<Integer>();
    private static final ReentrantLock lock = new ReentrantLock();

    private void addToSharedList(int num) {
        lock.lock();
        try{
            sharedList.add(num);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Shared list size: " + sharedList.size());
    }
}
