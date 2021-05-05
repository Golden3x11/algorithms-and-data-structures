import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        BST<String> bst=new BST<>((String s1, String s2)->s1.compareTo(s2));
        String line;
        ArrayList<String> arraylist=new ArrayList<>();
        String[] words;
        try (BufferedReader br = new BufferedReader(new FileReader("krzyzacy.txt"))) {
            while ((line = br.readLine())!= null){
                if(!line.equals("")) {
                    words = line.toLowerCase().split("[, ?.@!:();„”—]");
                    for (String word: words) {
                       if(!word.equals("")){
                           bst.insert(word);
                           arraylist.add(word);
                       }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        words= arraylist.toArray(new String[0]);

        String test="fundacja";
        long time=System.nanoTime();
        System.out.println(bst.search(test));
        time=System.nanoTime()-time;
        System.out.println("BST "+time);

        time=System.nanoTime();
        for(String word: words){
            if(word.equals(test)) {
                System.out.println(word);
                break;
            }
        }
        time=System.nanoTime()-time;
        System.out.println("String[] "+time);
    }

}
