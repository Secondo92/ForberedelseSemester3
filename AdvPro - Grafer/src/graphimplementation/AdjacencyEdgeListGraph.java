package graphimplementation;

import java.util.*;

public class AdjacencyEdgeListGraph<V> implements Graph<V>{
    protected List<V> vertices = new ArrayList<>(); // Store vertices

    protected List<Edge> edges = new ArrayList<>(); // Store edges

    protected List<List<Edge>> edgeList = new ArrayList<>(); // Store edges

    public List<List<Edge>> getEdgeList() {
        return edgeList;
    }



    /** Construct an empty graph */
    public AdjacencyEdgeListGraph() {
    }

    @Override /** Return the number of vertices in the graph */
    public int getSize() {
        return vertices.size();
    }
    @Override /** Return the number of edges in the graph */
    public int getNumEdges() {
        return edges.size()/2;
    }
    @Override /** Return the vertices in the graph */
    public List<V> getVertices() {
        return new ArrayList<>(vertices);
    }

    @Override
    /** Return the edges in the graph */
    public List<Edge> getEdges() {
        return new ArrayList<Edge>(edges);
    }
    @Override /** Return the object for the specified vertex */
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override /** Return the index for the specified vertex object */
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override /** Return the neighbors of the specified vertex */
    public List<V> getNeighbors(V v) {
        int index = getIndex(v);
        Set<V> result = new HashSet<>();
        for (Edge e: edges){
            if (e.u == index){
                result.add(vertices.get(e.v));
            }else if(e.v== index){
                result.add(vertices.get(e.u));
            }
        }
        return new ArrayList<V>(result);
    }

    public List<Integer> getNeighborsIndex(V v) {
        int index = getIndex(v);
        Set<Integer> result = new HashSet<>();
        for (Edge e: edges){
            if (e.u == index){
                result.add(e.v);
            }else if(e.v== index){
                result.add(e.u);
            }
        }
        return new ArrayList<>(result);
    }

    @Override /** Return the incident edges of vertex v */
    public List<Edge> getIncidentEdges(V v){
        int index = getIndex(v);
        Set<Edge> result = new HashSet<>();
        for (Edge e: edges){
            if (e.u == index){
                result.add(e);
            }else if(e.v== index){
                result.add(e);
            }
        }
        return new ArrayList<>(result);
    }

    @Override /** Return the degree for a specified vertex */
    public int getDegree(V v) {
        return getNeighbors(v).size();
    }

    @Override /** Print the edges */
    public void printEdges() {
        for (int i = 0; i < edges.size(); i++) {
            System.out.println(" (" +edges.get(i).u  + "," + edges.get(i).v +") :" + edges.get(i).element());
        }
    }

    @Override /** Clear the graph */
    public void clear() {
        vertices.clear();
        edges.clear();
    }

    @Override /** Add a vertex to the graph */
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            edgeList.add(new ArrayList<>());
            return true;
        }
        else {
            return false;
        }
    }

    // @Override /** Add an edge to the graph */
    private boolean addEdge(Edge e) {
        if (e.u < 0 || e.u > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + e.u);

        if (e.v < 0 || e.v > getSize() - 1)
            throw new IllegalArgumentException("No such index: " + e.v);

        if (!edges.contains(e)) {
            edgeList.get(e.u).add(e);
            edges.add(e);
            return true;
        }
        else {
            return false;
        }
    }

    @Override /** Add an edge to the graph */
    public boolean addEdge(int u, int v) {
        return addEdge(new Edge(u, v, 0)) && addEdge(new Edge(v, u, 0)) ;
    }
    @Override /** Add an edge to the graph */
    public boolean addEdge(int u, int v, int e) {
        return addEdge(new Edge(u, v, e)) && addEdge(new Edge(v, u, e));
    }

    @Override /** Remove vertex v and return true if successful */
    public boolean remove(V v) {
        if (v == null || !vertices.contains(v)) {
            return false;
        }

        List<Edge> incidentEdges  = getIncidentEdges(v);
        for(Edge edge : incidentEdges){
            remove(edge.u, edge.v);
        }

        vertices.remove(v);
        return true;
    }

    @Override /** Remove edge (u, v) and return true if successful */
    public boolean remove(int u, int v) {
        boolean removed = false;
        Iterator<Edge> edgeIterator = edges.iterator();
        while(edgeIterator.hasNext()){
            Edge edge = edgeIterator.next();
            if(edge.u == u && edge.v == v || edge.v == u && edge.u == v){
                edgeIterator.remove();
                removed = true;
            }
        }
        return removed;
    }

//
}

