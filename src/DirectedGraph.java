import java.util.Stack;

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
            Queue nodeNeighbours = this.adjacencyList[v];
            nodeNeighbours.remove(integerW);
        }
    }

    private boolean isValidVertex(int v) {
        return v >= 0 && v < size;
    }

    private int[] getNodeNeighbours(int nodeNumber) {
        Queue nodeNeighbours = this.adjacencyList[nodeNumber];
        int[] neighbours = new int[nodeNeighbours.size];
        Node currentNode = nodeNeighbours.head;
        int i = 0;
        while (currentNode != null) {
            if (currentNode instanceof IntegerNode) {
                int neighbourVertex = ((IntegerNode) currentNode).value;
                neighbours[i] = neighbourVertex;
                i++;
            }
            currentNode = currentNode.getNext();
        }
        return neighbours;
    }

    public boolean hasCycle(int root) {
        boolean[] visited = new boolean[this.size];
        boolean[] recursionStack = new boolean[this.size];

        Stack<Integer> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            visited[currentVertex] = true;
            recursionStack[currentVertex] = true;

            Node currentNode = adjacencyList[currentVertex].head;
            int[] currentNodeNeighbours = getNodeNeighbours(currentVertex);
            for (int adjacentVertex : currentNodeNeighbours) {
                if (!visited[adjacentVertex]) {
                    stack.push(adjacentVertex);
                } else if (recursionStack[adjacentVertex]) {
                    return true;
                }
            }
            recursionStack[currentVertex] = false;
        }
        return false;
    }


    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(5);
        graph.link(0, 1);
        graph.link(1, 2);
        graph.link(2, 3);
        graph.link(3, 0);
        boolean hasCycle = graph.hasCycle(1);
        System.out.println(hasCycle);
    }
}
