package Zadanie5;

import OneWayLinkedListWithHead.OneWayLinkedListWithHead;

public class Test {
    public static void main(String[] args) {
        OneWayLinkedListWithHeadAndDisplay<Integer> linkedList=new OneWayLinkedListWithHeadAndDisplay<>();
        for(int i=0;i<5;i++)
            linkedList.add(i);
        linkedList.displayFromFirstToLast();
        System.out.println("_______________");
        linkedList.displayFromLastToFirst();
    }
}
