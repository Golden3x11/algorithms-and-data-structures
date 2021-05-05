package Stack;

public class IStackDrowning<T> implements IStack<T> {
    private T array[];
    private int top=0;


    @SuppressWarnings("unchecked")
    public IStackDrowning(int initialSize){
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
    public void push(T elem) {
        if(isFull()){
            for(int i=0;i<array.length-1;i++){
                array[i]=array[i+1];
                array[i+1]=null;
            }
            top--;
        }
        array[top++]=elem;
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[top-1];
    }
}
