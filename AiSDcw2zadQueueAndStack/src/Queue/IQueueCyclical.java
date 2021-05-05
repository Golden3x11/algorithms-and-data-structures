package Queue;

public class IQueueCyclical<T> implements IQueue<T> {
    private int begin=0;
    private int end=0;
    private T[] array;

    @SuppressWarnings("unchecked")
    public IQueueCyclical(int size){
        this.array=(T[]) new Object[size+1];
    }
    @Override
    public boolean isEmpty() {
        return begin==end;
    }

    @Override
    public boolean isFull() {
        return begin%array.length==(end+1)% array.length;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        begin%=array.length;
        return array[begin++];
    }

    @Override
    public void enqueue(T elem) throws FullQueueException {
        if(isFull())
        throw new FullQueueException();
        array[end++]=elem;
        end%= array.length;
    }

    @Override
    public int size() {
        return (end+ array.length-begin)% array.length;
    }

    @Override
    public T first() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        return array[begin];
    }
}
