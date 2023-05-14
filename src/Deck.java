public class Deck extends Queue {
    public Node popFront() {
        synchronized (this) {
            return this.pop();
        }
    }

    public Node popBack() {
        synchronized (this) {
            Node node = this.tail;
            if (node != null) {
                this.tail = this.tail.getPrevious();
                if (this.size == 1) this.head = null;
                else {
                    this.tail.setNext(null);
                }
                this.size--;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        Request r1 = new Request("r1");
        Request r2 = new Request("r2");
        Request r3 = new Request("r3");
        Deck d1 = new Deck();
        d1.push(r1);
        d1.push(r2);
        d1.push(r3);
        Request frontNode = (Request) d1.popFront();
        Request backNode = (Request) d1.popBack();
        System.out.println("Front node value: " + frontNode.getLoad());
        System.out.println("Back node value: " + backNode.getLoad());
    }
}
