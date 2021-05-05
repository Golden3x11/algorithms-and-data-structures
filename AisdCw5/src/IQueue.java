
public interface IQueue{
    boolean isEmpty();
    boolean isFull();
    int dequeue() throws EmptyQueueException;
    void enqueue(int value) throws FullQueueException;
    int size();
    int first() throws EmptyQueueException;

    class EmptyQueueException extends Exception {
    }

    class FullQueueException extends Exception {
    }
}
