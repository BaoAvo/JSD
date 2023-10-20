package LibMan;

import common.*;

import java.util.*;

public class LibraryManProg {
    private static Date[] checkoutDate = new Date[]{
            new Date(2023 - 1900, Calendar.MARCH, 25),
            new Date(2023 - 1900, Calendar.MAY, 8),
            new Date(2023 - 1900, Calendar.JUNE, 1),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.AUGUST, 10)
    };

    private static Date[] dueDate = new Date[]{
            new Date(2023 - 1900, Calendar.APRIL, 25),
            new Date(2023 - 1900, Calendar.MAY, 10),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.JULY, 25),
            new Date(2023 - 1900, Calendar.SEPTEMBER, 20)
    };

    //    Specify and implement the LibraryManProgclass, which is the main program class.
    //    This class has a main method that performs the following tasks:
    //    (a) Initialize at least 10 books in the library collection.
    //    (b) Initialize at least 3 patrons involving both regular and premium patrons.
    //    (c) Initialize and use to create 5 book transactions
    //    (d) Print a list of currently checked-out books
    //    (e) Print list of the overdue books that are not returned yet
    //    (f) Patron returns the book
    //    (g) Sort transactions by patron ID
    //    (h) End the program.
    public static void main(String[] args) {
        DateUtils dateUtils = new DateUtils();
        Date currentDate = dateUtils.getCurrentDate();
        LibraryManager libraryManager = new LibraryManager();

        //  Initialize at least 10 books in the library collection.
        Book book1 = new Book("To Kill a Mockingbird", "Harper Lee", Genre.FICTION, 2005, 3);
        Book book2 = new Book("Pride and Prejudice", "Jane Austen", Genre.MYSTERY, 2010, 5);
        Book book3 = new Book("The Great Gatsby", "Scott Fitzgerald", Genre.SCIENCE_FICTION, 2018, 2);
        Book book4 = new Book("The Catcher in the Rye", "De Salinger", Genre.HISTORY, 1995, 7);
        Book book5 = new Book("Moby-Dick", "Herman Melville", Genre.ROMANCE, 2015, 4);
        Book book6 = new Book("War and Peace", "Leo Tolstoy", Genre.THRILLER, 2013, 6);
        Book book7 = new Book("The Lord of the Rings", "Rich Tolkien", Genre.BIOGRAPHY, 2002, 1);
        Book book8 = new Book("Harry Potter and the Sorcerer's Stone", "Kane Rowling", Genre.FANTASY, 2008, 8);
        Book book9 = new Book("The Hunger Games", "Suzanne Collins", Genre.HORROR, 2017, 9);
        Book book10 = new Book("The Alchemist", "Paulo Coelho", Genre.NON_FICTION, 2000, 10);

        // Add Book to list
        libraryManager.books.addAll(Arrays.asList(book1, book2, book3, book4, book5,book6,book7,book7,book9,book10));
        for (Book book : libraryManager.books) {
            System.out.println(book.toString());
        }
        System.out.println("---------------------------------");

        //  Initialize at least 3 patrons involving both regular and premium patrons.
        Patron patron1 = new Patron("John Doe", "1990-05-15", "john.doe@email.com", "123-456-7890", PatronType.REGULAR);
        Patron patron2 = new Patron("Jane Smith", "1985-07-20", "jane.smith@email.com", "987-654-3210", PatronType.PREMIUM);
        Patron patron3 = new Patron("Alice Johnson", "1998-03-10", "alice.johnson@email.com", "555-123-4567", PatronType.REGULAR);
        List<Patron> patronList = new ArrayList<>();
        patronList.add(patron1);
        patronList.add(patron2);
        patronList.add(patron3);
        for (Patron i : patronList) {
            System.out.println(i.toString());
        }
        System.out.println("---------------------------------");

        //  Initialize and use to create 5 book transactions
        LibraryTransaction transaction1 = new LibraryTransaction(patron1, book1, checkoutDate[0], dueDate[2], currentDate);
        LibraryTransaction transaction2 = new LibraryTransaction(patron2, book2, checkoutDate[0], dueDate[0], currentDate);
        LibraryTransaction transaction3 = new LibraryTransaction(patron3, book7, checkoutDate[0], dueDate[0], currentDate);
        LibraryTransaction transaction4 = new LibraryTransaction(patron2, book10, checkoutDate[2], dueDate[4], currentDate);
        LibraryTransaction transaction5 = new LibraryTransaction(patron2, book8, checkoutDate[2], dueDate[2], currentDate);
        libraryManager.transactions.addAll(Arrays.asList(transaction1, transaction2, transaction3, transaction4, transaction5));

        // get check out books
        List<LibraryTransaction> checkedOutBooks = libraryManager.getCheckedOutBooks(patron2);

        System.out.println("Books checked out by patron2:");
        for (LibraryTransaction transaction : checkedOutBooks) {
            transaction.getDescription();
            System.out.println("------------------------------------");
        }
        
        //  Print a list of currently checked-out books
        System.out.println("Checked-out Books: "); 
        List<LibraryTransaction> checkoutBooks = libraryManager.getCheckedOutBooks(patron2);
        checkoutBooks.getDescription();

        //  Print list of the overdue books that are not returned yet
        System.out.println("Overdue Books: ");  
        List<LibraryTransaction> overdueBooks = libraryManager.getOverdueBooks();
        overdueBooks.getDescription(); 
        //  Patron returns the book
        System.out.println("Return Books: ");
        libraryManager.returnBook(transaction5,currentDate);
        //  Sort transactions by patron ID
        System.out.println("After sort: ");
        libraryManager.transactions.sort();
        libraryManager.transactions.getDescription();
        // End the program  
        System.exit(0); 
    }
}
