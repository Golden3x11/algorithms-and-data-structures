package Zadanie3;

import OneWayLinkedListWithHead.OneWayLinkedListWithHead;

public class ListStack<E> implements IStack<E> {
    OneWayLinkedListWithHead<E> linkedList;
    public ListStack(){
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
    public E pop() throws EmptyStackException {
        E value=linkedList.remove(0);
        if(value==null) throw new EmptyStackException();
        return value;
    }

    @Override
    public void push(E elem) {
        linkedList.add(0,elem);
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public E top() throws EmptyStackException {
        E value=linkedList.get(0);
        if(value==null)
            throw new EmptyStackException();
        return value;
    }
}
