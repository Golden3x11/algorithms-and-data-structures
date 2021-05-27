import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
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
    private ArrayList<ArrayList<Edge>> lists;
    ArrayList<Node> vertices;
    ArrayList<Edge> edges;
    GraphRepresentation representation;
    public Graph(GraphRepresentation representation) {
        this.representation=representation;
        vertices=new ArrayList<>();
        edges=new ArrayList<>();
        if(representation==GraphRepresentation.Matrix)
            matrix=new int[10][10];
        else if(representation==GraphRepresentation.IncidenceMatrix)
            incidenceMatrix=new int[10][100];
        lists=new ArrayList<>();
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
    public void BFS(Node node){
        int[] color=new int[vertices.size()];//0-white 1-grey 2-black
        int[] distance=new int[vertices.size()];
        Integer[] parent=new Integer[vertices.size()];
        result=new ArrayList<>(vertices.size());
        for(int i=0;i<vertices.size();i++){
            color[i]=0;
            distance[i]=Integer.MAX_VALUE;
            parent[i]=null;
        }
        ArrayList<Edge> edgesCopy=new ArrayList<>(edges);
        int x=vertices.indexOf(node);
        color[x]=1;
        distance[x]=0;
        ArrayBlockingQueue<Integer> queue=new ArrayBlockingQueue<>(vertices.size());
        queue.add(x);
        while (!queue.isEmpty()){
            x=queue.peek();
            queue.remove(x);
            result.add(x);
            for(int i=edgesCopy.size()-1;i>=0;i--){
                if(vertices.get(x)==edgesCopy.get(i).u){
                    int y=vertices.indexOf(edgesCopy.get(i).v);
                    edgesCopy.remove(i);
                    if(color[y]==0){
                        color[y]=1;
                        distance[y]=distance[x]+1;
                        parent[y]=x;
                        queue.add(y);
                    }
                }
            }
            color[x]=2;
        }
        System.out.println("BFS "+Arrays.toString(result.toArray()));
    }
    private int[] color; //0-white 1-grey 2-black
    private int[][] timeArray;
    private Integer[] parent;
    private int time;
    ArrayList<Object> result;
    public void DFS(){
        time=0;
        result=new ArrayList<>(vertices.size());
        color=new int[vertices.size()];//0-white 1-grey 2-black
        timeArray=new int[vertices.size()][2];
        parent=new Integer[vertices.size()];
        for(int i=0;i<vertices.size();i++){
            if(color[i]==0) {
                DFS_Visit(i);
            }
        }
        System.out.println("DFS "+Arrays.toString(result.toArray()));
    }
    private void DFS_Visit(int u){
        color[u]=1;
        time++;
        timeArray[u][0]=time;
        result.add(u);
        for(int i=edges.size()-1;i>=0;i--){
            if(vertices.get(u)==edges.get(i).u) {
                int v = vertices.indexOf(edges.get(i).v);
                if (color[v] == 0) {
                    parent[v] = u;
                    DFS_Visit(v);
                }
            }
        }
        color[u]=2;
        timeArray[u][1]=time;
    }
    public void DijkstraSingleSource(Node node){
        int edgesnr=0;
        int verticesT=1;
        int[] l=new int[vertices.size()];
        boolean[] done=new boolean[vertices.size()];
        Arrays.fill(done,false);
        Integer[] parent=new Integer[vertices.size()];
        Arrays.fill(l,Integer.MAX_VALUE);
        int x=vertices.indexOf(node);
        l[x]=0;
        done[x]=true;
        for(Edge edge:lists.get(x)){
            if(edge.weight!=0) {
                int y = vertices.indexOf(edge.v);
                l[y] = edge.weight;
                parent[y] = x;
            }
        }
        while(verticesT!=vertices.size()){
            int min=Integer.MAX_VALUE;
            int index=-1;
            for(int i=0;i<l.length;i++){
                if(min>l[i]&& !done[i]){
                    min=l[i];
                    index=i;
                }
            }
            l[index]=min;
            done[index]=true;
            edgesnr++;
            verticesT++;
            for(Edge edge:lists.get(index)) {
                if (edge.weight!=0) {
                    int idx = vertices.indexOf(edge.v);
                    if (l[idx] > l[index] + edge.weight) {
                        l[idx] = l[index] + edge.weight;
                        parent[idx] = index;
                    }
                }
            }
        }
        int[][] dijkstra=new int[vertices.size()][edgesnr];
        int addedEdge=0;
        for(int i=0;i<parent.length;i++){
            if(parent[i]!=null){
                dijkstra[i][addedEdge]=1;
                dijkstra[parent[i]][addedEdge++]=-1;
            }
        }
        printIncidencMatrix(dijkstra);
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
    private void printList(ArrayList<ArrayList<Edge>> lists){
        for(ArrayList<Edge> list:lists){
            System.out.print(list.get(0).u.nameOfVertex+"->");
            for(int i=1;i<list.size();i++)
                System.out.print(list.get(i).v.nameOfVertex+"->");
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
                if(incidenceMatrix[i][j]<0)
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
        ArrayList<Edge> list=new ArrayList();
        lists.add(list);
        list.add(new Edge(vertex,vertex,0));
    }
    public void removeVertex(Node vertex){
        if(vertices.contains(vertex)) {
            int index=vertices.indexOf(vertex);
            vertices.remove(vertex);
            if (representation == GraphRepresentation.Matrix) {
                matrix=delRowCol(matrix,index);
            }
            else if(representation==GraphRepresentation.IncidenceMatrix){
                incidenceMatrix=delRow(incidenceMatrix,index);
            }
            lists.remove(index);
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
                }  else if(representation==GraphRepresentation.IncidenceMatrix) {
                    incidenceMatrix[indexU][edges.size()-1]=edge.weight*(-1);
                    incidenceMatrix[indexV][edges.size()-1]=edge.weight;
                }
                if (!lists.get(indexU).contains(edge))
                    lists.get(indexU).add(edge);
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
            else if(representation==GraphRepresentation.IncidenceMatrix) {
                incidenceMatrix=delCol(incidenceMatrix,index);
            }
            lists.get(indexU).remove(edge);
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
