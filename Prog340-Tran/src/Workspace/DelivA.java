package Workspace;
import java.io.*;
import java.util.*;
import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Iterator;

public class DelivA {

	File inputFile;
    File outputFile;
    PrintWriter output;
    Graph g;

    public DelivA( File in, Graph gr ) {
        inputFile = in;
        g = gr;

        // Get output file name.
        String inputFileName = inputFile.toString();
        String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 ); // Strip off ".txt"
        String outputFileName = baseFileName.concat( "_out.txt" );
        outputFile = new File( outputFileName );
        if ( outputFile.exists() ) {    // For retests
            outputFile.delete();
        }

        try {
            output = new PrintWriter(outputFile);
        }
        catch (Exception x ) {
            System.err.format("Exception: %s%n", x);
            System.exit(0);
        }

        // Count the number of outgoing edges
        int max=0;

        // store the mode with the most outgoing edges
        Node mostEdges = null;

        // Iterate over each node to find the one with the most edges
        for(Node node : g.getNodeList()){
            // find the current number of outgoing nodes
            int sum = node.getOutgoingEdges().size();

            // if the number of outgoing edges is greater than the current maximum, update the maximum and set
            // the current node as the one with the most outgoing edges
            if(sum>max){
                max=sum;
                mostEdges = node;
            }
        }

        // keep track of the maximum distance
        int maxDist = 0;

        // keep track of the edge with the maximum distance
        Edge maxEdge = null;

        // iterate over each edge to find the one with the maximum distance
        for(Edge edge: g.getEdgeList()){

            // if the current edge has a distance greater than the maximum distance, set it as th maxEdge
            // and update the maximum distance
            if(edge.dist> maxDist ){
                maxDist=edge.dist;
                maxEdge=edge;
            }
        }


        System.out.println("There are " + g.getNodeList().size()+" nodes in the graph.\n" +
                "\n" +
                "There are "+g.getEdgeList().size()+" edges in the graph.\n" +
                "\n" +
                "Node "+mostEdges.getName()+" has the most outgoing edges ("+mostEdges.getOutgoingEdges().size()+").\n" +
                "\n" +
                "The longest edges are edges "+ maxEdge.head.getName()+" and "+maxEdge.tail.getName()+" ("+maxEdge.dist+").\n");

        output.println("There are " + g.getNodeList().size()+" nodes in the graph.\n" +
                "\n" +
                "There are "+g.getEdgeList().size()+" edges in the graph.\n" +
                "\n" +
                "Node "+mostEdges.getName()+" has the most outgoing edges ("+mostEdges.getOutgoingEdges().size()+").\n" +
                "\n" +
                "The longest edges are edges "+ maxEdge.head.getName()+" and "+maxEdge.tail.getName()+" ("+maxEdge.dist+").\n");

        output.flush();
    }
}

