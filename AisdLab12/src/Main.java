import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,3},4));
        System.out.println(greedy(new int[]{1,4,7,2},2));
    }
    public static String coinChange(int[] denominations,int rest){
        int[] solution=new int[rest+1];
        solution[0]=0;
        for(int i=0;i<denominations.length;i++){
            solution[denominations[i]]+=1;
            for(int j=denominations[i]+1;j<=rest;j++){
                    solution[j] += solution[j - denominations[i]];
            }
        }
        return String.format("Banknot %d możemy rozdzielić na %d sposoby",rest,solution[rest]);
    }
    public static String greedy(int[] T,int l){
        int s_index=0;
        int r=Integer.MAX_VALUE;
        Arrays.sort(T);

        for(int i=0;i<T.length-l+1;i++){
            if(T[i+l-1]-T[i]<r) {
                r = T[i + l - 1] - T[i];
                s_index=i;
            }
        }

        int[] result=new int[l];
        for(int i=0;i<result.length;i++){
            result[i]=T[s_index++];
        }
        return "Najmiejsza różnica to "+r;
    }
}
