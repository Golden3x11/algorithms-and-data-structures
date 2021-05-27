public class Test {
    public static void main(String[] args) {
        Graph graph=new Graph(GraphRepresentation.List);
        graph.addVertex(new Graph.Node(0));
        graph.addVertex(new Graph.Node(1));
        graph.addVertex(new Graph.Node(2));
        graph.addVertex(new Graph.Node(3));
        graph.addVertex(new Graph.Node(4));
        graph.addVertex(new Graph.Node(5));
        graph.addEdge(new Graph.Edge(graph.vertices.get(0),graph.vertices.get(1)));
        graph.addEdge(new Graph.Edge(graph.vertices.get(1),graph.vertices.get(2),2));
        graph.addEdge(new Graph.Edge(graph.vertices.get(1),graph.vertices.get(3)));
        graph.addEdge(new Graph.Edge(graph.vertices.get(3),graph.vertices.get(2)));
        graph.addEdge(new Graph.Edge(graph.vertices.get(1),graph.vertices.get(4),8));
        graph.addEdge(new Graph.Edge(graph.vertices.get(3),graph.vertices.get(4)));
        graph.addEdge(new Graph.Edge(graph.vertices.get(4),graph.vertices.get(5)));
        graph.print();
        graph.BFS(graph.vertices.get(0));
        graph.DFS();
        System.out.println("MST");
        graph.MST();
        System.out.println("Dijkstra");
        graph.DijkstraSingleSource(graph.vertices.get(0));

        //System.out.println();
        //graph.removeEdge(graph.edges.get(4));
        //graph.removeVertex(graph.vertices.get(0));
        //graph.print();

    }
}
