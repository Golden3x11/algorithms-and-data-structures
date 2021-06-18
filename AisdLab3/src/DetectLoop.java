import java.util.Iterator;
import java.util.LinkedList;

public class DetectLoop {
    public static boolean hasCycle(IList list){
        Iterator fast=list.iterator();
        Iterator slow=list.iterator();
        while(fast.hasNext()){
            fast.next();
            if(fast.hasNext()){
                if(fast.next().equals(slow.next()))
                    return true;
            }
        }
        return false;
    }
}
