package Day3;

import java.util.Scanner;

public class D3Q2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        int sum = sumOfDigits(num);
        System.out.println("Sum of digits: " + sum);

        sc.close();
    }

    static int sumOfDigits(int n) {
        if (n == 0)
            return 0;
        return (n % 10) + sumOfDigits(n / 10);
    }
}
