package Day3;

public class D3Q3 {
    public static void main(String [] args){
        System.out.println("Book 1:");
        Book b1 = new Book();
        b1.displayInfo();


        System.out.println();


        System.out.println("Book 2:");
        Book b2 = new Book("Java Programming" , "Sakshi",2024);
        b2.displayInfo();
    }

}

class Book{
    String title;
    String author;
    int year;

    public Book(){
        title="Not specified";
        author="Unknown";
        year=0;
    }

    //para
    public Book(String title, String author, int year){
        this.title =title;
        this.author =author;
        this.year = year;
    }


    void displayInfo(){
        System.out.println("Title: " +title);
        System.out.println("Author: " +author);
        System.out.println("Year: " + year);
    }



}
