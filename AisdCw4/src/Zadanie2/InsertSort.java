package Zadanie2;

import java.util.Comparator;

public class InsertSort<T> {
    private final Comparator<T> _comparator;
    public InsertSort(Comparator<T> comparator)
    {
        _comparator = comparator;
    }
    public T[] sort(T[] array) {
        for (int i = 1; i < array.length; ++i) {
            T value = array[i],temp;
            int j;
            for (j = i; j > 0&& _comparator.compare(value, temp=array[j-1])< 0; --j)
                array[j]=temp;
            array[j]=value;
        }
        return array;
    }

}
