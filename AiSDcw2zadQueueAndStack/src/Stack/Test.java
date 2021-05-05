package Stack;

public class Test {
    public static void main(String[] args) {
        try {
            IStackDrowning sl = new IStackDrowning(3);
            sl.push(1);
            System.out.println("Rozmiar "+sl.size());
            sl.push(2);
            sl.push(3);
            System.out.println("Top "+sl.top());
            sl.push(4);
            System.out.println("Top "+sl.top());
            System.out.println("Rozmiar "+sl.size());

            System.out.println(sl.isFull());
            System.out.println(sl.pop());
            System.out.println(sl.pop());
            System.out.println(sl.pop());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
