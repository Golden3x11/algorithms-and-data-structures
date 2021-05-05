package Stack;

import java.util.Arrays;

public class UnlimitedStack<T> implements IStack<T>{
    private T[] array;
    private int topIndex;

    @SuppressWarnings("unchecked")
    public UnlimitedStack (int size){
        array=(T[])(new Object[size]);
    }
    @Override
    public boolean isEmpty() {
        return topIndex==0;
    }

    @Override
    public boolean isFull() {
        return topIndex==array.length;
    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[--topIndex];
    }

    @Override
    @SuppressWarnings("unchecked")
    public void push(T elem) {
        if(isFull()){
            //T[] arrayCopy=array;
            //array= Arrays.copyOf(arrayCopy,arrayCopy.length*2);
            T[] copy = (T[])(new Object[size()*2]);
            System.arraycopy(array, 0, copy, 0, size());
            array = copy;
        }
        array[topIndex++]=elem;
    }

    @Override
    public int size() {
        return topIndex;
    }

    @Override
    public T top() throws EmptyStackException {
        return array[topIndex-1];
    }
}
