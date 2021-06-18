import java.util.Iterator;
import java.util.ListIterator;

public class OneWayLinkedList<E> implements IList<E> {
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

    public OneWayLinkedList(){}
    public Element getElement(int index) throws IndexOutOfBoundsException,NullPointerException{
        if(index<0) throw new IndexOutOfBoundsException();
        Element actualElement=head;
        if(head==null)
            throw new NullPointerException();
        while(index>0 && actualElement!=null){
            index--;
            actualElement=actualElement.getNext();
        }
        if(actualElement==null)
            throw new IndexOutOfBoundsException();
        return actualElement;
    }
    @Override
    public boolean add(E value) {
        Element newElement= new Element(value);
        if(head==null){
            head=newElement;
            return true;
        }
        Element tail=head;
        while(tail.getNext()!=null)
            tail=tail.getNext();
        tail.setNext(newElement);
        return true;

    }

    @Override
    public boolean add(int index, E value) throws IndexOutOfBoundsException{
        Element newElement=new Element(value);
        if(index==0){
            newElement.setNext(head);
            head=newElement;
            return true;
        }
        Element elementBefore=getElement(index-1);
        newElement.setNext(elementBefore.getNext());
        elementBefore.setNext(newElement);
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
        int pos=0;
        Element actualElement=head;
        while(actualElement!=null){
            if(actualElement.getValue().equals(value))
                return pos;
            pos++;
            actualElement=actualElement.getNext();
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
        if(index==0) {
            E removedValue = head.getValue();
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
        if(head.getValue().equals(value)) {
            head=head.getNext();
            return true;
        }
        Element actualElement=head;
        while(actualElement.getNext()!=null && !actualElement.getNext().getValue().equals(value))
            actualElement=actualElement.getNext();
        if(actualElement.getNext()==null)
            return false;
        actualElement.setNext(actualElement.getNext().getNext());
        return true;
    }

    @Override
    public int size() {
        int size = 0;
        Element actualElement=head;
        while (actualElement!=null) {
            size++;
            actualElement=actualElement.getNext();
        }
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator() {
            Element actualElem = head;

            @Override
            public boolean hasNext() {
                return actualElem != null;
            }

            @Override
            public E next() {
                E value = actualElem.getValue();
                actualElem = actualElem.getNext();
                return value;
            }
        };
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }
}
