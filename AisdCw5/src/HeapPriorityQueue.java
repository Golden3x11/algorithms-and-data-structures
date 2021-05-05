import java.util.Comparator;

public class HeapPriorityQueue implements IQueue{
    private int[] array;
    private int size;
    public HeapPriorityQueue(int[] array) {
        this.array = array;
        this.size=0;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public boolean isFull() {
        return size==array.length;
    }

    @Override
    public int dequeue() throws EmptyQueueException {
        if(isEmpty()) throw new EmptyQueueException();
        int result=array[0];
        if(size>0){
            array[0]=array[size-1];
            sink(0,size);
        }
        size--;
        return result;
    }

    @Override
    public void enqueue(int value) throws FullQueueException {
        if(isFull()) throw new FullQueueException();
        array[size]=value;
        swim(array[size]);
        size++;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int first() throws EmptyQueueException {
        return 0;
    }
    private void swap(int left, int right)
    {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
    private void sink(int idx, int n){
        int idxBigger=2*idx+1;
        if(idxBigger<n){
            if(idxBigger+1<n && array[idxBigger]<array[idxBigger+1])
                idxBigger++;
            if(array[idx]<array[idxBigger]){
                swap(idx,idxBigger);
                sink(idxBigger,n);
            }
        }
    }
    private void swim(int idx) {
        int parent = (idx - 1) / 2;
        if (parent >= 0) {
            if (array[idx] > array[parent]) {
                swap(idx, parent);
                swim(parent);
            }
        }
    }
    public void createHeap(int n){
        for(int i=(n-1)/2;i>=0;i--)
            sink(i, n);
    }
}
