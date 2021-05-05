package Zadanie1;

public interface MyList<Integer> {
    public void displayFromFirstToLast();
    public void displayFromLastToFirst();
    public int sum();
    public int size();
    public MyOneWayLinkedList.Element findValue(int value);
    public void add(int value);
    public void remove();
    public void reverseList();
}
