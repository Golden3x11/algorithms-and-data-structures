package Iterators;

import java.util.Iterator;

public class FilterIterator implements Iterator {
    private Iterator iterator;
    private Predicate predicate;

    private int nextNumber;
    private boolean hasNextValid=true;

    public FilterIterator(Iterator iterator,Predicate predicate){
        this.iterator=iterator;
        this.predicate=predicate;
        findNextValid();
    }
    public void findNextValid(){
        while(iterator.hasNext()) {
            nextNumber = (int) iterator.next();
            if (predicate.accept(nextNumber))
                return;
        }

        nextNumber=0;
        hasNextValid=false;

    }
    @Override
    public boolean hasNext() {
        return hasNextValid;
    }

    @Override
    public Object next() {
        int validNumber=nextNumber;
        findNextValid();
        return validNumber;
    }
}
