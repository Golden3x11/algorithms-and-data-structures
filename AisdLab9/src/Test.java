import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws IOException {
        /*Map<String,String[]> dictionary=
        Files.readAllLines(Paths.get("odmiany.txt"), Charset.defaultCharset())
                .stream()
                .collect(Collectors.toMap(
                        (s) -> s.split(",")[0].trim(),
                        (s) -> Arrays.stream(s.split(",")).skip(1).map(String::trim).toArray(String[]::new),
                                (oldValue, newValue) -> oldValue)
                );
        RBTree<String,String[]> tree=new RBTree<>((String s1, String s2)->s1.compareTo(s2));
        Iterator<Map.Entry<String,String[]>> iterator=dictionary.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String[]> entry=iterator.next();
            tree.insert(entry.getKey(),entry.getValue());
        }
        System.out.println(tree.searchArray("Majchrzycki"));

         */
        RBTree<Integer,Integer> rbTree=new RBTree<>((Integer s1, Integer s2)->s1.compareTo(s2));
        for(int i=1;i<21;i++){
            rbTree.insert(i,i);
        }
        System.out.println(rbTree.search(30));
        rbTree.printTree();
        System.out.println(rbTree.percentOfBlackLeaves());

    }
}
