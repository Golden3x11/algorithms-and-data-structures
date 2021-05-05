package Iterators;

import java.util.Iterator;

public class FilterIterator<T> implements Iterator<T> {
    private Iterator<T> iterator;
    private Predicate<T> predicate;

    private T elemNext=null;
    private boolean bHasNext=true;

    public FilterIterator(Iterator iterator,Predicate predicate){
        this.iterator=iterator;
        this.predicate=predicate;
        findNextValid();
    }
    public void findNextValid(){
        while(iterator.hasNext()) {
            elemNext = iterator.next();
            if (predicate.accept(elemNext)) {
                return;
            }
        }
        elemNext=null;
        bHasNext=false;
    }
    @Override
    public boolean hasNext() {
        return bHasNext;
    }

    @Override
    public T next() {
        T ValidObject=elemNext;
        findNextValid();
        return ValidObject;
    }
}
