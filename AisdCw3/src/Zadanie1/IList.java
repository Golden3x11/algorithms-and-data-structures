package Zadanie1;

public interface IList<T> {
    void insert(int index, T value);
    T get(int index);
    int size();
    void clear();
    Element delete(int index);
    Element delete(T value);
    T set(int index,T value);
    int indexOf(T value);
    boolean contains(T value);
    void wyswietlListe();
}
