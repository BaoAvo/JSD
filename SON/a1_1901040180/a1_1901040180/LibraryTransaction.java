package a1_190140180;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LibraryTransaction {
    private Patron patronLibraryTransaction;

    private Book bookLibraryTransaction;

    private Date checkoutDate;

    private Date dueDate;
    private Date returnDate;
    private double fineAmount;

    public LibraryTransaction(Patron patronLibraryTransaction, Book bookLibraryTransaction, Date checkoutDate, Date dueDate) {
        this.patronLibraryTransaction = patronLibraryTransaction;
        this.bookLibraryTransaction = bookLibraryTransaction;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        double amount = this.calculateFine(this.getReturnDate(), dueDate);
        this.setFineAmount(amount);
    }

    public Patron getPatronLibraryTransaction() {
        return patronLibraryTransaction;
    }

    public void setPatronLibraryTransaction(Patron patronLibraryTransaction) {
        this.patronLibraryTransaction = patronLibraryTransaction;
    }

    public Book getBookLibraryTransaction() {
        return bookLibraryTransaction;
    }

    public void setBookLibraryTransaction(Book bookLibraryTransaction) {
        this.bookLibraryTransaction = bookLibraryTransaction;
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

    public double calculateFine(Date returnDate, Date dueDate) {
        if (returnDate == null) {
            return 0;
        }
        Calendar returnCalendar = Calendar.getInstance();
        returnCalendar.setTime(returnDate);
        Calendar dueCalendar = Calendar.getInstance();
        dueCalendar.setTime(dueDate);
        long daysOverdue = ((returnCalendar.getTimeInMillis() - dueCalendar.getTimeInMillis()) / (24 * 60 * 60 * 1000));
        double amountOverdue = daysOverdue <= 0 ? 0.0 : daysOverdue <= 7 ? daysOverdue * 1.0 : daysOverdue <= 14 ? daysOverdue * 2.0 : daysOverdue * 3.0;
        return amountOverdue;
    }

    public void getDescription() {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("E, MMM dd yyyy");

        String returnDateString = "";
        if (this.getReturnDate() != null) {
            returnDateString = dateTimeFormatter.format(this.getReturnDate());
        } else {
            returnDateString = "N/A";
        }

        String fineAmountString = "";
        if (this.getFineAmount() > 0) {
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
            fineAmountString = currencyFormatter.format(this.getFineAmount());
        } else {
            fineAmountString = "N/A";
        }

        StringBuilder description = new StringBuilder("Transaction Details:\n");
        description.append("\tPatron ID: ").append(this.getPatronLibraryTransaction().getPatronId()).append("\n");
        description.append("\tBook ISBN: ").append(this.getBookLibraryTransaction().getIsbn()).append("\n");
        description.append("\tCheckout Date: ").append(dateTimeFormatter.format(this.getCheckoutDate())).append("\n");
        description.append("\tDue Date: ").append(dateTimeFormatter.format(this.getDueDate())).append("\n");
        description.append("\tReturn Date: ").append(returnDateString).append("\n");
        description.append("\tFine Amount: ").append(fineAmountString);

        System.out.println(description.toString());
    }
}
