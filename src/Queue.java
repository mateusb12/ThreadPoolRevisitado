public class Queue {
    Node head = null;
    Node tail = null;
    int size = 0;

    public void push(Node newNode) {
        if (isEmpty()) {
            this.head = newNode;
        }
        else {
            this.tail.setNext(newNode);
            newNode.setPrevious(this.tail);
        }
        this.tail = newNode;
        this.size++;
    }

    public Node pop() {
        Node node = this.head;
        if (node != null) {
            this.head = this.head.getNext();
            if (this.size == 1) this.tail = null;
            else {
                this.head.setPrevious(null);
            }
            this.size--;
        }
        return node;
    }

    public boolean isEmpty() {
        return this.head==null;
    }

    public static void main(String[] args) {
        Queue q1 = new Queue();
        Request r1 = new Request("r1");
        Request r2 = new Request("r2");
        Request r3 = new Request("r3");
        q1.push(r1);
        q1.push(r2);
        q1.push(r3);
        System.out.println(q1.isEmpty());
    }
}
