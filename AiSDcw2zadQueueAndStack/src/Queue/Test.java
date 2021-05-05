package Queue;

public class Test {
    public static void main(String[] args)  {
        try {
            //QueueClassic qc = new QueueClassic(3);
            QueueCyclicalWithCounter qc=new QueueCyclicalWithCounter(3);
            qc.enqueue(1);System.out.println(qc.dequeue());
            qc.enqueue(1);System.out.println(qc.dequeue());
            qc.enqueue(1);System.out.println(qc.dequeue());
            qc.enqueue(1);System.out.println(qc.dequeue());
            System.out.println(qc.size());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
