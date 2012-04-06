import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: lindseydew
 * Date: 06/04/2012
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class Graph {

    private int V;
    private int countE;
    private LinkedList<Integer>[] adj;


    /**
     * Create an empty graph with V vertices.
     */
    public Graph(int V) {
        if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
        this.V = V;
        this.countE = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<Integer>();
        }
    }

    /**
     * Create a random graph with V vertices and countE edges.
     * Expected running time is proportional to V + countE.
     */
    public Graph(int V, int E) {
        this(V);
        if (E < 0) throw new RuntimeException("Number of edges must be nonnegative");
            for (int i = 0; i < countE; i++) {
                int v = (int) (Math.random() * V);
                int w = (int) (Math.random() * V);
                addEdge(v, w);
            }
    }

         /**
         * Create a digraph from input stream.
         */

//        public Graph(In in) {
//            this(in.readInt());
//            int countE = in.readInt();
//            for (int i = 0; i < countE; i++) {
//                int v = in.readInt();
//                int w = in.readInt();
//                addEdge(v, w);
//            }
//        }
        /**
         * Copy constructor.
         */
//        public Graph(Graph G) {
//            this(G.V());
//            this.countE = G.countE();
//            for (int v = 0; v < G.V(); v++) {
//                // reverse so that adjacency list is in same order as original
//                Stack<Integer> reverse = new Stack<Integer>();
//                for (int w : G.adj[v]) {
//                    reverse.push(w);
//                }
//                for (int w : reverse) {
//                    adj[v].add(w);
//                }
//            }
//        }

    /**
     * Return the number of vertices in the graph.
     */
    public int getV() { return V; }

    /**
     * Return the number of edges in the graph.
     */
    public int getCountE() { return countE; }

    public LinkedList<Integer>[] getAdj() { return adj;}


    /**
     * Add the edge v-w to graph.
     */
    public void addEdge(int u, int v) {
        countE++;
        adj[u].add(v);
    }
    /**
     * Return the list of neighbors of vertex v as in Iterable.
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    /**
     * Return a string representation of the graph.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + countE + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }


    public String toEdgeListString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + countE + " edges" + NEWLINE);
        s.append("[ ");
        for(int v = 0; v < V; v++) {
            for (int w : adj[v]) {
                if(v > w) {
                    s.append("");
                }
                else {
                    s.append("{");
                    s.append(v + ", ");
                    s.append(w);
                    s.append("} ");
                }
            }
        }
        s.append("]");
        return s.toString();
    }

    public String toAdjacencyListString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + countE + " edges" + NEWLINE);
        s.append("{ ");
        for(int v = 0; v < V; v++) {
            s.append("[ ");
            for (int w : adj[v]) {
                s.append(w + " ");

            }
            s.append("]");
        }
        s.append(" }");
        return s.toString();
    }
    
    
    public void depthFirstSearch(Graph graph, int startV) {
        boolean[] visited = new boolean[graph.getV()];
        int t = 0;
        String[] finishingTime = new String[graph.getV()];
        visited[startV] = true;
       //get neighbours of V
        for(int w : adj[startV]) {
            if(visited[w] == false) {
                visited[w] = true;
                depthFirstSearch(graph, w);
            }
        }
        t++;
        finishingTime[startV] = "f(" + t + ")";
        printResult(finishingTime);
    }




    public void printResult(String[] array) {
        for(String m : array) {
            System.out.print(m);
            System.out.println("");
        }
    }



}
