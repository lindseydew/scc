import junit.framework.TestCase;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class GraphTest {

    Graph graph = new Graph(5);
  
    @Before
    public void setup() {
        
    }
    
    
    @Test
    public void reverseGraphShouldReverseAGraph(Graph graph) {
        
        graph = this.graph;

        graph.addEdge(0,0);
        graph.addEdge(1,2);
        graph.addEdge(2,1);
        graph.addEdge(3,2);
        graph.addEdge(1,4);
        
        Graph reversedGraph1 = new Graph(5);
        reversedGraph1.addEdge(0,0);
        reversedGraph1.addEdge(2,1);
        reversedGraph1.addEdge(1,2);
        reversedGraph1.addEdge(2,3);
        reversedGraph1.addEdge(4,1);


        Graph reversedGraph = graph.gReverse();
        
        assertEquals(reversedGraph, reversedGraph1);
        
        
    }
    
                     
}
