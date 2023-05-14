/*
    O DeadLockPolice é também um monitor,
    portando deve ter seus métodos sincronizados;
    (Ver exemplo das filas de task)
 */
public class DeadLockPolice {
    DirectedGraph graph;

    public DeadLockPolice(int size) {
        this.graph = new DirectedGraph(size+1);
    }

    public void link(int v, int w) {
        this.graph.link(v, w);
    }

    public void unlink(int v, int w) {
        this.graph.unlink(v, w);
    }

    public boolean allow(int resource, int thread) {
        this.unlink(thread, resource);
        if (this.graph.hasCycle(thread)) {
            this.link(thread, resource);
            return false;
        }
        this.link(resource, thread);
        return true;
    }

    public static void main(String[] args) {
        DeadLockPolice police = new DeadLockPolice(3);

        // Linking resources to threads
        police.link(1, 1); // Thread 1 is linked to Resource 1
        police.link(2, 2); // Thread 2 is linked to Resource 2
        police.link(3, 3); // Thread 3 is linked to Resource 3

        // Checking resource access
        boolean allow1 = police.allow(1, 2); // Thread 2 requests access to Resource 1
        System.out.println("Allow thread 2 to access resource 1: " + allow1); // Output: true

        boolean allow2 = police.allow(2, 3); // Thread 3 requests access to Resource 2
        System.out.println("Allow thread 3 to access resource 2: " + allow2); // Output: true

        boolean allow3 = police.allow(3, 1); // Thread 1 requests access to Resource 3
        System.out.println("Allow thread 1 to access resource 3: " + allow3); // Output: false (Creates a cycle)

        // Unlinking resources from threads
        police.unlink(1, 1); // Unlink Thread 1 from Resource 1
        police.unlink(2, 2); // Unlink Thread 2 from Resource 2
        police.unlink(3, 3); // Unlink Thread 3 from Resource 3
    }
}
