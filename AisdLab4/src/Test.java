import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Integer[] array={9,4,5,6,7,3,2};
        InsertSort<Integer> listSorter=new InsertSort<>(new ValueComparator<Integer>());
        for(Object integer:array)
            System.out.println(integer);
        Integer[] arrayC=array.clone();
        System.out.println("Mniejsza złożność");
        System.out.println("Liczba swapów: "+numberOfSwaps(array));
        System.out.println(mergeInsertionSwap(array,0, array.length-1));
        array=arrayC;
        System.out.println("Większa złożność");
        listSorter.sort(array);
        System.out.println("----------------");
        for(Object integer:array)
            System.out.println(integer);

    }
    public static int numberOfSwaps(Integer[] array){
        ValueComparator<Integer> comparator=new ValueComparator();
        int biggerElementOnLeft=0;

        for(int i=1;i< array.length;i++) {
            int value = array[i];
            for(int j=i; j>0;j--)
                if(comparator.compare(array[j-1],value)>0)
                    biggerElementOnLeft++;
        }
        return biggerElementOnLeft;
    }

    static int mergeInsertionSwap(Integer array[], int left, int right)
    {
        int swaps = 0;
        if (left < right)
        {
            int mid =(right + left) / 2;

            swaps += mergeInsertionSwap(array, left, mid);
            swaps += mergeInsertionSwap(array, mid + 1, right);

            swaps += merge(array, left, mid + 1, right);
        }
        return swaps;
    }

    static int temp[] = new int[100000];

    static int merge(Integer array[], int left, int mid, int right)
    {

        int swaps = 0;
        int i = left, j = mid, k = left;
        while (i < mid && j <= right)
        {
            if (array[i] <= array[j])
            {
                temp[k] = array[i];
                k++;
                i++;
            }
            else
            {
                temp[k] = array[j];
                k++;
                j++;
                swaps += mid - i;
            }
        }
        while (i < mid)
        {
            temp[k] = array[i];
            k++;
            i++;
        }

        while (j <= right)
        {
            temp[k] = array[j];
            k++;
            j++;
        }

        while (left <= right)
        {
            array[left] = temp[left];
            left++;
        }
        return swaps;
    }

    public static class ValueComparator<T extends Comparable<? super T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
}
