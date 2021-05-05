package Onp;

import Queue.EmptyQueueException;
import Queue.IQueue;
import Queue.UnlimitedQueue;
import Stack.EmptyStackException;
import Stack.IStack;
import Stack.UnlimitedStack;

import java.io.IOException;

public class Onp {
    private static boolean isNumeric(String str){
        try {
            Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    public static IQueue<Object> changeToONP(String [] array){
        IQueue<Object> queue=new UnlimitedQueue<>(10);
        IStack<Object> stack=new UnlimitedStack<>(10);
        try {
            for (int i = 0; i < array.length; i++) {
                if (isNumeric(array[i]))
                    queue.enqueue(new Double(array[i]));
                else if (array[i] == "(")
                    stack.push(array[i]);
                else if (array[i] == ")") {
                    Object elem;
                    while (!stack.isEmpty()){
                        elem=stack.pop();
                        if(elem!="(")
                            queue.enqueue(elem);
                    }
                }else{//Operator
                    Operator op1=new Operator(array[i]);
                    while((!stack.isEmpty())&&(stack.top()instanceof Operator)&&
                            ((Operator)stack.top()).getPriority()>=op1.getPriority()){
                        queue.enqueue(stack.pop());
                    }
                    stack.push(op1);
                }
            }
            while (!stack.isEmpty())
                queue.enqueue(stack.pop());
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
        return queue;
    }
    public static void writeOnp(IQueue<Object> queue){
        try {
            IQueue<Object> copyQueue = new UnlimitedQueue<>(3);
            while (!queue.isEmpty()) {
                Object elem;
                elem = queue.dequeue();
                System.out.print(elem.toString() + ",");
                copyQueue.enqueue(elem);
            }
            while (!copyQueue.isEmpty())
                queue.enqueue(copyQueue.dequeue());
        }catch (EmptyQueueException e){
            e.printStackTrace();
        }
    }
    public static double calculate(IQueue<Object> queue){
        IStack<Double> stack = new UnlimitedStack<>(3);
        try {
            while (!queue.isEmpty()) {
                if (queue.first() instanceof Operator){
                    stack.push(easyEquation((Operator) queue.dequeue(),
                                            stack.pop(),
                                            stack.pop()));
                }
                else{
                    stack.push((Double) queue.dequeue());
                }
            }
            return stack.pop();
        }catch(EmptyQueueException | EmptyStackException e){
            e.printStackTrace();
        }
        return 0;
    }
    public static double easyEquation(Operator operator,double elem1,double elem2){
        if(operator.toString().equals("*"))
            return elem2*elem1;
        else if(operator.toString().equals("/")) {
            try {
                if(elem1==0)
                    throw new ArithmeticException();
                return elem2 / elem1;
            }catch (ArithmeticException e){
                e.printStackTrace();
            }
        }
        else if(operator.toString().equals("+"))
            return elem2+elem1;
        else if(operator.toString().equals("-"))
            return elem2-elem1;
        return 0;
    }
}
