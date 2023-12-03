import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryManager {
    public List<Book> books;
    public List<LibraryTransaction> transactions;
    private final Connection connection;

    private final Constants constants = new Constants();
    SimpleDateFormat dateFormat = new SimpleDateFormat(constants.PATTERN_DATE_FORMAT);


    public LibraryManager() {
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
        connection = DatabaseConnect.connect();
    }

    public void addBook(Book book) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(constants.ADD_BOOK_TO_DB);
            prepareStatement.setString(1, book.getIsbn());
            prepareStatement.setString(2, book.getTitle());
            prepareStatement.setString(3, book.getAuthor());
            prepareStatement.setString(4, book.getGenre().name());
            prepareStatement.setString(5, book.getPublicationYear());
            prepareStatement.setInt(6, book.getNumCopiesAvailable());

            prepareStatement.executeUpdate();
            System.out.println(constants.ADD_BOOK_TO_DB_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(constants.ADD_BOOK_TO_DB_FAIL);
        }
    }

    public void addPatron(Patron patron) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(constants.ADD_PATRON_TO_DB);
            prepareStatement.setString(1, patron.getName());
            prepareStatement.setString(2, patron.getDob());
            prepareStatement.setString(3, patron.getEmail());
            prepareStatement.setString(4, patron.getPhoneNumber());
            prepareStatement.setString(5, patron.getPatronType().toString());

            prepareStatement.executeUpdate();
            System.out.println(constants.ADD_PATRON_TO_DB_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(constants.ADD_PATRON_TO_DB_FAIL);
        }
    }

    public void addTransaction(LibraryTransaction transaction) {
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(constants.ADD_TRANSACTION_TO_DB);
            prepareStatement.setString(1, String.valueOf(transaction.getBookLib().getId()));
            prepareStatement.setString(2, String.valueOf(transaction.getPatronLib().getId()));
            prepareStatement.setString(3, dateFormat.format(transaction.getCheckoutDate()));
            prepareStatement.setString(4, dateFormat.format(transaction.getDueDate()));

            prepareStatement.executeUpdate();
            System.out.println(constants.ADD_TRANSACTION_TO_DB_SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(constants.ADD_TRANSACTION_TO_DB_FAIL);
        }
    }

    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        List<LibraryTransaction> checkedOutBooks = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(constants.GET_TRANSACTION_BY_ID);
            prepareStatement.setString(1, patron.getPatronId());
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                String patronId = rs.getString("patron_id");
                String bookId = rs.getString("book_id");
                String checkoutDateString = rs.getString("checkoutDate");
                String dueDateString = rs.getString("dueDate");
                Date checkoutDate = dateFormat.parse(checkoutDateString);
                Date dueDate = dateFormat.parse(dueDateString);

                Patron newPatronTransaction = getPatronById(patronId);
                Book newBookTransaction = getBookById(bookId);
                LibraryTransaction transaction = new LibraryTransaction(
                        newPatronTransaction,
                        newBookTransaction,
                        checkoutDate,
                        dueDate
                );
                checkedOutBooks.add(transaction);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return checkedOutBooks;
    }

    public List<LibraryTransaction> getAllCheckedOutBooks() {
        List<LibraryTransaction> checkedOutBooks = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(constants.GET_ALL_TRANSACTION);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                String patronId = rs.getString("patron_id");
                String bookId = rs.getString("book_id");
                String checkoutDateString = rs.getString("checkoutDate");
                String dueDateString = rs.getString("dueDate");
                Date checkoutDate = dateFormat.parse(checkoutDateString);
                Date dueDate = dateFormat.parse(dueDateString);

                Patron newPatronTransaction = getPatronById(patronId);
                Book newBookTransaction = getBookById(bookId);
                LibraryTransaction transaction = new LibraryTransaction(
                        newPatronTransaction,
                        newBookTransaction,
                        checkoutDate,
                        dueDate
                );
                checkedOutBooks.add(transaction);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return checkedOutBooks;
    }

    public Book getBookById(String bookId) {
        Book book = new Book();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(constants.GET_BOOK_BY_ID);
            preparedStatement.setString(1, bookId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    book.setIsbn(rs.getString("ISBN"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setGenre(Genre.valueOf(rs.getString("genre")));
                    book.setNumberOfCopiesAvailable(rs.getInt("numCopiesAvailable"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public Patron getPatronById(String patronId) {
        Patron patron = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(constants.GET_PATRON_BY_ID);
            preparedStatement.setString(1, patronId);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String patronTypeStr = rs.getString("patronType");

                PatronType patronType = PatronType.valueOf(patronTypeStr);
                patron = new Patron(name, dob, email, phone, patronType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patron;
    }

    public void updateBook(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(constants.UPDATE_BOOK);
            preparedStatement.setInt(1, book.getNumberOfCopiesAvailable());
            preparedStatement.setString(2, book.getIsbn());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LibraryTransaction> getOverdueBooks() {
        List<LibraryTransaction> overdueBooks = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(constants.GET_ALL_TRANSACTION_OVERDUE);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                String patronId = rs.getString("patron_id");
                String bookId = rs.getString("book_id");
                String checkoutDateStr = rs.getString("checkoutDate");
                String dueDateStr = rs.getString("dueDate");
                Date checkoutDate = dateFormat.parse(checkoutDateStr);
                Date dueDate = dateFormat.parse(dueDateStr);

                Patron newPatronTransaction = getPatronById(patronId);
                Book newBookTransaction = getBookById(bookId);
                if (dueDate.after(new Date())) continue;
                LibraryTransaction transaction = new LibraryTransaction(
                        newPatronTransaction,
                        newBookTransaction,
                        checkoutDate,
                        dueDate
                );
                overdueBooks.add(transaction);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return overdueBooks;
    }

    public void sort() {
        for (int i = 0; i < transactions.size() - 1; i++) {
            for (int j = i + 1; j < transactions.size(); j++) {
                LibraryTransaction transaction1 = transactions.get(i);
                LibraryTransaction transaction2 = transactions.get(j);
                String patronId1 = transaction1.getPatronLib().getPatronId();
                String patronId2 = transaction2.getPatronLib().getPatronId();

                if (patronId1.compareTo(patronId2) > 0) {
                    transactions.set(i, transaction2);
                    transactions.set(j, transaction1);
                }
            }
        }
    }

    public List<Book> getBooks() {
        List<Book> allBooksList = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(constants.GET_ALL_BOOK);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("ISBN"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(Genre.valueOf(rs.getString("genre")));
                book.setPublicationYear(rs.getString("pubYear"));
                book.setNumberOfCopiesAvailable(rs.getInt("numCopiesAvailable"));

                allBooksList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBooksList;
    }

    public void removeTransaction(String patron, String book) {
        List<Patron> patronList = getPatrons();
        String patronId = String.valueOf(patronList.stream()
                .filter(p -> p.getName().equals(patron))
                .findFirst()
                .map(Patron::getId)
                .orElse(null));
        List<Book> bookList = getBooks();
        int bookId = bookList.stream()
                .filter(b -> b.getTitle().equals(book))
                .findFirst()
                .map(Book::getId)
                .orElse(0);
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(constants.DELETE_TRANSACTION);
            prepareStatement.setInt(1, bookId);
            prepareStatement.setInt(2, Integer.parseInt(patronId));

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Patron> getPatrons() {
        List<Patron> allPatronsList = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = connection.prepareStatement(constants.GET_ALL_PATRON);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone");
                PatronType patronType = PatronType.valueOf(rs.getString("patronType"));
                Patron patron = new Patron(name, dob, email, phoneNumber, patronType);

                allPatronsList.add(patron);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPatronsList;
    }
}
