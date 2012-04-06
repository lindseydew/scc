import java.io.IOException;
public class scc {

    public static void main (String[] args) {


        try {
            int nLines = GraphBuilderFromFile.count("/Users/lindseydew/Documents/workspace/scc/src/test.txt");
            Graph graph = GraphBuilderFromFile.fileRead("/Users/lindseydew/Documents/workspace/scc/src/test.txt", nLines);

            System.out.println(graph.toString());
            System.out.println(graph.toEdgeListString());
            System.out.println(graph.toAdjacencyListString());
            graph.depthFirstSearch(graph, 0);

        }
        catch (IOException e) {
            e.printStackTrace();
        }




    }
}
