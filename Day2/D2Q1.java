package Day2;
import java.util.Scanner;

public class D2Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // TODO: Write your code here
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        int [] [] arr = new int [row] [col];

        //Taking input
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                arr[i][j]=scanner.nextInt();
            }
        }

        //Output Code
        for(int i=0; i<row;i++){
            for(int j=0; j<col;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }




        scanner.close();
    }
}
