package Zadanie2;

import java.util.ArrayList;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        InsertSort<Person> insertSort=new InsertSort<>(new AllComparator());
        ComplexComparator complexComparator=new ComplexComparator();

        complexComparator.addComparator(new AgeComparator());
        complexComparator.addComparator(new NameComparator());
        complexComparator.addComparator(new SurnameComparator());

        SelectSort<Person> selectSort=new SelectSort<>(complexComparator);
        Person[] array={new Person("Konrad","Kiełczyński",19),
                        new Person("Konrad","Kiełczyński",20),
                        new Person("Jakub","Nowak",13),
                        new Person("Jakub", "Kowalski",13),
                        new Person("Szymon","Paszkiewicz",30)};
        insertSort.sort(array);
        for(int i=0;i< array.length;i++)
            System.out.println(array[i].toString());
    }





    static class AgeComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {
            if(o1.getAge()> o2.getAge()) return 1;
            if(o1.getAge()< o2.getAge()) return -1;
            return 0;
        }
    }
    static class NameComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
    static class SurnameComparator implements Comparator<Person> {
        @Override
        public int compare(Person o1, Person o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
    }
    static class ComplexComparator implements Comparator<Person>{
        private final ArrayList<Comparator> _comparators =new ArrayList<Comparator>();
        public void addComparator(Comparator<Person> comparator)
        {
            _comparators.add(comparator);
        }

        public int compare(Person left, Person right) throws ClassCastException {
            int result = 0;
            for (Comparator obj:_comparators){
                Comparator<Person> comp=obj;
                result=comp.compare(left, right);
                if(result!=0) break;
            }
            return result;
        }
    }
    static class AllComparator implements Comparator<Person>{
        @Override
        public int compare(Person o1, Person o2) {
            return o1.compareTo(o2);
        }
    }
}
