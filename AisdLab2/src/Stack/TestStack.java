package Stack;

public class TestStack {
    public static void main(String[] args) throws EmptyStackException {
        try {
            UnlimitedStack us = new UnlimitedStack(2);
            us.push(1);
            us.push(2);
            us.push(3);
            us.push(4);
            us.push(5);
            us.push(6);
            us.push(7);
            System.out.println(us.pop());
            System.out.println(us.pop());
            System.out.println(us.pop());
            System.out.println(us.pop());
            System.out.println(us.pop());
            System.out.println(us.pop());
            System.out.println(us.pop());
            System.out.println(us.pop());
        }catch (EmptyStackException e){
            e.printStackTrace();
        }

    }
}
