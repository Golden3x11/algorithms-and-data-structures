package Zadanie5;

import OneWayLinkedListWithHead.OneWayLinkedListWithHead;

public class OneWayLinkedListWithHeadAndDisplay<E> extends OneWayLinkedListWithHead<E> {
    public void displayFromFirstToLast(){
        displayToEnd(0);
    }
    public void displayToEnd(int from){
        if(from<this.size()) {
            System.out.println(this.get(from));
            displayToEnd(++from);
        }

    }

    public void displayFromLastToFirst(){
        displayToFirst(size()-1);
    }
    public void displayToFirst(int from){
        if(from>=0) {
            System.out.println(this.get(from));
        displayToFirst(--from);
        }
    }
}
