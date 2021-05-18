import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Graph {
    public static class Node{
        int nameOfVertex;
        public Node(int nameOfVertex) {
            this.nameOfVertex = nameOfVertex;
        }
    }
    public static class Edge{
        Node u;
        Node v;
        int weight;

        public Edge(Node u,Node v) {
            this.u = u;
            this.v = v;
            this.weight=1;
        }
        public Edge(Node u, Node v,int weight){
            this.u = u;
            this.v = v;
            this.weight=weight;
        }

        public Edge() {

        }
    }
    private int[][] matrix;
    private int[][] incidenceMatrix;
    private ArrayList<ArrayList<Node>> lists;
    ArrayList<Node> vertices;
    ArrayList<Edge> edges;
    GraphRepresentation representation;
    public Graph(GraphRepresentation representation) {
        this.representation=representation;
        vertices=new ArrayList<>();
        edges=new ArrayList<>();
        if(representation==GraphRepresentation.Matrix)
            matrix=new int[10][10];
        else if(representation==GraphRepresentation.List)
            lists=new ArrayList<>();
        else if(representation==GraphRepresentation.IncidenceMatrix)
            incidenceMatrix=new int[10][100];
    }
    private class subset {
        int parent, rank;

        public subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }
    };
    private int find(ArrayList<subset> subsets, int i)
    {
        if (subsets.get(i).parent != i)
            subsets.get(i).parent
                    = find(subsets, subsets.get(i).parent);

        return subsets.get(i).parent;
    }
    private void Union(ArrayList<subset> subsets, int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets.get(xroot).rank
                < subsets.get(yroot).rank)
            subsets.get(xroot).parent = yroot;
        else if (subsets.get(xroot).rank
                > subsets.get(yroot).rank)
            subsets.get(yroot).parent = xroot;


        else {
            subsets.get(yroot).parent = xroot;
            subsets.get(xroot).rank++;
        }
    }
    public void MST(){
        int V=vertices.size();
        ArrayList<Edge> result=new ArrayList(V);
        for(int v=0;v<V;v++){
            result.add(new Edge());
        }
        ArrayList<subset> subsets=new ArrayList<>();
        int e=0;
        ArrayList<Edge> edgesCopy=edges;
        edgesCopy.sort(new EdgeWeightComparator());
        for(int v=0;v<V;v++){
            subsets.add(new subset(v,0));
        }
        int i=0;
        while (e<V-1){
            Edge next_edge=edgesCopy.get(i++);
            int x = find(subsets, vertices.indexOf(next_edge.u));
            int y = find(subsets, vertices.indexOf(next_edge.v));
            if (x != y) {
                result.set(e++,next_edge);
                Union(subsets, x, y);
            }
        }
        int[][] mst=new int[V][e];
        for(Edge edge:result){
            if(edge.u!=null) {
                mst[vertices.indexOf(edge.u)][result.indexOf(edge)] = edge.weight * (-1);
                mst[vertices.indexOf(edge.v)][result.indexOf(edge)] = edge.weight;
            }
        }
        printIncidencMatrix(mst);
    }
    static class EdgeWeightComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            if (o1.weight>o2.weight) return 1;
            if (o1.weight<o2.weight) return -1;
            return 0;
        }
    }
    private void printMatrix(int[][]matrix){
        StringBuilder sb=new StringBuilder();
        sb.append("  ");
        for(int i=0;i<vertices.size();i++)
            sb.append(vertices.get(i).nameOfVertex+" ");
        System.out.println(sb);
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if(j==0)
                    System.out.print(vertices.get(i).nameOfVertex+"|");
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private void printList(ArrayList<ArrayList<Node>> lists){
        for(ArrayList<Node> list:lists){
            for(int i=0;i<list.size();i++)
                System.out.print(list.get(i).nameOfVertex+"->");
            System.out.println();
        }
    }
    private void printIncidenceMatrix(int[][] incidenceMatrix){
        StringBuilder sb=new StringBuilder();
        sb.append("   ");
        for(int i=0;i<edges.size();i++)
            sb.append(i+"  ");
        System.out.println(sb);
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                if(j==0)
                    System.out.print(vertices.get(i).nameOfVertex+"|");
                if(incidenceMatrix[i][j]==-1)
                    System.out.print(incidenceMatrix[i][j] + " ");
                else
                    System.out.print(" "+incidenceMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    private void printIncidencMatrix(int[][] incidenceMatrix){
        StringBuilder sb=new StringBuilder();
        sb.append("   ");
        for(int i=0;i<incidenceMatrix[0].length;i++)
            sb.append(i+"  ");
        System.out.println(sb);
        for (int i = 0; i < incidenceMatrix.length; i++) {
            for (int j = 0; j < incidenceMatrix[0].length; j++) {
                if(j==0)
                    System.out.print(vertices.get(i).nameOfVertex+"|");
                if(incidenceMatrix[i][j]==-1)
                    System.out.print(incidenceMatrix[i][j] + " ");
                else
                    System.out.print(" "+incidenceMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    public void print(){
        if (representation == GraphRepresentation.Matrix)
            printMatrix(matrix);
        else if (representation == GraphRepresentation.List)
            printList(lists);
        else
            printIncidenceMatrix(incidenceMatrix);
    }
    public void addVertex(Node vertex){
        vertices.add(vertex);
        if(representation == GraphRepresentation.List){
            ArrayList<Node> list=new ArrayList();
            lists.add(list);
            list.add(vertex);
        }
    }
    public void removeVertex(Node vertex){
        if(vertices.contains(vertex)) {
            int index=vertices.indexOf(vertex);
            vertices.remove(vertex);
            if (representation == GraphRepresentation.Matrix) {
                matrix=delRowCol(matrix,index);
            }
            else if (representation == GraphRepresentation.List) {
                lists.remove(index);
            }
            else if(representation==GraphRepresentation.IncidenceMatrix){
                incidenceMatrix=delRow(incidenceMatrix,index);
            }
        }
    }
    public void addEdge(Edge edge){
        if(!edges.contains(edge)) {
            edges.add(edge);
            int indexU = vertices.indexOf(edge.u);
            int indexV = vertices.indexOf(edge.v);
            if (indexU != -1 && indexV != -1) {
                if (representation == GraphRepresentation.Matrix) {
                    matrix[indexU][indexV] = edge.weight;
                } else if (representation == GraphRepresentation.List) {
                    if (!lists.get(indexU).contains(edge.v))
                        lists.get(indexU).add(edge.v);
                } else if(representation==GraphRepresentation.IncidenceMatrix) {
                    incidenceMatrix[indexU][edges.size()-1]=edge.weight*(-1);
                    incidenceMatrix[indexV][edges.size()-1]=edge.weight;
                }
            }
        }
    }
    public void removeEdge(Edge edge){
        if(edges.contains(edge)) {
            int index=edges.indexOf(edge);
            edges.remove(edge);
            int indexU = vertices.indexOf(edge.u);
            int indexV = vertices.indexOf(edge.v);
            if (representation == GraphRepresentation.Matrix) {
                matrix[indexU][indexV]=0;
            }
            else if (representation == GraphRepresentation.List) {
                lists.get(indexU).remove(edge.v);
            }
            else if(representation==GraphRepresentation.IncidenceMatrix) {
                incidenceMatrix=delCol(incidenceMatrix,index);
            }
        }

    }
    private int[][] delRow(int[][]matrix,int index){
        int w= matrix.length;
        int[][] temp = new int[w][matrix[0].length];
        int x=0;
        for(int i=0;i<vertices.size()+1;i++) {
            if (index != i) {
                System.arraycopy(matrix[i], 0, temp[x], 0, w);
                x++;
            }
        }
        return temp;
    }
    private int[][] delCol(int[][]matrix,int index){
        int w= matrix.length;
        int[][] temp = new int[w][matrix[0].length];
        for(int i=0;i<vertices.size()+1;i++) {
            System.arraycopy(matrix[i], 0, temp[i], 0, index);
            System.arraycopy(matrix[i], index+1, temp[i], index, w-index);
        }
        return temp;
    }
    private int[][] delRowCol(int[][] matrix,int index){
        int w=matrix.length;
        int[][] temp = new int[w][w];
        int x=0;
        for(int i=0;i<w;i++){
            if(i!=index) {
                System.arraycopy(matrix[i], 0, temp[x], 0, index);
                System.arraycopy(matrix[i], index+1, temp[x], index, w-index-1);
                x++;
            }
        }
        return temp;
    }
}
