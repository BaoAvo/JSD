package a1_1901040168;

import common.DateUtils;
import common.Genre;
import common.PatronType;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public static void main(String[] args) {
        Date currentDate = new DateUtils().getCurrentDate();
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
        Book[] bookArray = new Book[]{book1, book2, book3, book4, book5,book6, book7, book7, book9, book10};
        for (int i = 0; i < bookArray.length; i++) {
            libraryManager.addBook(bookArray[i]);
        }

        //  Initialize at least 3 patrons involving both regular and premium patrons.
        Patron patron1 = new Patron("John Doe", "1990-05-15", "john.doe@email.com", "123-456-7890", PatronType.REGULAR);
        Patron patron2 = new Patron("Jane Smith", "1985-07-20", "jane.smith@email.com", "987-654-3210", PatronType.PREMIUM);
        Patron patron3 = new Patron("Alice Johnson", "1998-03-10", "alice.johnson@email.com", "555-123-4567", PatronType.REGULAR);

        //  Initialize and use to create 5 book transactions
        libraryManager.checkoutBook(patron1, book1, checkoutDate[0], dueDate[2]);
        libraryManager.checkoutBook(patron2, book2, checkoutDate[0], dueDate[0]);
        libraryManager.checkoutBook(patron3, book7, checkoutDate[0], dueDate[0]);
        libraryManager.checkoutBook(patron2, book10, checkoutDate[2], dueDate[4]);
        libraryManager.checkoutBook(patron2, book8, checkoutDate[2], dueDate[2]);

        //  Print a list of currently checked-out books
        System.out.println("---------------------------------");
        System.out.println("Checked-out Books: ");
        List<LibraryTransaction> checkoutBooks = libraryManager.getCheckedOutBooks(patron2);
        checkoutBooks.forEach(i -> {
            i.getDescription();
        });

        //  Print list of the overdue books that are not returned yet
        System.out.println("---------------------------------");
        System.out.println("Overdue Books: ");
        List<LibraryTransaction> overdueBooks = libraryManager.getOverdueBooks();
        overdueBooks.forEach(i -> {
            i.getDescription();
        });

        //  Patron returns the book
        System.out.println("---------------------------------");
        System.out.println("Return Books: ");
        libraryManager.returnBook(checkoutBooks.get(0), currentDate);

        //  Sort transactions by patron ID
        libraryManager.sort();

        // End the program
        System.exit(0);

    }
}
