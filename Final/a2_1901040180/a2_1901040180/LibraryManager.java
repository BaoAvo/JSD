import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LibraryManager {
    private List<Book> books;
    private List<LibraryTransaction> transactions;
    private DatabaseConnector connector;

    public LibraryManager() {
        transactions = new ArrayList<>();
        books = new ArrayList<>();
        connector = new DatabaseConnector();
    }

    public void addBook(Book book) {
        connector.addBookToDb(book);
    }


    public void addPatron(Patron patron) {
        connector.addPatronToDb(patron);
    }

    public void addTransaction(LibraryTransaction transaction) {
        connector.addTransactionToDb(transaction);
    }


    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        return connector.getCheckedOutBooks(patron);
    }

    public Book getBookById(String bookId) {
        return connector.getBookById(bookId);
    }

    public Patron getPatronById(String patronId) {
        return connector.getPatronById(patronId);
    }


    public List<LibraryTransaction> getOverdueBooks() {
        return connector.getOverdueBooks();
    }

    public List<Book> getBooks() {
        return connector.getAllBooks();
    }

    public void removeTransaction(String patron, String book) {
        connector.removeTransaction(patron,book);
    }

    public List<Patron> getPatrons() {
        return connector.getAllPatrons();
    }
}
