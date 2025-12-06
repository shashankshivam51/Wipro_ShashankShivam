package Day1;

import java.util.Scanner;

public class D1Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // TODO: Write your code here
         System.out.println("Enter the first number: ");
        int x = sc.nextInt();
         System.out.println("Enter the second number: ");
        int y = sc.nextInt();
        int sum = x+y;
        int diff = x-y;
        int quo = x/y;
        int rem = x%y;
        int pro = x*y;

        System.out.println("Sum: " + sum);
        System.out.println("Difference: " + diff);
        System.out.println("Product: " + pro);
        System.out.println("Quotient: " + quo);
        System.out.println("Remainder: " + rem);


        sc.close();
    }
}
