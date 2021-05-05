package Queue;

public interface IQueue<T> {
    boolean isEmpty();
    boolean isFull();
    T dequeue() throws EmptyQueueException;
    void enqueue(T elem) throws FullQueueException;
    int size();  // zwraca liczbę elementów w kolejce
    T first() throws EmptyQueueException;
}
