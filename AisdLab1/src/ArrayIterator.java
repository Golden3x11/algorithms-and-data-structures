import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T> {
    private T array[];
    private int pos=0;

    public ArrayIterator(T anArray[]) throws NullPointerException {
        if(anArray!=null) {
            array = anArray;
        }
        else
            throw new NullPointerException();
    }

    public void first(){
        pos=0;
    }
    public T current()throws NoSuchElementException{
        if(!isDone()){
            return array[pos];
        }
        else
            throw new NoSuchElementException();
    }
    public void nextOwn(){
        pos++;
    }
    public void last(){
        pos= array.length-1;
    }
    public void previous(){
        pos--;
    }
    public boolean isDone(){
        return !hasNext() || pos<0;
    }
    @Override
    public boolean hasNext() {
        return pos < array.length;
    }
    public boolean hasPrevious(){
        return pos >0;
    }

    @Override
    public T next() {
        return null;
    }
}
