import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LibraryTransaction {
    private Patron patronLib;

    private Book bookLib;

    private Date checkoutDate;

    private Date dueDate;
    private Date returnDate;
    private double fineAmount;

    public LibraryTransaction(Patron patronLib, Book bookLib, Date checkoutDate, Date dueDate) {
        this.patronLib = patronLib;
        this.bookLib = bookLib;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.setFineAmount(calculateFine(this.getReturnDate(),dueDate));
    }

    public Patron getPatronLib() {
        return patronLib;
    }

    public void setPatronLib(Patron patronLib) {
        this.patronLib = patronLib;
    }

    public Book getBookLib() {
        return bookLib;
    }

    public void setBookLib(Book bookLib) {
        this.bookLib = bookLib;
    }

    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public double calculateFine(Date returnDate, Date dueDate){
        if(returnDate == null){
            return 0;
        }
        LocalDate returnDateCast = returnDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        LocalDate dueDateCast = dueDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        long daysOverdue = ChronoUnit.DAYS.between(dueDateCast,returnDateCast);

        if (daysOverdue <= 0) {
            return 0.0;
        } else if (daysOverdue <= 7) {
            return daysOverdue * 1.0;
        } else if (daysOverdue <= 14) {
            return daysOverdue * 2.0;
        } else {
            return daysOverdue * 3.0;
        }
    }
}
