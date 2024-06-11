package grafalgoritmer;

class Edge<V> implements Comparable<Edge<V>>  {
  int u;
  int v;
  int e;

  public Edge(int u, int v, int e) {
    this.u = u;
    this.v = v;
    this.e = e;
  }

  public boolean equals(Object o) {
    return u == ((Edge)o).u && v == ((Edge)o).v;
  }

  public int element() {
    return e;
  }

  @Override
  public int compareTo(Edge<V> edge) {
    return Integer.compare(this.e, edge.e);
  }

  public String toString(){
    return "(" + u + ", " + v +"): " + e;
  }
}
