package grafalgoritmer;

import java.util.PriorityQueue;

public class App {
    public static void main(String[] args) {
        EdgeListGraph<Integer> graph = new EdgeListGraph<>();

        int[] vertices = {123, 38, 6, 66, 15};
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

        GraphAlgortihms.minimumSpanningTree(graph);

        /*
        //System.out.println(GraphAlgortihms.dfs(graph, graph.getVertex(0)));
        System.out.println("Sammenhængende graf? " + GraphAlgortihms.connected(graph));
        System.out.println("Er der en vej mellem to vertices? " + GraphAlgortihms.isPath(graph, graph.getVertex(2), graph.getVertex(3)));

*/
        System.out.println("Kortest udspændte træ: ");
        System.out.println(GraphAlgortihms.mst(graph));
        for (Edge edge : GraphAlgortihms.mst(graph)) {
            System.out.println(graph.getVertex(edge.v) + " - " + graph.getVertex(edge.u) + ": " + edge.e);
        }
        System.out.println(GraphAlgortihms.bfs(graph, graph.getVertex(0)));




    }
}
