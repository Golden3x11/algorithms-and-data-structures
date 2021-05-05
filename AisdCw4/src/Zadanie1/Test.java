package Zadanie1;

public class Test {
    public static void main(String[] args) {
        MyOneWayLinkedList linkedList=new MyOneWayLinkedList();
        linkedList.add(3);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);

        linkedList.displayFromFirstToLast();
        linkedList.reverseList();
        linkedList.displayFromLastToFirst();
        System.out.println(linkedList.sum());
        System.out.println(linkedList.size());
        System.out.println(linkedList.findValue(60));
    }
}
