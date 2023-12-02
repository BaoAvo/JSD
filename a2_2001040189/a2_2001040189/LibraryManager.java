import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LibraryManager {
    private List<Book> books;
    private List<LibraryTransaction> transactions;
    private DateUtils dateUtils;
    private Connection dbConnection;

    public LibraryManager() {
        transactions = new ArrayList<>();
        books = new ArrayList<>();
        dateUtils = new DateUtils();
        dbConnection = DbConnector.connect();
    }

    public void addBook(Book book) {
        try {
            String sql = "INSERT INTO book (ISBN, title, author, genre, pubYear, numCopiesAvailable) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                statement.setString(1, book.getISBN());
                statement.setString(2, book.getTitle());
                statement.setString(3, book.getAuthor());
                statement.setString(4, book.getGenre().name());
                statement.setString(5, book.getPublicationYear().toString());
                statement.setInt(6, book.getNumCopiesAvailable());

                // Execute the update
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addPatron(Patron patron) {
        try {
            String sql = "INSERT INTO patron (name, dob, email, phone, patronType) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                statement.setString(1, patron.getName());

                statement.setString(2, patron.getDob().toString());

                statement.setString(3, patron.getEmail());
                statement.setString(4, patron.getPhoneNumber());
                statement.setString(5, patron.getPatronType().toString());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(LibraryTransaction transaction) {
        try {
            String sql = "INSERT INTO \"transaction\" (book_id, patron_id, checkoutDate, dueDate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                statement.setString(1, String.valueOf(transaction.getBook().getId()));
                statement.setString(2, String.valueOf(transaction.getPatron().getDbId()));

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                statement.setString(3, dateFormat.format(transaction.getCheckoutDate()));
                statement.setString(4, dateFormat.format(transaction.getDueDate()));

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        List<LibraryTransaction> checkedOutBooks = new ArrayList<>();

        try {
            String sql = "SELECT * FROM \"transaction\"";

            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String patronId = resultSet.getString("patron_id");
                        String bookId = resultSet.getString("book_id");
                        String checkoutDateStr = resultSet.getString("checkoutDate");
                        String dueDateStr = resultSet.getString("dueDate");

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date checkoutDate = dateFormat.parse(checkoutDateStr);
                        Date dueDate = dateFormat.parse(dueDateStr);

                        LibraryTransaction transaction = new LibraryTransaction(
                                getPatronById(patronId),
                                getBookById(bookId),
                                checkoutDate,
                                dueDate
                        );

                        checkedOutBooks.add(transaction);
                    }
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }

        return checkedOutBooks;
    }

    public Book getBookById(String bookId) {
        Book book = new Book();

        try {
            String sql = "SELECT * FROM book WHERE id = ?";

            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                statement.setString(1, bookId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        book.setId(resultSet.getInt("id"));
                        book.setISBN(resultSet.getString("ISBN"));
                        book.setTitle(resultSet.getString("title"));
                        book.setAuthor(resultSet.getString("author"));
                        book.setGenre(resultSet.getString("genre"));
                        book.setNumCopiesAvailable(resultSet.getInt("numCopiesAvailable"));
                    }
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
            String sql = "SELECT * FROM patron WHERE id = ?";

            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                statement.setString(1, patronId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String name = resultSet.getString("name");
                        String dob = resultSet.getString("dob");
                        String email = resultSet.getString("email");
                        String phone = resultSet.getString("phone");
                        String patronTypeStr = resultSet.getString("patronType");

                        PatronType patronType = PatronType.valueOf(patronTypeStr);
                        patron = new Patron(name, dob, email, phone, patronType);
                        patron.setDbId(Integer.valueOf(patronId));
                    }
                }
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }

        return patron;
    }


    public List<LibraryTransaction> getOverdueBooks() {
        List<LibraryTransaction> overdueBooks = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(new Date());

        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM \"transaction\"";

            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Retrieve data from the result set
                        String patronId = resultSet.getString("patron_id");
                        String bookId = resultSet.getString("book_id");
                        String checkoutDateStr = resultSet.getString("checkoutDate");
                        String dueDateStr = resultSet.getString("dueDate");

                        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date checkoutDate = dateFormat.parse(checkoutDateStr);
                        Date dueDate = dateFormat.parse(dueDateStr);

                        if (dueDate.after(new Date())) continue;
                        // Create a LibraryTransaction object and add it to the list
                        LibraryTransaction transaction = new LibraryTransaction(
                                getPatronById(patronId), // Assuming you have a method to get a Patron by ID
                                getBookById(bookId), // Assuming you have a method to get a Book by ID
                                checkoutDate,
                                dueDate
                        );

                        overdueBooks.add(transaction);
                    }
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return overdueBooks;
    }

    public void sort() {
        Comparator<LibraryTransaction> patronIdComparator = (t1, t2) -> {
            String patronId1 = t1.getPatron().getId();
            String patronId2 = t2.getPatron().getId();
            return patronId1.compareTo(patronId2);
        };

        Collections.sort(transactions, patronIdComparator);
    }

    public List<Book> getBooks() {
        List<Book> allBooks = new ArrayList<>();

        try {
            // Prepare the SQL statement
            String sql = "SELECT * FROM book";
            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Process the result set
                    while (resultSet.next()) {
                        Book book = new Book();
                        book.setId(resultSet.getInt("id"));
                        book.setISBN(resultSet.getString("ISBN"));
                        book.setTitle(resultSet.getString("title"));
                        book.setAuthor(resultSet.getString("author"));
                        book.setGenre(resultSet.getString("genre"));
                        book.setPublicationYear(resultSet.getString("pubYear"));
                        book.setNumCopiesAvailable(resultSet.getInt("numCopiesAvailable"));

                        allBooks.add(book);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return allBooks;
    }

    public void deleteTransaction(String patron, String book) {
        List<Patron> patronList = getPatrons();
        String patronId= null;
        for (Patron elm : patronList) {
            if (elm.getName().equals(patron)) {
                patronId = elm.getDbId();
            }
        }
        List<Book> bookList = getBooks();
        int bookId = 0;
        for (Book elm: bookList) {
            if (elm.getTitle().equals(book)) {
                bookId = elm.getId();
            }
        }
        try {
            System.out.println("patron: " + patronId);
            System.out.println("book: " + bookId);
            String sql = "DELETE FROM 'transaction' WHERE book_id = ? AND patron_id = ?";
            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                statement.setInt(1, bookId);
                statement.setInt(2, Integer.parseInt(patronId));

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Patron> getPatrons() {
        List<Patron> allPatrons = new ArrayList<>();

        try {
            String sql = "SELECT * FROM patron";
            try (PreparedStatement statement = dbConnection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("name");
                        String dob = resultSet.getString("dob");
                        String email = resultSet.getString("email");
                        String phoneNumber = resultSet.getString("phone");
                        String patronTypeStr = resultSet.getString("patronType");
                        PatronType patronType = PatronType.valueOf(patronTypeStr);
                        Patron patron = new Patron(name, dob, email, phoneNumber, patronType);
                        patron.setDbId(resultSet.getInt("id"));

                        allPatrons.add(patron);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allPatrons;
    }


    public List<LibraryTransaction> getTransactions() {
        return transactions;
    }
}
