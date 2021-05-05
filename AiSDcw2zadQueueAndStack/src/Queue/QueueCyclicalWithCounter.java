package Queue;

public class QueueCyclicalWithCounter<T> implements IQueue<T>{
    private int begin=0;
    private int end=0;
    private int counter=0;
    private T[] array;

    @SuppressWarnings("unchecked")
    public QueueCyclicalWithCounter(int size){
        this.array=(T[]) new Object[size];
    }
    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean isFull() {
        return size()== array.length;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        begin%=array.length;
        counter--;
        return array[begin++];
    }

    @Override
    public void enqueue(T elem) throws FullQueueException {
        if(isFull())
            throw new FullQueueException();
        array[end++]=elem;
        counter++;
        end%= array.length;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public T first() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        return array[begin];
    }
}
