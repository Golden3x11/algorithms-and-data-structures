package Queue;

public class IQueueNatural<T> implements IQueue<T> {
    private T[] array;
    private int index=0;

    @SuppressWarnings("unchecked")
    public IQueueNatural(int size){
        this.array=(T[]) new Object[size];
    }
    @Override
    public boolean isEmpty() {
        return index==0;
    }

    @Override
    public boolean isFull() {
        return index==array.length;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        T first=array[0];
        index--;
        for(int i=0;array[index]!=null;i++){
            array[i]=array[i+1];
            array[i+1]=null;
        }
        return first;
    }

    @Override
    public void enqueue(T elem) throws FullQueueException {
        if(isFull())
            throw new FullQueueException();
        array[index++]=elem;
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public T first() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        return array[0];
    }
}
