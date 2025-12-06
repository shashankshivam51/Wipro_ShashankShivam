package Day2;

import java.util.Scanner;

public class D2Q2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int[] arr = new int[size];
        // read the array elements
        for (int i = 0; i < size; i++) {
            arr[i] = input.nextInt();
        }

        sumArray(arr,size);

    }

    public static void sumArray(int[] arr,int size)
    {
        //write logic here and display sum
        int sum=0;
        for(int i=0;i<size;i++){
            sum += arr[i];

        }
        System.out.print("the sum of the elements in the array = " + sum);

    }
}
