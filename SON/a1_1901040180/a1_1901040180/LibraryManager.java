package a1_190140180;

import common.*;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryManager {
    private final Date RETURN_DATE = new DateUtils().getCurrentDate();
    public List<Book> books;
    public List<LibraryTransaction> transactions;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        List<LibraryTransaction> checkedOutBooksList = new ArrayList<>();
        this.transactions.forEach(transaction -> {
            if (transaction.getPatronLibraryTransaction().getPatronId().equals(patron.getPatronId())) {
                checkedOutBooksList.add(transaction);
            }
        });
        return checkedOutBooksList;
    }

    public void checkoutBook(Patron patron, Book book, Date checkoutDate, Date dueDate) {
        int borrowBookLimit = this.checkBorrowBookLimitFollowingPatronType(patron);
        int borrowedBookSize = getCheckedOutBooks(patron).size();
        if (borrowedBookSize >= borrowBookLimit) {
            System.out.println("You has exceeded checkout limit.");
        } else {
            if (book.getNumCopiesAvailable() <= 0) {
                System.out.println("The number of books is no longer enough");
            } else {
                LibraryTransaction transaction = new LibraryTransaction(patron, book, checkoutDate, dueDate);
                transactions.add(transaction);
                int numberCopiesAvailableCurrent = book.getNumberOfCopiesAvailable() - 1;
                book.setNumberOfCopiesAvailable(numberCopiesAvailableCurrent);
            }
        }
    }

    public int checkBorrowBookLimitFollowingPatronType(Patron patron) {
        switch (patron.getPatronType()) {
            case REGULAR : return 3;
            case PREMIUM : return 5;
            default : return 0;
        }
    }

    public void returnBook(LibraryTransaction transaction, Date returnDate) {
        transaction.setReturnDate(returnDate);
        double amountOverdue = transaction.calculateFine(returnDate, transaction.getDueDate());
        transaction.setFineAmount(amountOverdue);
        int numberCopiesAvailableCurrent = transaction.getBookLibraryTransaction().getNumberOfCopiesAvailable() + 1;
        transaction.getBookLibraryTransaction().setNumberOfCopiesAvailable(numberCopiesAvailableCurrent);
        transaction.getDescription();
        System.out.println("You have successfully returned book!");
    }

    public List<LibraryTransaction> getOverdueBooks() {
        return transactions.stream()
                .filter(transaction ->
                        transaction.getDueDate() != null && RETURN_DATE.after(transaction.getDueDate()) && transaction.getReturnDate() == null)
                .collect(Collectors.toList());
    }

    public void sort() {
        Comparator<LibraryTransaction> transactionComparator = Comparator.comparing(
                transaction -> transaction.getPatronLibraryTransaction().getPatronId()
        );
        Collections.sort(transactions, transactionComparator);
    }
}
