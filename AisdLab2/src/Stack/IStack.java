package Stack;

public interface IStack<T>{
    boolean isEmpty();
    boolean isFull();
    T pop() throws EmptyStackException;
    void push(T elem);
    int size();
    T top() throws EmptyStackException;
}
