package Day1;

import java.util.Scanner;

public class D1Q4 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();sc.close();
        if(n%2==0){
            System.out.println("No is Even");
        }else{
            System.out.println("No is odd");
        }

    }
}
