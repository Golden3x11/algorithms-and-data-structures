package Zadanie4;

import OneWayLinkedListWithHead.IList;

import java.util.Iterator;
import java.util.ListIterator;

public class TwoWayLinkedListWithHead<E> implements IList<E> {
    private class Element{
        private E value;
        private Element next;
        private Element previous;

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

        public Element getPrevious() {
            return previous;
        }

        public void setPrevious(Element previous) {
            this.previous = previous;
        }
        public void insertAfter(Element element){
            element.setNext(this.getNext());
            element.setPrevious(this);
            this.getNext().setPrevious(element);
            this.setNext(element);
        }
        public void insertBefore(Element element){
            element.setNext(this);
            element.setPrevious(this.getPrevious());
            this.getPrevious().setNext(element);
            this.setPrevious(element);
        }
        public void remove(){
            this.getNext().setPrevious(this.getPrevious());
            this.getPrevious().setNext(this.getNext());
        }
    }
    Element head=null;
    private Element getElement(int index)throws IndexOutOfBoundsException{
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
    private Element getElement(E value){
        Element element=head;
        while(element!=null && !element.getValue().equals(value))
            element=element.getNext();
        return element;
    }
    @Override
    public boolean add(E value) {
        Element newElement=new Element(value);
        if(head==null){
            head=newElement;
            return true;
        }
        Element elementBefore=getElement(size()-1);
        elementBefore.setNext(newElement);
        newElement.setPrevious(elementBefore);
        return true;
    }

    @Override
    public boolean add(int index, E value)throws IndexOutOfBoundsException {
        Element element= new Element(value);
        if(index==0){
            element.setNext(head);
            if(head==null)
                head=element;
            head=element;
            return true;
        }
        else if(index==size()){
            add(value);
        }
        else {
            Element elementBefore = getElement(index - 1);
            elementBefore.insertAfter(element);
        }
        return true;
    }

    @Override
    public void clear() {
        head=null;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value)!=-1;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        Element actualElement=getElement(index);
        return actualElement.getValue();
    }

    @Override
    public E set(int index, E value) throws IndexOutOfBoundsException {
        Element actualElement=getElement(index);
        E replacedValue=actualElement.getValue();
        actualElement.setValue(value);
        return replacedValue;
    }

    @Override
    public int indexOf(E value) {
        Element element=head;
        int counter=0;
        while (element!=null){
            if(element.getValue().equals(value))
                return counter;
            counter++;
            element=element.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if(head==null)
            return null;
        if(index==0){
            E removedValue = head.getValue();
            head.getNext().setPrevious(head.getPrevious());
            head=head.getNext();
            return removedValue;
        }
        else if(index==size()-1){
            Element elementBefore=getElement(index-1);
            if(elementBefore.getNext()==null)
                throw new IndexOutOfBoundsException();
            E removedValue=elementBefore.getNext().getValue();
            elementBefore.setNext(elementBefore.getNext().getNext());
            return removedValue;
        }
        Element element=getElement(index);
        element.remove();
        return element.getValue();
    }

    @Override
    public boolean remove(E value) {
        if(head==null)
            return false;
        if(head.getValue().equals(value)){
            head.getNext().setPrevious(head.getPrevious());
            head=head.getNext();
            return true;
        }
        Element element=getElement(value);
        if(element==null)
            return false;
        if(element.getNext()==null){
            element.getPrevious().setNext(null);
            return true;
        }
        element.remove();
        return true;
    }

    @Override
    public int size() {
        Element element=head;
        int counter=0;
        while (element!=null) {
            counter++;
            element=element.getNext();
        }
        return counter;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator() {
            Element current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E value = current.getValue();
                current = current.getNext();
                return value;
            }
        };
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            Element current=head;
            Element lastCurrent;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E value = current.getValue();
                lastCurrent=current;
                current = current.getNext();
                return value;
            }

            @Override
            public boolean hasPrevious() {
                if(current==null){
                    if(current==head)
                        return false;
                    return true;
                }
                return current.getPrevious()!=null;
            }

            @Override
            public E previous() {
                if(current==null &&current!=head){
                    current=lastCurrent;
                }
                else
                    current=current.getPrevious();
                return current.getValue();
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void addList(TwoWayLinkedListWithHead<E> linkedList){
        Element lastElement=this.getElement(size()-1);
        Element firstElementOfAddedList=linkedList.getElement(0);
        lastElement.setNext(firstElementOfAddedList);
        firstElementOfAddedList.setPrevious(lastElement);
    }
    public void addList(int index,TwoWayLinkedListWithHead<E> linkedList){
        Element elementBefore=this.getElement(index-1);
        Element elementAfter=this.getElement(index);

        Element elementFirstOfAddedList=linkedList.getElement(0);
        Element elementLastOfAddedList=linkedList.getElement(size()-1);

        //Połączenie na początku dodawanej listy
        elementBefore.setNext(elementFirstOfAddedList);
        elementFirstOfAddedList.setPrevious(elementBefore);
        //Połączenie na końcu dodawanej listy
        elementAfter.setPrevious(elementLastOfAddedList);
        elementLastOfAddedList.setNext(elementAfter);
    }
}
