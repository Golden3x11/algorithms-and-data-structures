package Zadanie2;

import OneWayLinkedListWithHead.OneWayLinkedListWithHead;
import java.util.ListIterator;

public class ListQueue<E> implements IQueue<E>{
    OneWayLinkedListWithHead<E> linkedList;
    public ListQueue(){
        linkedList=new OneWayLinkedListWithHead<E>();
    }
    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        E value= linkedList.remove(0);
        if(value==null)
            throw new EmptyQueueException();
        return value;
    }

    @Override
    public void enqueue(E elem){
        linkedList.add(elem);
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public E first() throws EmptyQueueException {
        E value=linkedList.get(0);
        if(value==null) throw new EmptyQueueException();
        return value;
    }
}
