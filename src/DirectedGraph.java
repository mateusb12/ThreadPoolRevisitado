public class DirectedGraph {
    Queue[] adjacencyList;
    int size;

    public DirectedGraph(int size) {
        this.size = size;
        this.adjacencyList = buildQueue();
    }

    private Queue[] buildQueue() {
        Queue[] adjacencyList = new Queue[this.size];
        for (int i = 0; i < this.size; i++) {
            adjacencyList[i] = new Queue();
        }
        return adjacencyList;
    }

    public void link(int v, int w) {
        boolean bothValidVertexes = isValidVertex(v) && isValidVertex(w);
        if (bothValidVertexes) {
            IntegerNode integerW = new IntegerNode(w);
            this.adjacencyList[v].push(integerW);
        }
    }

    public void unlink(int v, int w) {
        boolean bothValidVertexes = isValidVertex(v) && isValidVertex(w);
        if (bothValidVertexes) {
            IntegerNode integerW = new IntegerNode(w);
            this.adjacencyList[v].remove(integerW);
        }
    }

    private boolean isValidVertex(int v) {
        return v >= 0 && v < size;
    }

    public boolean hasCycle(int root) {
        boolean[] visited = new boolean[size];
        boolean[] recursionStack = new boolean[size];

        return hasCycleDFS(root, visited, recursionStack);
    }

    private boolean hasCycleDFS(int vertex, boolean[] visited, boolean[] recursionStack) {
        visited[vertex] = true;
        recursionStack[vertex] = true;

        Node currentNode = adjacencyList[vertex].head;
        while (currentNode != null) {
            if (currentNode instanceof IntegerNode) {
                int adjacentVertex = ((IntegerNode) currentNode).value;
                if (!visited[adjacentVertex]) {
                    if (hasCycleDFS(adjacentVertex, visited, recursionStack)) {
                        return true;
                    }
                } else if (recursionStack[adjacentVertex]) {
                    return true;
                }
            }
            currentNode = currentNode.getNext();
        }
        recursionStack[vertex] = false;
        return false;
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(5);
        graph.link(0, 1);
        graph.link(0, 2);
        graph.link(0, 3);
        graph.link(1, 3);
        graph.link(2, 3);
        graph.link(3, 4);
        graph.link(4, 0);
        graph.unlink(0, 2);
        graph.unlink(3, 4);
        boolean hasCycle = graph.hasCycle(0);
        System.out.println(hasCycle);
    }
}
