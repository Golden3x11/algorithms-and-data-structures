package Iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NumbersIterator implements Iterator {
    private int number;
    private int endNumber;

    public NumbersIterator(int number,int endNumber){
        this.number=number;
        this.endNumber=endNumber;
    }
    @Override
    public boolean hasNext() {
        return number<=endNumber;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if(hasNext())
            return number++;
        else
            throw new NoSuchElementException();
    }
}
