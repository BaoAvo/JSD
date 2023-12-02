package a2_1901040168;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryManager {
    public List<Book> books; // This list holds all the books in the library
    public List<LibraryTransaction> transactions; // This list contains all the transactions that have occurred in the library.

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    //    This method adds a book to the library
    public void addBook(Book book) {
        this.books.add(book);
    }

    //    This method retrieves a list of checked-out books for a specific patron
    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        List<LibraryTransaction> checkedOutBooks = new ArrayList<>();
        for (LibraryTransaction transaction : transactions) {
            if (transaction.getPatronLib().getPatronId() == patron.getPatronId()) {
                checkedOutBooks.add(transaction);
            }
        }
        return checkedOutBooks;
    }

    public void checkoutBook(Patron patron, Book book, Date checkoutDate, Date dueDate) {
        int checkoutLimit = 0; // Assume you have a method to get the patron's checkout limit
        if (patron.getPatronType().equals(PatronType.REGULAR)) {
            checkoutLimit = 3;
        } else if (patron.getPatronType().equals(PatronType.PREMIUM)) {
            checkoutLimit = 5;
        } else {
            checkoutLimit = 0;
        }
        int booksCheckedOut = getCheckedOutBooks(patron).size(); // Assume you have a method to get patron's checked-out books

        if (booksCheckedOut < checkoutLimit) {
            if (book.getNumCopiesAvailable() > 0) {
                LibraryTransaction transaction = new LibraryTransaction(patron, book, checkoutDate, dueDate);
                transactions.add(transaction);
                book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable() - 1);
            } else {
                System.out.println(book.getTitle() + " in the library are out of stock");
            }
        } else {
            System.out.println("Hey " + patron.getName() + "! You has exceeded checkout limit.");
        }
    }

    public void returnBook(LibraryTransaction transaction, Date returnDate) {
        transaction.setReturnDate(returnDate);
        transaction.setFineAmount(transaction.calculateFine(returnDate,transaction.getDueDate()));
        transaction.getBookLib().setNumberOfCopiesAvailable(transaction.getBookLib().getNumberOfCopiesAvailable() + 1);
        transaction.getDescription();
        System.out.println(String.format("You have successfully returned the %s book!",transaction.getBookLib().getTitle()));
    }

    public List<LibraryTransaction> getOverdueBooks() {
        List<LibraryTransaction> overdueBooks = new ArrayList<>();
        DateUtils dateUtils = new DateUtils();
        Date returnDate = dateUtils.getCurrentDate();// Get the current date
        for (LibraryTransaction transaction : transactions) {
            Date dueDate = transaction.getDueDate();
            if (dueDate != null && returnDate.after(dueDate) &&  transaction.getReturnDate() == null) {
                overdueBooks.add(transaction);
            }
        }
        return overdueBooks;
    }

    public void sort() {
        for (int i = 0; i < transactions.size() - 1; i++) {
            for (int j = i + 1; j < transactions.size(); j++) {
                LibraryTransaction transaction1 = transactions.get(i);
                LibraryTransaction transaction2 = transactions.get(j);

                // Compare transactions based on patron IDs
                String patronId1 = transaction1.getPatronLib().getPatronId();
                String patronId2 = transaction2.getPatronLib().getPatronId();

                if (patronId1.compareTo(patronId2) > 0) {
                    // Swap the transactions if they are out of order
                    transactions.set(i, transaction2);
                    transactions.set(j, transaction1);
                }
            }
        }
    }
}
