public class QueryConstants {
    public final String ADD_BOOK_TO_DB = "INSERT INTO book (ISBN, title, author, genre, pubYear, numCopiesAvailable) VALUES (?, ?, ?, ?, ?, ?)";
    public final String ADD_PATRON_TO_DB = "INSERT INTO patron (name, dob, email, phone, patronType) VALUES (?, ?, ?, ?, ?)";
    public final String ADD_TRANSACTION_TO_DB = "INSERT INTO \"transaction\" (book_id, patron_id, checkoutDate, dueDate) VALUES (?, ?, ?, ?)";
    public final String FIND_CHECKOUT_BOOK_BY_PATRON_ID = "SELECT * FROM \"transaction\" WHERE patron_id = ?";
    public final String GET_ALL_TRANSACTION = "SELECT * FROM \"transaction\"";
    public final String GET_BOOK_BY_ID = "SELECT * FROM book WHERE id = ?";
    public final String UPDATE_BOOK = "UPDATE book SET numCopiesAvailable = ? WHERE ISBN = ?";
    public final String GET_PATRON_BY_ID = "SELECT * FROM patron WHERE id = ?";
    public final String GET_TRANSACTION_OVERDUE_BOOKS = "SELECT * FROM \"transaction\"";
    public final String GET_ALL_BOOKS = "SELECT * FROM book";
    public final String GET_ALL_PATRONS = "SELECT * FROM patron";
    public final String DELETE_TRANSACTION = "DELETE FROM 'transaction' WHERE book_id = ? AND patron_id = ?";
}
