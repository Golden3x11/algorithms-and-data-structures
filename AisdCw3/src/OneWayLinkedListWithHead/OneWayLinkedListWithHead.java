package OneWayLinkedListWithHead;

import java.util.Iterator;
import java.util.ListIterator;

public class OneWayLinkedListWithHead<E> implements IList<E> {
    public class Element{
        private E value;
        private Element next;

        Element(E value){
            this.value=value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }
    }
    Element head=null;

    public OneWayLinkedListWithHead(){}
    private Element getElement(int index) throws IndexOutOfBoundsException{
        if(index<0) throw new IndexOutOfBoundsException();
        Element element=head;
        while(index>0 && element!=null){
            index--;
            element=element.getNext();
        }
        if(element==null)
            throw new IndexOutOfBoundsException();
        return element;
    }
    @Override
    public boolean add(E value) {
        Element element=new Element(value);
        if(head==null){
            head=element;
            return true;
        }
        Element tail=head;
        while (tail.getNext()!=null)
            tail=tail.getNext();
        tail.setNext(element);
        return true;
    }

    @Override
    public boolean add(int index, E value)throws IndexOutOfBoundsException {
        Element element=new Element(value);
        if(index==0){
            element.setNext(head);
            head=element;
            return true;
        }
        Element elementBefore=getElement(index-1);
        element.setNext(elementBefore.getNext());
        elementBefore.setNext(element);
        return true;
    }

    @Override
    public void clear() {
        head=null;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value)>=0;
    }

    @Override
    public E get(int index)throws IndexOutOfBoundsException {
        Element element=getElement(index);
        return element.getValue();
    }

    @Override
    public E set(int index, E value) throws IndexOutOfBoundsException{
        Element element=getElement(index);
        E replacedValue=element.getValue();
        element.setValue(value);
        return replacedValue;
    }

    @Override
    public int indexOf(E value) {
        Element element = head;
        int counter = 0;
        while (element != null){
            if (element.getValue().equals(value))
                return counter;
            counter++;
            element = element.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public E remove(int index)throws IndexOutOfBoundsException {
        if(head==null)
            return null;
        if(index==0){
            E removedValue=head.getValue();
            head=head.getNext();
            return removedValue;
        }
        Element elementBefore=getElement(index-1);
        if(elementBefore.getNext()==null)
            throw new IndexOutOfBoundsException();
        E removedValue=elementBefore.getNext().getValue();
        elementBefore.setNext(elementBefore.getNext().getNext());
        return removedValue;
    }

    @Override
    public boolean remove(E value) {
        if(head==null)
            return false;
        if(head.getValue().equals(value)){
            head=head.getNext();
            return true;
        }
        Element element=head;
        while(element.getNext()!=null && element.getValue().equals(value))
            element=element.getNext();
        if(element.getNext()==null)
            return false;
        element.setNext(element.getNext().getNext());
        return true;
    }

    @Override
    public int size() {
        Element element=head;
        int counter=0;
        while(element!=null){
            counter++;
            element=element.getNext();
        }
        return counter;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Element element=head;
            @Override
            public boolean hasNext() {
                return element!=null;
            }

            @Override
            public E next() {
                E value=element.getValue();
                element=element.getNext();
                return value;
            }
        } ;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }
}
