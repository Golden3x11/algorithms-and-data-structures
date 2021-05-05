package Zadanie3;

public interface IStack<E> {
    boolean isEmpty();
    boolean isFull();
    E pop() throws EmptyStackException;
    void push(E elem);
    int size();
    E top() throws EmptyStackException;
}
