package Onp;

import Queue.IQueue;

public class TestOnp {
    public static void writeEquation(String[] array){
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]);
        }
    }
    public static void main(String[] args) {
        String[] array={"2","+","3"};
        //String[] array={"(","2","+","1",")","*","3"};
        //String[] array={"2","*","3","+","3","*","1"};
        //String[] array={"(","2","+","1",")","+","1","*","(","3","-","1",")"};
        IQueue queue= Onp.changeToONP(array);
        System.out.print("Odwrotna Notacja Polska: ");
        Onp.writeOnp(queue);

        System.out.println();
        System.out.print("Wynik: ");
        writeEquation(array);
        System.out.println("="+Onp.calculate(queue));

    }
}
