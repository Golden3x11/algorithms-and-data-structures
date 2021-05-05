import Iterators.Predicate;

public class PredicateZewn implements Predicate{


    @Override
    public boolean accept(int arg) {
        return Main.isPrimeNumber(arg);
    }
}
