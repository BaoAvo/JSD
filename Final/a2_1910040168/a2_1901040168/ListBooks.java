import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class ListBooks extends JPanel {
    private DefaultTableModel tableListBook;
    private LibraryManager libraryManager;

    public ListBooks(){
        createBookListGUI();
    }
    private void createBookListGUI() {
        libraryManager = new LibraryManager();
        setLayout(new BorderLayout());
        // the panels
        JPanel north = new JPanel();
        JLabel title = new JLabel("List Books");
        north.add(title);
        north.setBackground(Color.YELLOW);
        north.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        north.setFont(new Font("Arial", Font.BOLD, 18));
        add(north, BorderLayout.NORTH);

        tableListBook = new DefaultTableModel();
        tableListBook.addColumn("ID");
        tableListBook.addColumn("ISBN");
        tableListBook.addColumn("Title");
        tableListBook.addColumn("Author");
        tableListBook.addColumn("Genre");
        tableListBook.addColumn("Publication Year");
        tableListBook.addColumn("Copies Available");

        JTable bookTable = new JTable(tableListBook);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane, BorderLayout.CENTER);

        libraryManager.getBooks().forEach(book -> {
            Vector<Object> rowBookList = new Vector<>();
            rowBookList.add(book.getId());
            rowBookList.add(book.getIsbn());
            rowBookList.add(book.getTitle());
            rowBookList.add(book.getAuthor());
            rowBookList.add(book.getGenre().name());
            rowBookList.add(book.getPublicationYear());
            rowBookList.add(book.getNumCopiesAvailable());
            tableListBook.addRow(rowBookList);
        });
    }
}
