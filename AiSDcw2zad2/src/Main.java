import Iterators.FilterIterator;
import Iterators.NumbersIterator;
import Iterators.Predicate;

public class Main {
    public static void main(String[] args) {
        NumbersIterator filterIterator=new NumbersIterator(1,10);
        while (filterIterator.hasNext()) {
            System.out.print(filterIterator.next()+",");
        }
        //filterIteratorAnonim(1,100);
        //System.out.println();
        //System.out.println();
        //filterIteratorZewn(1,10);
    }
    public static boolean isPrimeNumber(int liczba)
    {
        if(liczba<2)
        {
            return false;
        }
        for(int i=2; i<=liczba/2; i++)
        {
            if(liczba%i==0)
            {
                return false;
            }
        }
        return true;
    }
    public static void filterIteratorAnonim(int start,int end){
        NumbersIterator iterator=new NumbersIterator(start,end);
        FilterIterator filterIterator=new FilterIterator(iterator, new Predicate() {
            @Override
            public boolean accept(int arg) {
                return isPrimeNumber(arg);
            }
        });
        while (filterIterator.hasNext()) {
            System.out.print(filterIterator.next()+",");
        }
    }
    public static void filterIteratorZewn(int start,int end){
        NumbersIterator iterator=new NumbersIterator(start,end);
        
        FilterIterator filterIterator=new FilterIterator(iterator,new PredicateZewn());
        while (filterIterator.hasNext()) {
            System.out.print(filterIterator.next()+",");
        }
    }
}
