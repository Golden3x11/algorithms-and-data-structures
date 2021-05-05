package Zadanie4;

import java.util.ListIterator;

public class Test {
    public static void main(String[] args) {
        TwoWayLinkedListWithHead<Integer> linkedList1=new TwoWayLinkedListWithHead<>();
        for(int i=0;i<5;i++)
            linkedList1.add(i);
        TwoWayLinkedListWithHead<Integer> linkedList2=new TwoWayLinkedListWithHead<>();
        for(int i=20;i<25;i++)
            linkedList2.add(i);
        linkedList1.addList(1,linkedList2);

        for (Integer integer : linkedList1) System.out.println(integer);
    }
}
