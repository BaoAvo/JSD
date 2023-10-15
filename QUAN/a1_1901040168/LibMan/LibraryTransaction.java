package LibMan;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryTransaction {
    private Patrons patronLib;

    private Books bookLib;

    private Date checkoutDate;

    private Date dueDate;
    private Date returnDate;
    private double fineAmount;

    public LibraryTransaction(Patrons patronLib, Books bookLib, Date checkoutDate, Date dueDate, Date returnDate, double fineAmount) {
        this.patronLib = patronLib;
        this.bookLib = bookLib;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fineAmount = fineAmount;
    }

    public double calculateFine(Date returnDate, Date dueDate){
        return 0;
    }
    public void tesst(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy");
        String strDate = formatter.format(date);
        System.out.println(strDate);
    }

}
