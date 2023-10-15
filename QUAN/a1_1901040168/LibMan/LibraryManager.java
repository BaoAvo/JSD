package LibMan;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryManager {
    public List<Book> books; // This list holds all the books in the library
    public List<LibraryTransaction> transactions ; // This list contains all the transactions that have occurred in the library.

    public LibraryManager(){
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    //    This method adds a book to the library
    public List<Book> addBook(Book book){
        this.books.add(book);
        return this.books;
    }

    //    This method retrieves a list of checked-out books for a specific patron
    public List<LibraryTransaction> getCheckedOutBooks(Patron patron){
        return this.transactions;
    }

    //    This method allows a patron to check out a book. It first checks
    //    if the patron has exceeded their checkout limit based on their patron type. If
    //    not, it creates a new LibraryTransaction, adds it to the list of transactions,
    //    and updates the number of available copies for the book.
    public void checkoutBook(Patron patron, Book book, Date checkoutDate, Date dueDate){

    }

    //    This method allows a patron to return a book, calculates fines (if any), and
    //    updates the number of available copies. It sets the return date in the
    //    transaction, calculates fines, updates the number of available copies for the
    //    book, and prints a success message
    public void returnBook(LibraryTransaction transaction, Date returnDate){

    }

    //    This method return a list of library transactions representing overdue books that are not returned yet.
    //    Note: To get the current date to use for calculating the number of overdue days,
    //    you need to use the getCurrentDate() method in the provided
    //    common.DateUtils class (you should not modify this class)
    public List<LibraryTransaction> getOverdueBooks(){
        return this.transactions;
    }

    // This method sorts the list of transactions by patron ID
    public void sort(){}

}
