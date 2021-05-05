package Zadanie1;

public class Test {
    public static void main(String[] args) {
        OneWayLinkedListWithSentinelAndHead<Integer> oneWay=new OneWayLinkedListWithSentinelAndHead<>();
        for(int i=0;i<5;i++)
            oneWay.insert(i,i);
        System.out.println(oneWay.size());
        oneWay.delete(0);
        oneWay.delete((Integer) 1);
        oneWay.wyswietlListe();
        System.out.println(oneWay.indexOf(2));
        oneWay.clear();
        oneWay.wyswietlListe();

    }
}
