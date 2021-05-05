import java.util.Comparator;
import java.util.Random;

public class SortMethods<T> {
    private static Random rnd=new Random();

    public static<T> void insertSort(T[] array, Comparator<T> comparator) {
        int n= array.length;
        for (int i = 1; i < n; i++) {
            T value = array[i],temp;
            int j;
            for (j = i; j > 0 && comparator.compare(value, temp=array[j-1])< 0; --j)
                array[j] = temp;

            array[j]=value;
        }
    }
    public static<T> void selectSort(T[] array,Comparator<T> comparator){
        int n = array.length;
        for (int i = 0; i < n-1; i++){
            int min_idx = i;
            for (int j = i+1; j < n ; j++) {
                if (comparator.compare(array[j], array[min_idx]) < 0)
                    min_idx = j;
            }

            T temp = array[min_idx];
            array[min_idx] = array[i];
            array[i] = temp;

        }
    }
    public static<T> void shellSort(T[] array,Comparator<T> comparator){
        int n= array.length;
        for(int gap=n/2;gap>0;gap/=2){
            for(int i=gap; i<n;i++){
                T value=array[i],temp;
                int j;
                for(j=i;j>=gap && comparator.compare(value, temp=array[j-gap])< 0;j-=gap)
                    array[j]=array[j-gap];

                array[j]=value;
            }
        }
    }
    public static<T> void mergeSort(T[] array,Comparator<T> comparator){
        divide(array,0,array.length-1,comparator);
    }

    private static<T> void divide(T[] array, int startIndex, int endIndex,Comparator<T> comparator){
        if(startIndex<endIndex){
            int mid=startIndex+(endIndex-startIndex)/2;
            divide(array,startIndex,mid,comparator);
            divide(array,mid+1,endIndex,comparator);
            merge(array,startIndex,mid,endIndex,comparator);
        }
    }
    @SuppressWarnings("unchecked")
    private static<T> void merge(T[] array, int start,int mid ,int end,Comparator<T> comparator){
        T[] result=(T[])(new Object[end-start+1]);
        int elemL=start,elemR=mid+1,i=0;

        while(elemL<=mid &&elemR<=end){
            if(comparator.compare(array[elemL],array[elemR])<=0)
                result[i++]=array[elemL++];
            else
                result[i++]=array[elemR++];
        }
        while(elemL<=mid)
            result[i++]=array[elemL++];
        while (elemR<=end)
            result[i++]=array[elemR++];
        for(i=start;i<=end;i++)
            array[i]=result[i-start];
    }
    public static<T> void quickSort(T[] array,Comparator<T> comparator){
        quickS(array,0, array.length-1,comparator);
    }
    private static<T> void quickS(T[] array, int startIndex, int endIndex,Comparator<T> comparator){
        if (startIndex<endIndex){
            int partition=partition1(array,startIndex,endIndex,comparator);
            quickS(array,startIndex,partition-1,comparator);
            quickS(array,partition+1,endIndex,comparator);
        }
    }
    private static<T> int partition1(T[] array, int start,int end,Comparator<T> comparator){
        int random=start+rnd.nextInt(end-start+1);
        swap(array,start,random);
        int idxBigger=start+1,idxLower=end;
        T value=array[start];
        do{
            while (idxBigger<=idxLower && comparator.compare(array[idxBigger],value)<=0)
                idxBigger++;
            while (comparator.compare(array[idxLower],value)>0)
                idxLower--;
            if(idxBigger<idxLower)
                swap(array,idxBigger,idxLower);
        }while (idxBigger<idxLower);
        swap(array,idxLower,start);
        return idxLower;
    }
    private static<T> int partition(T[] array, int start,int end,Comparator<T> comparator){
        T value=array[end];
        int i=start-1;
        for(int j=start;j<end;j++){
            if(comparator.compare(array[j],value)<=0) {
                i++;
                swap(array,i,j);
            }
        }
        swap(array,++i,end);
        return i;
    }
    private static<T> void swap(T[] array, int left, int right)
    {
        T temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
    private static<T> void sink(T[] array,int idx,int n,Comparator<T> comparator){
        int idxBigger=2*idx+1;
        if(idxBigger<n){
            if(idxBigger+1<n && comparator.compare(array[idxBigger],array[idxBigger+1])<0)
                idxBigger++;
            if(comparator.compare(array[idx],array[idxBigger])<0){
                swap(array,idx,idxBigger);
                sink(array,idxBigger,n,comparator);
            }
        }
    }
    private static<T> void createHeap(T[] array,int n,Comparator<T> comparator){
        for(int i=(n-1)/2;i>=0;i--)
            sink(array, i, n,comparator);
    }
    public static<T> void heapSort(T[] array,Comparator<T> comparator){
        heapS(array, array.length,comparator);
    }
    private static<T> void heapS(T[] array,int n,Comparator<T> comparator){
        createHeap(array,n,comparator);
        for(int i=n-1;i>0;i--){
            swap(array,i,0);
            sink(array,0,i,comparator);
        }
    }

    public static class BasicComparator<T extends Comparable<? super T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }
    public static class AgeComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return Double.compare(o1.getAge(), o2.getAge());
        }
    }
    public static class IndexComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.getIndex().compareTo(o1.getIndex());
        }
    }
    public static class SurnameComparator implements Comparator<Student> {

        @Override
        public int compare(Student o1, Student o2) {
            return o1.getSurname().compareTo(o2.getSurname());
        }
    }
}
