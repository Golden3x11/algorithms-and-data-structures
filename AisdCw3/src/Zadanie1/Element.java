package Zadanie1;

class Element<T> {
    private Element next;
    private T value;
    public Element(T value) {
        this.value=value;
    }

    public Element getNext() {
        return next;
    }

    public void setNext(Element next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
