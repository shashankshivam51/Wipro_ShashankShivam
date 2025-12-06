package Day8;
import java.util.*;
public class D8Q1 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int x = s.nextInt();

        int y = s.nextInt();
        s.close();

        A1 a = new A1(x,y);
        Thread t  = new Thread(a);
        t.start();
        try{
            t.join();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }


    }
    // complete the class below and create the thread to achive desired output

}

class A1 implements Runnable{
    int i, j;

    //result
    private int sum, diff, prod, ratio;
    private double power;

    A1(int x, int y){
        i =x;
        j=y;
    }

    @Override
    public void run(){
        Thread tSum = new Thread(() -> sum = i+j);
        Thread tDiff = new Thread(() -> diff = i-j);
        Thread tProd = new Thread(() -> prod = i*j);
        Thread tRatio = new Thread(() -> ratio = (j != 0) ? (i/j) : 0);
        Thread tPower = new Thread (() -> power = Math.pow(i,j));

        tSum.start();
        tDiff.start();
        tProd.start();
        tRatio.start();
        tPower.start();

        try{
            tSum.join();
            tDiff.join();
            tProd.join();
            tRatio.join();
            tPower.join();
        } catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        System.out.println("ARITHEMATIC OPERATIONS");
        System.out.println("SUM " + sum);
        System.out.println("DIFFERENCE " + diff);
        System.out.println(" PRODUCT  " + prod);
        System.out.println("RATIO  " + ratio);
        System.out.println("POWER  " + power);
        System.out.println("END OF A");

    }
}