package Day4;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
class D4Q1
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        double length=sc.nextDouble();
        double width=sc.nextDouble();
        Rectangle rectangle1 = new Rectangle(length,width);
        double area1 = rectangle1.calculateArea();
        double perimeter1 = rectangle1.calculatePerimeter();

        System.out.println("Rectangle 1: ");
        System.out.println("Length: " + rectangle1.getLength());
        System.out.println("Width: " + rectangle1.getWidth());
        System.out.println("Area: " + area1);
        System.out.println("Perimeter: " + perimeter1);
    }
}

class Rectangle {
    // your solution
    private double length;
    private double width;

    Rectangle(double length, double width){
        this.length = length;
        this.width = width;
    }

    double calculateArea(){
        return length*width;
    }

    double calculatePerimeter(){
        return 2*(length + width);
    }


    public double getLength(){
        return length;
    }

    public double getWidth(){
        return width;
    }

}