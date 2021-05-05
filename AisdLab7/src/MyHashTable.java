import java.util.ArrayList;

public class MyHashTable<E> {
    private ArrayList[] array;
    private int size;
    private int elements=0;
    private double acceptedLevelOfFill=0.7;
    public MyHashTable(int howMuchElements) throws WrongSizeException {
        if(howMuchElements<=0)
            throw new WrongSizeException();
        this.size = (int) (howMuchElements*(1/acceptedLevelOfFill)+1);
        array= new ArrayList[this.size];
        for(int i = 0; i< this.size; i++){
            array[i]=new ArrayList<E>();
        }
    }


    public void add(E item) throws FullTableException {
        if(levelOfFill(elements+1, size)<=acceptedLevelOfFill) {
            int index = hashFunction(item);
            if (!array[index].contains(item)) {
                array[index].add(item);
                elements++;
            }
        }
        else
            throw new FullTableException();
    }
    public void remove(E item){
        int index=hashFunction(item);
        if(array[index].contains(item)) {
            array[index].remove(item);
            elements--;
        }
    }
    public boolean contains(E item){
        return array[hashFunction(item)].contains(item);
    }
    public double levelOfFill(int element,int size){
        return (double)element/size;
    }
    public int hashFunction(E item){
        int hashCode = 0;
        hashCode ^= item.hashCode();
        return hashCode%size;
    }

}
