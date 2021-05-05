package Zadanie1;

public class OneWayLinkedListWithSentinelAndHead<T> implements IList<T> {
    Element sentinel=null;
    Element head=sentinel;
    public OneWayLinkedListWithSentinelAndHead(){
    }
    private Element getElement(int index)throws IndexOutOfBoundsException{
        if(index<0) throw new IndexOutOfBoundsException();
        Element element=head;
        while(index>0){
            index--;
            element=element.getNext();
        }
        if(element==sentinel)
            throw new IndexOutOfBoundsException();
        return element;
    }
    @Override
    public void insert(int index, T value) throws IndexOutOfBoundsException{
        Element newElement=new Element(value);
        if(index==0){
            if(head==sentinel)
                newElement.setNext(sentinel);
            else
                newElement.setNext(head);
            head=newElement;
        }
        else {
            Element elementBefore = getElement(index - 1);
            newElement.setNext(elementBefore.getNext());
            elementBefore.setNext(newElement);
        }
    }

    @Override
    public T get(int index)throws IndexOutOfBoundsException {
        Element<T> element=getElement(index);
        return element.getValue();
    }

    @Override
    public int size() {
        Element element=head;
        int counter=0;
        while(element!=sentinel) {
            counter++;
            element=element.getNext();
        }
        return counter;
    }

    @Override
    public void clear() {
        head=sentinel;
    }

    @Override
    public Element delete(int index)throws IndexOutOfBoundsException {
        if(head==sentinel)
            return null;
        if(index==0){
            Element removedElement=head;
            head=head.getNext();
            return removedElement;
        }
        Element element=getElement(index-1);
        if(element.getNext()==null)
            throw new IndexOutOfBoundsException();
        Element removedElement=element.getNext();
        element.setNext(removedElement.getNext());
        return removedElement;
    }

    @Override
    public Element delete(T value) {
        if(head==sentinel)
            return null;
        if(head.getValue().equals(value)) {
            Element removedElement=head;
            head=head.getNext();
            return removedElement;
        }
        Element element=head;
        while(element.getNext()!=null && element.getNext().getValue().equals(value))
            element=element.getNext();
        if(element.getNext()==null)
            return null;
        Element removedElement=element.getNext();
        element.setNext(removedElement.getNext());
        return removedElement;
    }

    @Override
    public T set(int index, T value)throws IndexOutOfBoundsException {
        Element<T> element=getElement(index);
        T removedValue=element.getValue();
        element.setValue(value);
        return removedValue;
    }

    @Override
    public int indexOf(T value) {
        Element element=head;
        int counter=0;
        while (element!=sentinel){
            if(element.getValue().equals(value))
                return counter;
            counter++;
            element=element.getNext();
        }
        return -1;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value)>=0;
    }

    @Override
    public void wyswietlListe() {
        StringBuffer buffer= new StringBuffer();
        buffer.append('[');
        if(head!=sentinel){
            Element element=head;
            while(element!=sentinel) {
                buffer.append(element.getValue().toString()).append(", ");
                element=element.getNext();
            }
            buffer.setLength(buffer.length() - 2);
        }
        buffer.append(']');
        System.out.println(buffer.toString());
    }
}
