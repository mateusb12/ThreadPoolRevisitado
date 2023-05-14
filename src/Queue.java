public class Queue {
    Node head = null;
    Node tail = null;
    int size = 0;

    public void push(Node node) {
        synchronized (this) {
            if (isEmpty()) {
                this.head = node;
            } else {
                this.tail.setNext(node);
                node.setPrevious(this.tail);
            }
            this.tail = node;
            this.size++;
        }
    }

    public Node pop() {
        synchronized (this) {
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
    }

    public void remove(IntegerNode inputNode) {
        int inputNodeValue = inputNode.value;
        int currentNodeValue;
        synchronized (this) {
            IntegerNode currentNode = (IntegerNode) this.head;
            currentNodeValue = currentNode.value;

            while (currentNode != null && currentNodeValue != inputNodeValue) {
                currentNode = (IntegerNode) currentNode.getNext();
                if (currentNode != null) {
                    currentNodeValue = currentNode.value;
                }
            }

            if (currentNode != null) {
                Node previousNode = currentNode.getPrevious();
                Node nextNode = currentNode.getNext();
                if (previousNode != null) {
                    previousNode.setNext(nextNode);
                } else {
                    // The node to be removed is the head node
                    this.head = nextNode;
                }
                if (nextNode != null) {
                    nextNode.setPrevious(previousNode);
                } else {
                    // The node to be removed is the tail node
                    this.tail = previousNode;
                }
                this.size--;
            }
        }
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
        q1.pop();
        q1.pop();
        q1.pop();
        System.out.println(q1.isEmpty());
    }
}
