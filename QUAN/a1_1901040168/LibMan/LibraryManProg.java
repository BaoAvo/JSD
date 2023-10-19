package LibMan;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LibraryManProg {
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
        LocalDate d1 = LocalDate.parse("2018-05-26", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse("2018-05-28", DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        System.out.println(diffDays);

        
        LocalDate dueDate = LocalDate.of(2022, 1, 1);  
          
        LocalDate returnDate1 = LocalDate.of(2022, 1, 5);  
        System.out.println(FineCalculator.calculateFine(returnDate1, dueDate));  // Outputs: 4.0  
  
        LocalDate returnDate2 = LocalDate.of(2022, 1, 12);  
        System.out.println(FineCalculator.calculateFine(returnDate2, dueDate));  // Outputs: 15.0  
  
        LocalDate returnDate3 = LocalDate.of(2022, 1, 22);  
        System.out.println(FineCalculator.calculateFine(returnDate3, dueDate));  // Outputs: 57.0 

        Transaction transaction = new Transaction("P001", "JD-4-2023", LocalDate.of(2023, 5, 8), LocalDate.of(2023, 5, 10), LocalDate.of(2023, 5, 27), 2081933.00);  
        System.out.println(transaction.getTransactionDetails()); 
    }
}
