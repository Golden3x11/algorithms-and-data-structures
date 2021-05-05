import java.util.Comparator;
import java.util.NoSuchElementException;

public class BST<T> {
    class Node{
        private T value;
        private Node left;
        private Node right;
        Node(T obj){
            value=obj;}
        Node(T obj, Node leftNode,Node rightNode){
            value=obj;
            left=leftNode;
            right=rightNode;}

    }
    private Node root;
    private Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.root = null;
        this.comparator = comparator;
    }
    public void insert(T value){
        root=insert(root,value);
    }
    private Node insert(Node node,T value){
        if(node==null)
            node=new Node(value);
        else{
            int cmp=comparator.compare(node.value,value);
            if(cmp>0)
                node.left=insert(node.left,value);
            else if(cmp<0)
                node.right=insert(node.right,value);
        }
        return node;
    }
    public T search(T value){
        Node node=search(root,value);
        return node==null?null:node.value;
    }
    private Node search(Node node,T value){
        if(node==null)
            return null;
        int cmp=comparator.compare(node.value,value);
        if(cmp>0)
            return search(node.left,value);
        else if(cmp<0)
            return search(node.right,value);
        else
            return node;
    }
    public void delete(T value){
        root=delete(root,value);
    }
    private Node delete(Node node,T value){
        if(node==null) throw new NoSuchElementException();
        else{
            int cmp=comparator.compare(node.value,value);
            if(cmp>0)
                node=delete(node.left,value);
            else if(cmp<0)
                node=delete(node.right,value);
            else if(node.left!=null &&node.right!=null)
                node.right=detachMin(node,node.right);
            else
                node = (node.left != null) ? node.left : node.right;
        }
        return node;
    }
    private Node detachMin(Node deleted,Node node){
        if(node.left!=null) node.left=detachMin(deleted,node.left);
        else{
            deleted.value=node.value;
            node=node.right;
        }
        return node;
    }
}
