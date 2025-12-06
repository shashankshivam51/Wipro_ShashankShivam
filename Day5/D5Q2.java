package Day5;

import java.util.Scanner;
public class D5Q2 {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        //write your answer here

        try{
            int n = scn.nextInt();
            try{
                if(99 % n == 0){
                    System.out.println(n + "is a factor of 99");
                }else{
                    System.out.println(n + " is a not a factor of 99");
                }
            }catch(ArithmeticException e){
                System.out.println("Arithmetic Exception java.lang.ArithmeticException: / by zero");
            }
        }catch(Exception e){
            System.out.println("Number Format Exception java.lang.NumberFormatException: For input string: \"" + scn.next() + "\"");
        }

        scn.close();


    }
}
