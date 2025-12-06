package Day6;

import java.util.*;
import java.util.Scanner;
// Java program to remove
// elements from HashMap

public class D6Q4 {

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        //write your answer here

        String [] arr = s.nextLine().split("\\s+");
        HashMap<Integer, String> map = new HashMap<>();


        for(int i=1;i<=4;i++){
            map.put(i,arr[i-1]);
        }

        int keyToRemove = Integer.parseInt(arr[4]);
        System.out.println("Mappings of HashMap are : " + map);
        map.remove(keyToRemove);
        System.out.println("Mappings after removal are : " + map);
    }
}
