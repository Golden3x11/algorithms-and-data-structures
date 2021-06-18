import java.util.Iterator;
import java.util.ListIterator;

public class TwoWayLinkedList<E> implements IList<E>{
    public class Element{
        private E value;
        private Element next;
        private Element previous;

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
        Element(E value){
            this.value=value;
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
    Element sentinel;
    public TwoWayLinkedList(){
        sentinel=new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrevious(sentinel);
    }
    private Element getElement(int index)throws IndexOutOfBoundsException,NullPointerException{
        if(index<0) throw new IndexOutOfBoundsException();
        if(sentinel.getNext()==sentinel)
            throw new NullPointerException();
        Element element=sentinel.getNext();
        int counter=0;
        while(element!=sentinel && counter<index){
            counter++;
            element=element.getNext();
        }
        if(element==sentinel){
            throw new IndexOutOfBoundsException();
        }
        return element;
    }
    private Element getElement(E value){
        Element element=sentinel.getNext();
        while(element!=sentinel && !element.getValue().equals(value)){
            element=element.getNext();
        }
        if(element==sentinel){
            return null;
        }
        return element;
    }
    @Override
    public boolean add(E value) {
        Element element=new Element(value);
        sentinel.insertBefore(element);
        return true;
    }

    @Override
    public boolean add(int index, E value)throws IndexOutOfBoundsException {
        Element element=new Element(value);
        if(index==0)
            sentinel.insertAfter(element);
        else {
            Element elementBefore = getElement(index - 1);
            elementBefore.insertAfter(element);
        }
        return true;
    }

    @Override
    public void clear() {
        sentinel.setPrevious(sentinel);
        sentinel.setNext(sentinel);
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value)!=-1;
    }

    @Override
    public E get(int index)throws IndexOutOfBoundsException {
        return getElement(index).getValue();
    }

    @Override
    public E set(int index, E value)throws IndexOutOfBoundsException {
        Element element=getElement(index);
        E removedValue=element.getValue();
        element.setValue(value);
        return removedValue;
    }

    @Override
    public int indexOf(E value) {
        Element element=sentinel.getNext();
        int counter=0;
        while(element!=sentinel && !element.getValue().equals(value)){
            counter++;
            element=element.getNext();
        }
        if(element==sentinel)
            return -1;
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.getNext()==sentinel;
    }



    @Override
    public E remove(int index)throws IndexOutOfBoundsException {
        Element element=getElement(index);
        element.remove();
        return element.getValue();
    }

    @Override
    public boolean remove(E value) {
        Element element=getElement(value);
        if(element==null)
            return false;
        element.remove();
        return true;
    }

    @Override
    public int size() {
        Element element=sentinel.getNext();
        int counter=0;
        while(element!=sentinel){
            counter++;
            element=element.getNext();
        }
        return counter;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Element current=sentinel;
            @Override
            public boolean hasNext() {
                return current.getNext()!=sentinel;
            }

            @Override
            public E next() {
                current=current.getNext();
                return current.getValue();
            }
        };
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            boolean wasNext=false;
            boolean wasPrevious=false;
            Element current=sentinel;
            @Override
            public boolean hasNext() {
                return current.getNext()!=sentinel;
            }

            @Override
            public E next() {
                wasNext=true;
                wasPrevious=false;
                current=current.getNext();
                return current.getValue();
            }

            @Override
            public boolean hasPrevious() {
                return current!=sentinel;
            }

            @Override
            public E previous() {
                wasNext=false;
                wasPrevious=true;
                E value= current.getValue();
                current=current.getPrevious();
                return value;
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
                if(wasNext){
                    Element curr=current.getPrevious();
                    current.remove();
                    current=curr;
                    wasNext=false;}
                if(wasPrevious){
                    current.getNext().remove();
                    wasPrevious=false;}
            }

            @Override
            public void set(E e) {
                if(wasNext){
                    current.setValue(e);
                    wasNext=false;}
                if(wasPrevious){
                    current.getNext().setValue(e);
                    wasNext=false;}
            }

            @Override
            public void add(E e) {
                Element element=new Element(e);
                current.insertAfter(element);
                current=current.getNext();
            }
        };
    }
}
