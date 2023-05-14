public interface DeadLockInterface {
     Object lock1 = new Object();
     Object lock2 = new Object();

     void createThreadA();
     void createThreadB();
}
