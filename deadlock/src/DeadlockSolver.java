public interface DeadlockSolver {
    boolean solve(Object lock1, Object lock2);
}
