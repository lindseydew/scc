import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
    
    enum VertexState {
        White, Gray, Black
    }


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
        s.append("[ ");
        for(int v = 0; v < V; v++) {
            s.append("{ ");
            for (int w : adj[v]) {
                s.append(w + " ");

            }
            s.append("}");
        }
        s.append(" ]");
        return s.toString();
    }

    
    public Graph gReverse() {
        int nVertices = V;
        Graph reversedGraph = new Graph(nVertices);
        for(int v = 0; v < V; v++)
           for(int w: adj[v]) {
               reversedGraph.addEdge(w, v);
           }
        return reversedGraph;
    }
    
    public Graph orderedGraph(Graph graph, Map<Integer, Integer> finishingTime) {
        Graph revOrderedGraph = new Graph(V);
        Graph reverse = graph.gReverse();
        for(int v = 0; v < V; v++) {
            LinkedList<Integer>[] revAdj = reverse.getAdj();
            for(int w : revAdj[v]) {
                int v1 = finishingTime.get(v);
                int w1 = finishingTime.get(w);
                revOrderedGraph.addEdge(v1, w1);
            }
        }
        Graph orderedGraph = revOrderedGraph.gReverse();
        return orderedGraph;
    }

    public Map<Integer, Integer> DFS()  {
        Map<Integer, Integer> finishingTime = new HashMap<Integer, Integer>();
        VertexState state[] = new VertexState[V];
        for(int v = 0; v < V; v++) {
           state[v] = VertexState.White;
        }
        for(int i = V - 1; i >= 0; i--) {
            if(state[i] == VertexState.White) {
            runDFSFirstPass(i, state, finishingTime);
            }
        }

        return finishingTime;
    }
    public void runDFSFirstPass(int u, VertexState[] state, Map<Integer, Integer> finishingTime) {

        state[u] = VertexState.Gray;
        //get neighbours of V
        for(int w : adj[u]) {
            if(state[w] == VertexState.White) {
                runDFSFirstPass(w, state, finishingTime);
            }
        }

        state[u] = VertexState.Black;
        finishingTime.put(u, time);
        time = time + 1;
    }
    int s;

    public LinkedList<Integer> DFSSecond() {
        LinkedList<LinkedList<Integer>> leaders = new LinkedList<LinkedList<Integer>>();
        VertexState state[] = new VertexState[V];
        for(int v = 0; v < V; v++) {
            state[v] = VertexState.White;
        }
        for(int i = V - 1; i > 0; i--) {
            if(state[i] == VertexState.White){
                s = i;
                runDFSStackSecondPass(s, i, state);
            }
        }
        return numberOfS;
    }
    int time = 0;

    LinkedList<Integer> numberOfS = new LinkedList<Integer>();


    public void runDFSSecondPass(int s, int u, VertexState[] state) {

//        LinkedList<Integer> ithConnectedComponent = new LinkedList<Integer>();
        numberOfS.add(s);
        state[u] = VertexState.Gray;
        for(int w : adj[u]) {
            if(state[w] == VertexState.White) {
                runDFSSecondPass(s, w, state);
            }
        }
        state[u] = VertexState.Black;

//        ithConnectedComponent.add(s);
//        leaders.add(ithConnectedComponent);
    }
    
    
    public LinkedList<LinkedList<Integer>> instantiateLinkedList() {
        LinkedList<LinkedList<Integer>> leaders = new LinkedList<LinkedList<Integer>>();
        LinkedList<Integer> connectedComponent1 = new LinkedList<Integer>();
        LinkedList<Integer> connectedComponent2 = new LinkedList<Integer>();
        LinkedList<Integer> connectedComponent3 = new LinkedList<Integer>();
        LinkedList<Integer> connectedComponent4 = new LinkedList<Integer>();
        
        connectedComponent1.add(4);
        connectedComponent1.add(5);
        connectedComponent2.add(3);
        connectedComponent3.add(2);
        connectedComponent4.add(0);
        connectedComponent4.add(1);
        connectedComponent4.add(2);
        
        leaders.add(connectedComponent1);
        leaders.add(connectedComponent2);
        leaders.add(connectedComponent3);
        leaders.add(connectedComponent4);

        return leaders;
    }
    int[] predecessors = new int[1000];
    public void runDFSStackSecondPass(int s, int u, VertexState[] state) {
          Stack<Integer> stack = new Stack<Integer>();
          stack.push(u);
          while(!stack.isEmpty()) {
             int x = stack.pop();
             if(state[x] != VertexState.Black) {
                 state[x] = VertexState.Gray;
                 for(int w : adj[x]) {
                     predecessors[w] = x;
                     if(state[w] == VertexState.White) {
                         state[w] = VertexState.Gray;
                         stack.push(w);
                     }
                 }
             }
             state[x] = VertexState.Black;
          }
    }

    public String finishingTimeToString(Map<Integer, Integer> finishingTime) {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        for(Integer i = 0; i < V; i++) {
            Integer time = finishingTime.get(i);
            s.append("f(" + time + ") = " + i + NEWLINE );
        }

        return s.toString();
    }

}
