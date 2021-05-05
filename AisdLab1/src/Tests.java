import java.util.ArrayList;

public class Tests {
    public static void main(String[] args) {
        Integer [] array={0,1,2};
        try{
            ArrayIterator iterator=new ArrayIterator(array);
            while(!iterator.isDone()) {
                System.out.print(iterator.isDone()+",");
                System.out.print(iterator.current()+",");
                iterator.nextOwn();
                System.out.println(iterator.isDone());
            }
            System.out.println();
            iterator.last();
            while(!iterator.isDone()) {
                System.out.print(iterator.isDone()+",");
                System.out.print(iterator.current()+",");
                iterator.previous();
                System.out.println(iterator.isDone());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
