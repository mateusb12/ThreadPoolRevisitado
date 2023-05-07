
public class DummyDeadlockSolver implements DeadlockSolver {
    @Override
    public boolean solve(Object lock1, Object lock2) {
        return false;
    }
}
