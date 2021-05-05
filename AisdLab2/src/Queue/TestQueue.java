package Queue;

import java.util.NoSuchElementException;

public class TestQueue {
    public static void main(String[] args) {
        try{
            UnlimitedQueue uq=new UnlimitedQueue(2);
            uq.enqueue(1);
            uq.enqueue(2);
            uq.enqueue(3);
            uq.enqueue(4);
            uq.enqueue(5);
            uq.enqueue(6);
            uq.enqueue(7);
            while(!uq.isEmpty()){
                System.out.println(uq.dequeue());
            }
        }catch(EmptyQueueException e){
            e.printStackTrace();
        }
    }
}
