import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;

public class scc {

    public static void main (String[] args) {


        try {
            Graph graph = GraphBuilderFromFile.fileRead("/Users/lindseydew/Documents/workspace/scc/src/test1.txt", 6);

            System.out.println("original graph");
            System.out.println(graph.toString());

            Graph reversedGraph = graph.gReverse();
            System.out.println("graph reversed");
            System.out.println(reversedGraph.toString());

            Map<Integer, Integer> finishingTime = reversedGraph.DFS();

            System.out.println("graph with magical ordering");
            Graph orderedGraph = graph.orderedGraph(graph, finishingTime);
            System.out.println(orderedGraph.toString());

            LinkedList<Integer> leaders = orderedGraph.DFSSecond();
            System.out.println("Connected components are");
            System.out.println(linkedListToString(leaders));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String arrayToString(int[] leader) {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        for(int i = 0; i < leader.length; i++) {
            int v = leader[i];
            s.append(v + NEWLINE);
        }
        return s.toString();
    }

    private static String linkedListToString(LinkedList<Integer> leader) {
        StringBuilder s = new StringBuilder();
//        s.append("{ ");
//        for(int i = 0; i < leader.size(); i++) {
            s.append("{ ");
            for(int w : leader) {
            s.append(w + " ");
            }
            s.append("}");
//        }
//        s.append("}");
        return s.toString();
    }
}
