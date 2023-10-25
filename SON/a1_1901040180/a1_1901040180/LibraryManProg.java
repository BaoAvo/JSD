package a1_190140180;
import common.DateUtils;
import common.Genre;
import common.PatronType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LibraryManProg {
    private static final Date CURRENT_DATE = new DateUtils().getCurrentDate();
    private static Date[] checkoutDate = new Date[]{
            new Date(2023 - 1900, Calendar.MARCH, 25),
            new Date(2023 - 1900, Calendar.MAY, 8),
            new Date(2023 - 1900, Calendar.JUNE, 1),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.AUGUST, 10)
    };

    private static Date[] dueDate = new Date[]{
            new Date(2023 - 1900, Calendar.APRIL, 25),
            new Date(2023 - 1900, Calendar.MAY, 10),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.JULY, 25),
            new Date(2023 - 1900, Calendar.SEPTEMBER, 20)
    };

    private static final String[][] BOOK_INFO = new String[][]{
            {"The Silent Observer", "Sarah Roberts", Genre.MYSTERY.name(), convertIntToString(2018), convertIntToString(10)},
            {"Beyond the Stars", "David Anderson", Genre.SCIENCE_FICTION.name(), convertIntToString(2020), convertIntToString(51)},
            {"Whispers in the Dark", "Emily Taylor", Genre.THRILLER.name(), convertIntToString(2017), convertIntToString(20)},
            {"Lost in the Wilderness", "James Mitchell", Genre.ADVENTURE.name(), convertIntToString(2015), convertIntToString(32)},
            {"Love's Eternal Embrace", "Jennifer Smith", Genre.ROMANCE.name(), convertIntToString(2019), convertIntToString(7)},
            {"The Enchanted Realm", "Daniel Turner", Genre.FANTASY.name(), convertIntToString(2016), convertIntToString(13)},
            {"The Art of Deception", "Jessica Williams", Genre.SELF_HELP.name(), convertIntToString(2021), convertIntToString(24)},
            {"Echoes of the Past", "Robert Johnson", Genre.HISTORY.name(), convertIntToString(2014), convertIntToString(35)},
            {"Shadows in the Night", "Olivia Davis", Genre.HORROR.name(), convertIntToString(2022), convertIntToString(45)},
            {"Laughing Out Loud", "Thomas White", Genre.NON_FICTION.name(), convertIntToString(2013), convertIntToString(46)}
    };

    private static final String[][] PATRON_INFO = new String[][]{
            {"Truong Dang Son", "2001-06-06", "truong.dang.son@gmail.com", "034-747-6477", PatronType.REGULAR.name()},
            {"Pham Van Bao", "2001-07-29", "pham.van.bao@gmail.com", "037-209-9999", PatronType.PREMIUM.name()},
            {"Ngo Ba Kha", "1993-11-27", "ngo.ba.kha@gmail.com", "033-265-7478",PatronType.REGULAR.name()}
    };

    private static String convertIntToString(int number) {
        return String.valueOf(number);
    }

    public static void main(String[] args) {
        LibraryManager libManage = new LibraryManager();

        //  Initialize at least 10 books in the library collection.
        for (int i = 0; i < BOOK_INFO.length; i++) {
            Book book = new Book(BOOK_INFO[i][0], BOOK_INFO[i][1], Genre.valueOf(BOOK_INFO[i][2]), Integer.parseInt(BOOK_INFO[i][3]), Integer.parseInt(BOOK_INFO[i][4]));
            libManage.addBook(book);
        }

        //  Initialize at least 3 patrons involving both regular and premium patrons.
        ArrayList<Patron> patronList = new ArrayList<>();
        for (int i = 0; i < PATRON_INFO.length; i++) {
            patronList.add(new Patron(PATRON_INFO[i][0], PATRON_INFO[i][1], PATRON_INFO[i][2], PATRON_INFO[i][3], PatronType.valueOf(PATRON_INFO[i][4])));
        }

        //  Initialize and use to create 5 book transactions
        libManage.checkoutBook(patronList.get(0), libManage.books.get(0), checkoutDate[0], dueDate[2]);
        libManage.checkoutBook(patronList.get(1), libManage.books.get(1), checkoutDate[0], dueDate[0]);
        libManage.checkoutBook(patronList.get(2), libManage.books.get(6), checkoutDate[0], dueDate[0]);
        libManage.checkoutBook(patronList.get(1), libManage.books.get(9), checkoutDate[2], dueDate[4]);
        libManage.checkoutBook(patronList.get(1), libManage.books.get(7), checkoutDate[2], dueDate[2]);

        //  Print a list of currently checked-out books
        System.out.println("---------------------------------\nChecked-out Books: ");
        List<LibraryTransaction> checkoutBooks = libManage.getCheckedOutBooks(patronList.get(2));
        for (LibraryTransaction transaction : checkoutBooks) {
            transaction.getDescription();
        }

        //  Print list of the overdue books that are not returned yet
        System.out.println("---------------------------------\nOverdue Books: ");
        List<LibraryTransaction> overdueBooks = libManage.getOverdueBooks();
        overdueBooks.forEach(LibraryTransaction::getDescription);

        //  Patron returns the book
        System.out.println("---------------------------------\nReturn Books: ");
        libManage.returnBook(checkoutBooks.get(0), CURRENT_DATE);

        //  Sort transactions by patron ID
        libManage.sort();

        // End the program
        System.exit(0);
    }
}
