package Day1;

import java.util.Scanner;

public class D1Q2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean a = scanner.nextBoolean();
        boolean b = scanner.nextBoolean();

        // TODO: Write your code here

        boolean result = a && b;
        boolean resultt = a || b;
        System.out.println(result);
        System.out.println(resultt);
    }
}
