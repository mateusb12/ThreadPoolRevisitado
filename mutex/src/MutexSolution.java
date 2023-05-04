import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MutexSolution implements SharedListManager{
    private static final List<Integer> sharedList = new ArrayList<Integer>();
    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public List<Integer> getSharedList() {
        return sharedList;
    }

    public void addToSharedList(int num) {
        lock.lock();
        try{
            sharedList.add(num);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CoreInsertion insertionInstance = new CoreInsertion();
        MutexSolution mutex = new MutexSolution();
        insertionInstance.run(mutex);
    }
}
