
import java.util.Date;

public class LibraryTransaction {
    private Patron patron;
    private Book book;
    private Date checkoutDate;
    private Date dueDate;

    public LibraryTransaction(Patron patron, Book book, Date checkoutDate, Date dueDate) {
        this.patron = patron;
        this.book = book;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "LibraryTransaction: \n" +
               "Patron Name: " + patron.getName() +
               "\nBook Title: " + book.getTitle() +
               "\nCheckout Date: " + checkoutDate +
               "\nDueDate: " + dueDate +
               ' ';
    }

    public Patron getPatron() {
        return patron;
    }

    public Book getBook() {
        return book;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
