package Zadanie1;

public class MyOneWayLinkedList<Integer> implements MyList<Integer> {
    public class Element<Integer> {
        private int value;
        private Element next;

        Element(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
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
    public MyOneWayLinkedList(){}
    @Override
    public void displayFromFirstToLast() {
        print(head);
        System.out.println();
    }
    private void print(Element head){
        if(head==null)
            return;

        System.out.print(head.getValue()+",");
        print(head.next);

    }
    @Override
    public void displayFromLastToFirst() {
        printReverse(head);
        System.out.println();
    }
    private void printReverse(Element head){
        if(head==null)
            return;

        printReverse(head.next);
        System.out.print(head.value+",");

    }

    @Override
    public int sum() {
        return sum(head);
    }
    private int sum(Element head){
        if(head==null)
            return 0;
        else
            return head.value + sum(head.next);
    }

    @Override
    public int size() {
        return size(head);
    }
    private int size(Element head){
        if(head==null)
            return 0;
        else
            return 1 + size(head.next);
    }

    @Override
    public Element findValue(int value) {
        return findValue(head,value);
    }
    private Element findValue(Element head, int value){
        if(head==null)
            return null;
        else if(value==head.value)
            return head;
        else
            return findValue(head.next,value);

    }

    @Override
    public void add(int value) {
        head=insertEnd(head,value);
    }
    private Element insertEnd(Element head,int value){
        if(head==null)
            return new Element(value);
        else
            head.next=insertEnd(head.next,value);

        return head;
    }

    @Override
    public void remove() {
        remove(head);
    }
    private void remove(Element head){
        if(head==null)
            return;
        if(head.next.next==null){
            head.setNext(null);
        }
        else
            remove(head.next);
    }


    @Override
    public void reverseList() {
        head=reverseList(head);
    }
    private Element reverseList(Element head){
        if(head==null || head.next==null)
            return head;
        Element newHead =reverseList(head.next);
        head.next.next=head;
        head.next=null;
        return newHead;
    }
}
