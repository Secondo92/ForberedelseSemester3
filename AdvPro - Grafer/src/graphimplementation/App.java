package graphimplementation;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        //EdgeListGraph<Integer> graph = new EdgeListGraph<>();
        //AdjacencyEdgeListGraph<Integer> graph = new AdjacencyEdgeListGraph<>();
        AdjacencyMatrixGraph<Integer> graph = new AdjacencyMatrixGraph<>();

        int[] vertices = {123, 38, 6, 66, 15};
        graph.printMatrix();
        for(int vertex : vertices){
            graph.addVertex(vertex);
        }

        int[][] edges = {
                {0, 1, 55},
                {0, 2, 7},
                {0, 3, 76},
                {1, 4, 15},
                {1, 3, 2},
                {4, 3, 90},
                {4, 2, 23},
                {2, 3, 8},
        };
        for(int i = 0; i < edges.length; i++){
            graph.addEdge(edges[i][0], edges[i][1], edges[i][2]);
            }

        graph.printMatrix();
        System.out.println(graph.areAdjacent(graph.getVertex(4), graph.getVertex(3)));
        //System.out.println("Biggest value: " + biggestVal(graph));

        System.out.println("Before: ");
        System.out.println(graph.getVertices());
        System.out.println(graph.getEdges());

        //System.out.println(graph.getEdgeList());
        /*
        graph.remove(123);
        System.out.println("After: ");
        System.out.println("Biggest value: " + biggestVal(graph));
        System.out.println(graph.getVertices());
        System.out.println(graph.getEdges());

         */

    }
    private static int biggestVal(EdgeListGraph<Integer> graph){
        List<Integer> vertices = graph.getVertices();
        int biggestVal = vertices.get(0);
        for(Integer vertex : vertices){
            if(vertex > biggestVal){
                biggestVal = vertex;
            }
        }
        return biggestVal;
    }
}
