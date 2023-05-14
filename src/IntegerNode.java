public class IntegerNode implements Node {
    int value;
    Node previous;
    Node next;

    public IntegerNode(int value) {
        this.value = value;
    }

    @Override
    public Node getPrevious() {
        return previous;
    }

    @Override
    public Node getNext() {
        return next;
    }

    @Override
    public void setPrevious(Node node) {
        previous = node;
    }

    @Override
    public void setNext(Node node) {
        next = node;
    }

    public void linkWithNode(Node newnode){
        this.setNext(newnode);
        newnode.setPrevious(this);
    }

    public static void main(String[] args) {
        IntegerNode n1 = new IntegerNode(1);
        IntegerNode n2 = new IntegerNode(2);
        IntegerNode n3 = new IntegerNode(3);
        n1.linkWithNode(n2);
        n2.linkWithNode(n3);
    }
}
