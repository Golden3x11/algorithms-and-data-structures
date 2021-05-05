package Zadanie2;

public interface IQueue<E> {
        boolean isEmpty();
        boolean isFull();
        E dequeue() throws EmptyQueueException;
        void enqueue(E elem);
        int size();  // zwraca liczbę elementów w kolejce
        E first() throws EmptyQueueException;
}
