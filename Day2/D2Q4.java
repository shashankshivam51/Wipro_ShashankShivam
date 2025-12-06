package Day2;
import java.util.Scanner;
public class D2Q4 {
    public static void main(String[] args) {

        //write your answer here
        Scanner sc= new Scanner(System.in);
        String s=sc.nextLine();
        int [] freq = new int [26];
        for(char c: s.toCharArray()){
            freq[c - 'a']++;
        }

        int maxFreq = 0;
        for(int f:freq){
            if(f > maxFreq){
                maxFreq = f;
            }
        }
        int minMoves = s.length() - maxFreq;
        System.out.println(minMoves);

    }
}
