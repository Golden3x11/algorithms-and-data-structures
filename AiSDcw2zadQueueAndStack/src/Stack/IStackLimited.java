package Stack;

public class IStackLimited<T> implements IStack<T> {
    private T array[];
    private int top=0;


    @SuppressWarnings("unchecked")
    public IStackLimited(int initialSize){
        array=(T[])(new Object[initialSize]);
    }
    @Override
    public boolean isEmpty() {
        return top==0;
    }

    @Override
    public boolean isFull() {
        return top==array.length;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[--top];
    }

    @Override
    public void push(T elem) throws FullStackException {
        if(isFull())
            throw new FullStackException();
        array[top++]=elem;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public T top() throws EmptyStackException {
        return array[top-1];
    }
}
