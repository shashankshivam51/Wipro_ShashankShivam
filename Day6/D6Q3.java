package Day6;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class D6Q3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ch = scanner.nextInt();
        if(ch==1)
        {
            Library<Book> bookLibrary = new Library<>();
            int n = scanner.nextInt();
            for(int i=1;i<=n ;i++ )
            {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int numpages = scanner.nextInt();
                Book book = new Book(id,name,author,numpages);
                bookLibrary.addItem(book);
            }
            bookLibrary.displayItems();
        }

        else if(ch==2)
        {
            Library<DVD> dvdLibrary = new Library<>();
            int n = scanner.nextInt();
            for(int i=1;i<=n ;i++ )
            {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int duration = scanner.nextInt();
                DVD dvd = new DVD(id,name,author,duration);
                dvdLibrary.addItem(dvd);
            }

            Library<Magazine> magazineLibrary = new Library<>();

            n = scanner.nextInt();
            for(int i=1;i<=n ;i++ )
            {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int issueno = scanner.nextInt();
                Magazine magazine = new Magazine(id,name,author,issueno);
                magazineLibrary.addItem(magazine);
            }
            dvdLibrary.displayItems();
            magazineLibrary.displayItems();
        }

        else if(ch==3) {
            Library<Book> bookLibrary = new Library<>();
            int n = scanner.nextInt();
            for(int i=1;i<=n ;i++ )
            {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int numpages = scanner.nextInt();
                Book book = new Book(id,name,author,numpages);
                bookLibrary.addItem(book);
            }


            // Checking out and returning items
            int itemId = scanner.nextInt();

            if( bookLibrary.checkOutItem(itemId))
            {
                System.out.println("Item " + itemId + " checked out successfully.");
            }
            else
            {
                System.out.println("Item " + itemId + " not found in the library.");
            }


        }

        else if(ch==4)
        {

            Library<Magazine> magazineLibrary = new Library<>();

            int n = scanner.nextInt();
            for(int i=1;i<=n ;i++ )
            {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int issueno = scanner.nextInt();
                Magazine magazine = new Magazine(id,name,author,issueno);
                magazine.checkedOut= true;
                magazineLibrary.addItem(magazine);
            }

            int itemId = scanner.nextInt();


            if(magazineLibrary.returnItem(itemId))
            {
                System.out.println("Item " + itemId + " returned successfully.");
            }
            else
            {
                System.out.println("Item " + itemId + " not found in the library.");
            }

        }

    }
}

class LibraryItem {
    private int itemId;
    private String title;
    private String creator;
    public boolean checkedOut;


    public LibraryItem(int itemId, String title, String creator) {
        //write your code here
        this.itemId = itemId;
        this.title = title;
        this.creator = creator;
    }

    // define getter methods for itemId, title and creator

    public int getItem(){
        return itemId;
    }

    public String getTitle(){
        return title;
    }

    public String getCreator(){
        return creator;
    }


    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void checkedOut() {
        //implement code and throw an exception if item already checked out
        if(checkedOut){
            throw new ItemAlreadyCheckedOutException(itemId);
        }
        checkedOut = true;
    }

    public void returnItem() {
        //implement  code and also throw an exception if item is not checked out
        if(!checkedOut){
            throw new ItemNotAlreadyCheckedOutException(itemId);
        }
        checkedOut = false;

    }

    @Override
    public String toString() {
        return "Item ID: " + itemId + "\nTitle: " + title + "\nCreator: " + creator + "\nChecked Out: " + checkedOut;
    }
}

// create and define custom classes of exceptions

class ItemAlreadyCheckedOutException extends RuntimeException{
    public ItemAlreadyCheckedOutException(int itemId){
        super("Item " + itemId + " is already checked out");
    }
}

class ItemNotAlreadyCheckedOutException extends RuntimeException{
    public ItemNotAlreadyCheckedOutException(int itemId){
        super("Item "+ itemId +" is not checked out");
    }
}



class Book extends LibraryItem {
    private int numPages;

    // define parameterized constructor with 4 arguments  : int itemId, String title, String author, int numPages
    public Book(int itemId, String title, String author, int numPages){
        super(itemId,title, author);
        this.numPages = numPages;
    }


    @Override
    public String toString() {
        return super.toString() + "\nType: Book\nNumber of Pages: " + numPages;
    }
}

class DVD extends LibraryItem {
    private int duration; // in minutes


    // define parameterized constructor with 4 arguments int itemId, String title, String director, int duration
    public DVD(int itemId, String title, String director, int duration){
        super(itemId, title, director);
        this.duration = duration;
    }


    @Override
    public String toString() {
        return super.toString() + "\nType: DVD\nDuration: " + duration + " minutes";
    }
}

class Magazine extends LibraryItem {
    private int issueNumber;


    // define parameterized consturcor with 4 arguments : int itemId, String title, String publisher, int issueNumber
    public Magazine(int itemId, String title, String publisher, int issueNumber){
        super(itemId,title,publisher);
        this.issueNumber = issueNumber;
    }



    @Override
    public String toString() {
        return super.toString() + "\nType: Magazine\nIssue Number: " + issueNumber;
    }
}

class Library<T extends LibraryItem> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public void displayItems() {
        for (T item : items) {
            System.out.println(item);
            System.out.println("-------------");
        }
    }

    public boolean checkOutItem(int itemId) {
        // write your code here
        T item = findItem(itemId);
        if(item != null){
            try{
                item.checkedOut();
                return true;
            }catch(ItemNotAlreadyCheckedOutException e){
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean returnItem(int itemId) {
        // write your code here
        T item = findItem(itemId);
        if(item != null){
            try {
                item.returnItem();
                return true;
            }catch(ItemNotAlreadyCheckedOutException e){
                System.out.println(e.getMessage());
            }
        }

        return false;
    }

    private T findItem(int itemId) {
        // write your code here
        for(T item : items){
            if(item.getItem() == itemId){
                return item;
            }
        }
        return null;
    }
}