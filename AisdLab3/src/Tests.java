import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

public class Tests {
    public static void main(String[] args) {
        //testOneWayList();
        testTwoWayList();
        //System.out.println("Looped List:");
        //testLoopedList();
    }
    public static void testLoopedList(){
        OneWayLinkedList<Student> oneWayLinkedList=new OneWayLinkedList<>();
        Random rnd=new Random();
        for(int i=0;i<5;i++)
            oneWayLinkedList.add(new Student(Integer.toString(rnd.nextInt(10)+1)));

        oneWayLinkedList.getElement(4).setNext(oneWayLinkedList.getElement(0));
        System.out.println(DetectLoop.hasCycle(oneWayLinkedList));
    }
    public static void testOneWayList(){
        OneWayLinkedList<Student> oneWayLinkedList= new OneWayLinkedList<>();
        Random rnd=new Random();
        for(int i=0;i<5;i++)
            oneWayLinkedList.add(new Student(Integer.toString(rnd.nextInt(10)+1)));

        Iterator<Student> iterator=oneWayLinkedList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getIndex());
        }

        System.out.println(oneWayLinkedList.remove(0));
        System.out.println("__________________________");
        iterator=oneWayLinkedList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getIndex());
        }
        System.out.println();
        System.out.println(DetectLoop.hasCycle(oneWayLinkedList));
    }
    public static void testTwoWayList(){
        TwoWayLinkedList<Student> twoWayLinkedList=new TwoWayLinkedList<>();
        Random rnd=new Random();
        for(int i=0;i<5;i++)
            twoWayLinkedList.add(new Student(Integer.toString(rnd.nextInt(10)+1)));

        ListIterator<Student> iterator=twoWayLinkedList.listIterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().getIndex());
        }

        iterator.remove();
        System.out.println("__________________________");
        while(iterator.hasPrevious()){
            System.out.println(iterator.previous().getIndex());
        }
    }
}
