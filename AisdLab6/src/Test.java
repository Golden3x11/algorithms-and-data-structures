import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Test {
    static int size=10;
    static Student[] array1=new Student[size];
    static Student[] array2=new Student[size];
    static Student[] array3=new Student[size];
    static Student[] array4=new Student[size];

    static Comparator comparator=new SortMethods.AgeComparator();

    public static void main(String[] args) {
        System.out.println("Quick");
        pessimistic(array2);
        SortMethods.mergeSort(array2,comparator);
        for(Student student:array2)
            System.out.println(student.getAge());

    }
    public static void insert(Student[] array) {
        long start = System.currentTimeMillis();
        SortMethods.insertSort(array, comparator);
        long stop = System.currentTimeMillis();
        System.out.println((stop - start));
    }
    public static void select(Student[] array){
        long start=System.currentTimeMillis();
        SortMethods.selectSort(array,comparator);
        long stop=System.currentTimeMillis();
        System.out.println((stop-start));
    }
    public static void shell(Student[] array){
        long start=System.currentTimeMillis();
        SortMethods.shellSort(array,comparator);
        long stop=System.currentTimeMillis();
        System.out.println((stop-start));
    }
    public static void merge(Student[] array){
        long start=System.currentTimeMillis();
        SortMethods.mergeSort(array,comparator);
        long stop=System.currentTimeMillis();
        System.out.println((stop-start));
    }
    public static void quick(Student[] array){
        long start=System.currentTimeMillis();
        SortMethods.quickSort(array,comparator);
        long stop=System.currentTimeMillis();
        System.out.println((stop-start));
    }
    public static void heap(Student[] array){
        long start=System.currentTimeMillis();
        SortMethods.heapSort(array,comparator);
        long stop=System.currentTimeMillis();
        System.out.println((stop-start));
    }
    public static void optimistic(Student[] array){
        for(int i=0;i<size;i++)
            array[i]=new Student("A","23",i);
        array[0]= new Student("A","we",2);
    }
    public static void pessimistic(Student[] array){
        for(int i=0;i<size;i++)
            array[i]=new Student("A","23",size-i);
    }
    public static void random(Student[] array){
        Random rnd=new Random();
        for(int i=0;i<size;i++)
            array[i]=new Student("A","23",rnd.nextInt(size));
    }
    public static void randomGauss(Student[] array){
        Random rnd=new Random();
        for(int i=0;i<size;i++)
            array[i]=new Student("A","23", rnd.nextGaussian());
    }

}
