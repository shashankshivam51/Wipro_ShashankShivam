package Day6;
import java.util.Scanner;
import java.util.*;

public class D6Q1 {

    public static void main(String[] args) {

        //write your answer here

        Scanner sc = new Scanner(System.in);
        ArrayList <Integer>list = new ArrayList<>();
        while(sc.hasNextInt())
        {
            list.add(sc.nextInt());
        }
        System.out.println("List before sort: " + list);
        Collections.sort(list);
        System.out.println("List after sort: "+ list);
    }
}
