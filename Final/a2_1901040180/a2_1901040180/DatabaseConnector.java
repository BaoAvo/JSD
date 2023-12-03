import java.lang.invoke.CallSite;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseConnector {
    private static final String JDBC_URL = "jdbc:sqlite:D:\\Assignment JSD\\Final\\a2_1901040180\\a2_1901040180\\library.db";
    private static Connection conn = null;

    private final QueryConstants constants = new QueryConstants();
    private final MessageConstants messageConstants = new MessageConstants();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(messageConstants.DATE_FORMATTER);

    public DatabaseConnector() {
        connect();
    }

    public void connect() {
        if (conn != null) return;
        else {
            try {
                conn = DriverManager.getConnection(JDBC_URL);
                System.out.println("Connected to the database");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error connecting to the database");
            }
        }
    }

    public void addBookToDb(Book book) {
        try {
            PreparedStatement state = conn.prepareStatement(constants.ADD_BOOK_TO_DB);
            state.setString(1, book.getIsbn());
            state.setString(2, book.getTitle());
            state.setString(3, book.getAuthor());
            state.setString(4, book.getGenre().name());
            state.setString(5, book.getPublicationYear());
            state.setInt(6, book.getNumCopiesAvailable());

            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPatronToDb(Patron patron) {
        try {
            PreparedStatement state = conn.prepareStatement(constants.ADD_PATRON_TO_DB);
            state.setString(1, patron.getName());
            state.setString(2, patron.getDob().toString());
            state.setString(3, patron.getEmail());
            state.setString(4, patron.getPhoneNumber());
            state.setString(5, patron.getPatronType().toString());

            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransactionToDb(LibraryTransaction transaction) {
        try {
            PreparedStatement state = conn.prepareStatement(constants.ADD_TRANSACTION_TO_DB);
            state.setString(1, String.valueOf(transaction.getBook().getId()));
            state.setString(2, String.valueOf(transaction.getPatron().getId()));
            state.setString(3, dateFormatter.format(transaction.getCheckoutDate()));
            state.setString(4, dateFormatter.format(transaction.getDueDate()));

            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        List<LibraryTransaction> checkedOutBooks = new ArrayList<>();
        try {
            PreparedStatement state = conn.prepareStatement(constants.FIND_CHECKOUT_BOOK_BY_PATRON_ID);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                String patronId = rs.getString("patron_id");
                String bookId = rs.getString("book_id");
                Date checkoutDate = dateFormatter.parse(rs.getString("checkoutDate"));
                Date dueDate = dateFormatter.parse(rs.getString("dueDate"));

                Patron newPatron = getPatronById(patronId);
                Book newBook = getBookById(bookId);
                LibraryTransaction transaction = new LibraryTransaction(
                        newPatron,
                        newBook,
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
            PreparedStatement prepareStatement = conn.prepareStatement(constants.GET_ALL_TRANSACTION);
            ResultSet rs = prepareStatement.executeQuery();
            while (rs.next()) {
                String patronId = rs.getString("patron_id");
                String bookId = rs.getString("book_id");
                String checkoutDateString = rs.getString("checkoutDate");
                String dueDateString = rs.getString("dueDate");

                Patron newPatronTransaction = getPatronById(patronId);
                Book newBookTransaction = getBookById(bookId);
                LibraryTransaction transaction = new LibraryTransaction(
                        newPatronTransaction,
                        newBookTransaction,
                        dateFormatter.parse(checkoutDateString),
                        dateFormatter.parse(dueDateString)
                );
                checkedOutBooks.add(transaction);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return checkedOutBooks;
    }

    public Book getBookById(String bookId) {
        Book book = null;
        try {
            PreparedStatement state = conn.prepareStatement(constants.GET_BOOK_BY_ID);
            state.setString(1, bookId);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String isbn = rs.getString("ISBN");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                String pubYear = rs.getString("pubYear");
                int numCopiesAvailable = rs.getInt("numCopiesAvailable");
                book = new Book(title, author, Genre.valueOf(genre), pubYear, numCopiesAvailable);
                book.setId(id);
                book.setIsbn(isbn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public Patron getPatronById(String patronId) {
        Patron patron = null;
        try {
            PreparedStatement state = conn.prepareStatement(constants.GET_PATRON_BY_ID);
            state.setString(1, patronId);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                PatronType patronType = PatronType.valueOf(rs.getString("patronType"));

                patron = new Patron(name, dob, email, phone, patronType);
                patron.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patron;
    }

    public List<LibraryTransaction> getOverdueBooks() {
        List<LibraryTransaction> overdueBooks = new ArrayList<>();
        try {
            PreparedStatement state = conn.prepareStatement(constants.GET_TRANSACTION_OVERDUE_BOOKS);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                String patronId = rs.getString("patron_id");
                String bookId = rs.getString("book_id");
                Date checkoutDate = dateFormatter.parse(rs.getString("checkoutDate"));
                Date dueDate = dateFormatter.parse(rs.getString("dueDate"));
                Patron patron = getPatronById(patronId);
                Book book = getBookById(bookId);
                if(new Date().after(dueDate) && dueDate != null){
                    LibraryTransaction transaction = new LibraryTransaction(
                            patron,
                            book,
                            checkoutDate,
                            dueDate
                    );
                    overdueBooks.add(transaction);
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return overdueBooks;
    }

    public List<Book> getAllBooks(){
        List<Book> allBookList = new ArrayList<>();
        try {
            PreparedStatement state = conn.prepareStatement(constants.GET_ALL_BOOKS);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String isbn = rs.getString("ISBN");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String genre = rs.getString("genre");
                String pubYear = rs.getString("pubYear");
                int numCopiesAvailable = rs.getInt("numCopiesAvailable");
                Book book = new Book(title, author, Genre.valueOf(genre), pubYear, numCopiesAvailable);
                book.setId(id);
                book.setIsbn(isbn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allBookList;
    }

    public void updateBook(Book book) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(constants.UPDATE_BOOK);
            preparedStatement.setInt(1, book.getNumberOfCopiesAvailable());
            preparedStatement.setString(2, book.getIsbn());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Patron> getAllPatrons(){
        List<Patron> allPatronList = new ArrayList<>();
        try {
            PreparedStatement state = conn.prepareStatement(constants.GET_ALL_PATRONS);
            ResultSet rs = state.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone");
                PatronType patronType = PatronType.valueOf(rs.getString("patronType"));
                Patron patron = new Patron(name, dob, email, phoneNumber, patronType);
                patron.setId(id);
                allPatronList.add(patron);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allPatronList;
    }

    public void removeTransaction(String patron, String book) {
        List<Patron> patronList = getAllPatrons();
        String patronId = String.valueOf(patronList.stream()
                .filter(p -> p.getName().equals(patron))
                .findFirst()
                .map(Patron::getId)
                .orElse(null));
        List<Book> bookList = getAllBooks();
        int bookId = bookList.stream()
                .filter(b -> b.getTitle().equals(book))
                .findFirst()
                .map(Book::getId)
                .orElse(0);
        try {
            PreparedStatement prepareStatement = conn.prepareStatement(constants.DELETE_TRANSACTION);
            prepareStatement.setInt(1, bookId);
            prepareStatement.setString(2, patronId);

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
