public class Request implements Node{
    Request previous = null;
    Request next = null;
    String load;

    public Request(String str) {
        this.load = str;
    }

    @Override
    public Node getPrevious() {
        return this.previous;
    }

    @Override
    public Node getNext() {
        return this.next;
    }

    @Override
    public void setPrevious(Node node) {
        this.previous = (Request) node;
    }

    @Override
    public void setNext(Node node) {
        this.next = (Request) node;
    }

    public void setLoad(String str) {
        this.load = str;
    }

    public String getLoad() {
        return this.load;
    }

    public static void main(String[] args) {
        Request requestA = new Request("First Load");
        Request requestB = new Request("Second Load");
        Request requestC = new Request("Third Load");

        // Test setting and getting of load
        System.out.println("Request A load: " + requestA.getLoad());
        requestA.setLoad("Special First Load");
        System.out.println("Request A new load: " + requestA.getLoad());

        requestB.setPrevious(requestA);
        requestB.setNext(requestC);
        Request requestBPrevious = (Request) requestB.getPrevious();
        Request requestBNext = (Request) requestB.getNext();
        System.out.println("Request B previous load: " + requestBPrevious.getLoad());
        System.out.println("Request B next load: " + requestBNext.getLoad());
    }
}
