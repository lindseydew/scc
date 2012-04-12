
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GraphBuilderFromFile {


    public static int count(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 1;
            int readChars = 0;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
            }
            return count;
        } finally {
            is.close();
        }
    }


    public static Graph fileRead(String filename, int nLines) throws FileNotFoundException {
        Graph graph = new Graph(nLines);
        Graph graphRev = new Graph(nLines);
        try {
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;

            //vertices
            int i = 0;
            //add condition not to be empty string
            while ((line = br.readLine()) != null ){

                String newline = line.replaceAll("\\s+", " ");
                String[] newline1 = newline.split("\\s");

                int[] numbersInLine = new int[newline1.length];
                int j = 0;
                //populate the integer array
                for (String s : newline1) {
                    if(!s.equals("")) {
                        int num = Integer.parseInt(s);
                        numbersInLine[j] = num;
                        j++;
                    }
                }
                //vertices
                int vertex1 = numbersInLine[0];
                int vertex2 = numbersInLine[1];
                graph.addEdge(vertex1 - 1, vertex2 - 1);
                graphRev.addEdge(vertex2 - 1, vertex1 - 1);

            }

            in.close();


        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }


        return graph;
    }

}