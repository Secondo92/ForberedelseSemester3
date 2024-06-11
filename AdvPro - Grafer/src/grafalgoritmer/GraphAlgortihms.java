package grafalgoritmer;

import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphAlgortihms {
    /**
     * Returnerer en liste af grafens knuder fundet ved et dybddeførst gennemløb af
     * grafen med startknude v.
     */
    public static <V> List<V> dfs(Graph<V> graph, V v) {
        List<V> visited = new ArrayList<>();
        dfsHelper(graph, v, visited);
        return visited;
    }

    private static <V> void dfsHelper(Graph<V> graph, V v, List<V> visited) {
        System.out.println("Visiting " + v);
        visited.add(v);
        List<V> neighbors = graph.getNeighbors(v);
        for(V vertex : neighbors){
            if(!visited.contains(vertex)){
                System.out.println(vertex + " is not visited...");
                dfsHelper(graph, vertex, visited);
            }
        }
    }

    /**
     * Returnerer en liste af grafens knuder fundet ved et breddeførst gennemløb af
     * grafen med startknude v.
     */
    public static <V> List<V> bfs(Graph<V> graph, V v) {
        Queue<V> queue = new LinkedList<>();
        List<V> visited = new ArrayList<>();
        queue.add(v);
        // Pusher 123 på stakken.
        // Så længe stakken ikke er tom så:
        // Pop 123
        // Finder 123's naboer: 38, 6, 66
        // Smider 123 i listen af besøgte knuder
        // For hver af 123's naboer: 38, 6, 66:
        // Hvis listen af besøgte knuder ikke har naboerne(38, 6, 66) og stakken ikke har naboerne(38, 6, 66)
        // Så put naboerne(38, 6, 66) på stakken.
        // Tilbage til indre while-loop:
        // Stakken er ikke tom, da 123's naboer(38, 6, 66) er på stakken.
        // Pop 38
        // Smider 38 i listen af besøgte knuder
        // For hver af 38's naboer: 123, 66, 15:
        // Hvis listen af besøgte knuder ikke har naboerne(123, 66, 15) og stakken ikke har naboerne(123, 66, 15)
        // Så put naboerne (123, 66, 15) på stakken. 123 er besøgt, 66 er på stakken, så kun 15 kommer på stakken.
        // Tilbage til indre while-loop:
        // Stakken er ikke tom, da 123 og 38's naboer(6, 66, 15) er på stakken.
        // Og så videre...

        while(!queue.isEmpty()){
            V current = queue.poll();
            List<V> neighbors = graph.getNeighbors(current);
            visited.add(current);
            for(V neighbor : neighbors){
                if(!visited.contains(neighbor) && !queue.contains(neighbor)){
                    queue.add(neighbor);
                }
            }
        }
        return visited;
    }

    /**
     * Returnerer om grafen er sammenhængende
     * Pre: grafen er ikke tom
     */
    public static <V> boolean connected(Graph<V> graph) {
        if(graph.getVertices().isEmpty()){
            return false;
        }
        List<V> visited = bfs(graph, graph.getVertex(0));
        return visited.size() == graph.getVertices().size();
    }

    /**
     * Returnerer om der er en vej fra v1 til v2 i graph
     */
    public static <V> boolean isPath(Graph<V> graph, V v1, V v2) {
        List<V> visited = bfs(graph, v1);
        return visited.contains(v2);
    }

    public static <V> void minimumSpanningTree(Graph<V> graph){
        // IKKE FÆRDIG OVERHOVEDET. SORTERER KUN EFTER STØRRELSE OG FJERNER MIRROR EDGES
        ArrayList<HashSet<V>> grupper = new ArrayList<HashSet<V>>();
        Set<String> seenEdges = new HashSet<>();

        List<Edge> uniqueEdges = graph.getEdges().stream()
                .filter(edge -> {

                    String forward = edge.u + "," + edge.v;
                    String backward = edge.v + "," + edge.u;


                    if (seenEdges.contains(forward) || seenEdges.contains(backward)) {
                        return false;
                    }

                    seenEdges.add(forward);
                    return true;
                })
                .sorted(Comparator.comparingInt(e -> e.e))
                .collect(Collectors.toList());

        Edge[] edgeArray = uniqueEdges.toArray(new Edge[0]);


        System.out.println(Arrays.toString(edgeArray));
    }

    /**
     * Returnerer en mængde af grafens kanter der udgør det letteste udspændende træ for grafen.
     * Grafen er en simpel vægtet graf
     */
    public static <V> Set<Edge> mst(Graph<V> graph) {
        ArrayList<HashSet<V>> c = new ArrayList<HashSet<V>>();
        Set<Edge> tree = new HashSet<>();
        for (V v : graph.getVertices()) {
            HashSet<V> s = new HashSet<V>();
            s.add(v);
            c.add(s);
        }
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        for (Edge edge : graph.getEdges()) {
            priorityQueue.add(edge);
        }
        while (tree.size() < graph.getSize() - 1) {
            Edge e = priorityQueue.remove();
            V vertex1 = graph.getVertex(e.v);
            V vertex2 = graph.getVertex(e.u);
            int index1 = findIndex(vertex1, c);
            int index2 = findIndex(vertex2, c);
            if (index1 != index2) {
                tree.add(e);
                Set<V> l1 = c.get(index1);
                c.get(index2).addAll(l1);
                c.remove(index1);
            }
        }
        return tree;
    }
    public static <V> int findIndex(V v, ArrayList<HashSet<V>> c) {
        boolean found = false;
        int i = 0;
        while (!found && i < c.size()) {
            if (c.get(i).contains(v))
                found = true;
            else
                i++;
        }
        if (found)
            return i;
        else
            return -1;
    }


}
