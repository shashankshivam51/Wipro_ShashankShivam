package Day5;


import java.util.InputMismatchException;
import java.util.Scanner;

class D5Q1 {
    public static void main(String args[] ) throws Exception {

        // Write your code here
        Scanner sc = new Scanner(System.in);
        boolean sucess = false;
        while(!sucess){
            try{
                int num1 = sc.nextInt();
                int num2;
                while(true){
                    num2 = sc.nextInt();
                    if(num2 == 0){
                        System.out.println("Cannot divide by zero. Please enter a valid divisor.");

                    }else{
                        break;
                    }
                }

                int result = num1 / num2;
                System.out.println("Result: " + result);
                sucess = true;
            }catch(InputMismatchException e){
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }

        sc.close();

    }
}