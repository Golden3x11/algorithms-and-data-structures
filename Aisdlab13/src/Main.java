import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        //System.out.println(flowers(new int[]{5,1,1,3,3,3}));
        System.out.println(zad2(new char[]{'c','a','b'}));
    }
    public static int flowers(int[] flowers){
        Stack<Integer> stack1=new Stack<>();
        Stack<Integer> stack2=new Stack<>();

        for(int flower:flowers)
            stack1.push(flower);
        int i=0;
        int size2=0,size1=stack1.size();
        while(size1!=size2){
            size1=stack1.size();
            while(!stack1.isEmpty()){
                int x=stack1.pop();
                if(stack1.isEmpty() || stack1.peek()>=x)
                    stack2.push(x);
            }
            size2=stack2.size();
            if(size1!=size2)
                i++;
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }
        return i;
    }
    static int[][][] result;
    static int[] count = new int[3];

    static int length(int a, int b, int c)
    {

        if(a < 0 || b < 0 || c < 0)

            if (result[a][b][c] != -1)
                return result[a][b][c];

        if (a == 0 && b == 0)
            return (result[a][b][c] = c);
        if (a == 0 && c == 0)
            return (result[a][b][c] = b);
        if (b == 0 && c == 0)
            return (result[a][b][c] = a);

        if (a == 0 && count[0]!=0)
            return (result[a][b][c] = length(a + 1, b - 1, c - 1));
        if (b == 0 && count[1]!=0)
            return (result[a][b][c] = length(a - 1, b + 1, c - 1));
        if (c == 0 && count[2]!=0)
            return (result[a][b][c] = length(a - 1, b - 1, c + 1));
        if(a==0 ||b==0||c==0){
            return a+b+c;
        }

        result[a][b][c] =
                Math.min(length(a - 1, b - 1, c + 1),
                        Math.min(length(a - 1, b + 1, c - 1),
                                length(a + 1, b - 1, c - 1)));

        return result[a][b][c];
    }

    static int zad2(char[] array)
    {
        int n = array.length;

        count = new int[3];
        for (char c : array)
            count[c - 'a']++;
        int x =Math.max(count[0],Math.max(count[1],count[2] ));
        result=new int[100][100][100];

        for (int i = 0; i < count[0]; ++i)
            for (int j = 0; j < count[1]; ++j)
                for (int k = 0; k < count[2]; ++k)
                    result[i][j][k] = -1;

        return length(count[0], count[1], count[2]);
    }
    static int zad22(String str) {
        int n = str.length();
        count= new int[3];
        for (int i = 0; i < n; ++i) {
            count[str.charAt(i) - 'a']++;
        }
        if (count[0] == n || count[1] == n
                || count[2] == n) {
            return n;
        }
        if(count[0]==0||count[1]==0||count[2]==0)
            return n;

        if ((count[0] % 2) == (count[1] % 2)
                && (count[1] % 2) == (count[2] % 2)) {
            return 2;
        }

        return 1;
    }

}
