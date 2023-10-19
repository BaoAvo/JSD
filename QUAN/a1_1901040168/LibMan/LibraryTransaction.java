package LibMan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryTransaction {
    private Patron patronLib;

    private Book bookLib;

    private Date checkoutDate;

    private Date dueDate;
    private Date returnDate;
    private double fineAmount;

    public LibraryTransaction(Patron patronLib, Book bookLib, Date checkoutDate, Date dueDate, Date returnDate, double fineAmount) {
        this.patronLib = patronLib;
        this.bookLib = bookLib;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = calculateFine(returnDate,returnDate);
    }

    //  method calculates the fine amount based on the difference
    //  between the return date and due date, applying the fine rules as specified below.
    //   An overdue fine is generated for each book checked out by a patron, depending on
    //  the number of days the book is overdue. The fine calculation is as follows:
    //   $1.00 per day for books overdue by 1 to 7 days
    //   $2.00 per day for books overdue by 8 to 14 days
    //   $3.00 per day for books overdue by more than 14 days
    public double calculateFine(Date returnDate, Date dueDate){
        long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);  
      
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

    //    method generates a detailed description of the
    //    transaction, including the patron ID, book ISBN, checkout date, due date, return
    //    date (if available), and fine amount (if applicable).
    //    Example:
    //    Transaction Details:
    //    Patron ID: P001
    //    Book ISBN: A1-01-2021
    //    Checkout Date: Mon, May 08 2023
    //    Due Date: Wed, May 10 2023
    //    Return Date: Sun, May 27 3923
    //    Fine Amount: $2081933.00
    public void getDescription(){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E, MMM dd yyyy");  
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);  
  
        String returnDateString = (this.getReturnDate() != null) ? this.getReturnDate().format(dateFormatter) : "N/A";  
        String fineAmountString = (this.getFineAmount() != null && this.getFineAmount() > 0) ? currencyFormatter.format(this.getFineAmount()) : "No Fine";  
        System.out.println("Transaction Details:\n"  
                        + "Patron ID: " + this.getParonLib().getPatronId() + "\n"  
                        + "Book ISBN: " + this.getBookLib().getIsbn() + "\n"  
                        + "Checkout Date: " + this.getCheckoutDate().format(dateFormatter) + "\n"  
                        + "Due Date: " + this.getDueDate().format(dateFormatter) + "\n"  
                        + "Return Date: " + returnDateString + "\n"  
                        + "Fine Amount: " + fineAmountString);
    }
    public void tesst(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
        String strDate = formatter.format(date);
        System.out.println(strDate);
    }

}
