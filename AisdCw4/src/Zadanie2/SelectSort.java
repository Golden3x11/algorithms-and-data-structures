package Zadanie2;

import java.util.Comparator;

public class SelectSort<T> {
    private final Comparator<T> _comparator;
    public SelectSort(Comparator<T> comparator) {
        _comparator = comparator;
    }
    public T[] sort(T[] array) {
        int size = array.length;
        for (int slot = 0; slot < size - 1; ++slot) {
            int smallest = slot; // pozycja wartości minimalnej
            for (int check = slot + 1; check < size; ++check)
                if (_comparator.compare(array[check], array[smallest]) < 0)
                    smallest = check;
            swap(array, smallest, slot);
        }
        return array;
    }
    private void swap(T[] array, int left, int right) {
        if (left != right) {
            T temp = array[left];
            array[left]=array[right];
            array[right]=temp;
        }
    }

}
