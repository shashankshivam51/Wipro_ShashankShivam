package Day3;
import java.util.Scanner;
public class D3Q4 {

    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        Constructor cuboid1 = new Constructor();
        int l = s.nextInt();
        int b = s.nextInt();
        int h = s.nextInt();

        Constructor cuboid2 = new Constructor(l,b,h);


    }
}

class Constructor{
    int length, breath, height;

    Constructor(){
        length = 10;
        breath = 10;
        height = 10;
        System.out.println("Constructor without parameter");
        System.out.println("Volume is " + (length * breath * height) + ".0");
    }

    Constructor(int l, int b, int h){
        length =l;
        breath = b;
        height = h;
        System.out.println("Constructor with parameter");
        System.out.println("Volume is " + (length * breath* height)+".0");

    }

}