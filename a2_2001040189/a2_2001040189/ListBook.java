
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class ListBook extends JPanel {

    private DefaultTableModel tableModel;
    private LibraryManager manager;

    public ListBook() {
        manager = new LibraryManager();
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Title");
        tableModel.addColumn("Author");
        tableModel.addColumn("Genre");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("Publication Year");
        tableModel.addColumn("Copies Available");

        JTable bookTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(bookTable);

        add(scrollPane, BorderLayout.CENTER);

        List<Book> books = manager.getBooks();
        for (Book book : books) {
            Vector<Object> row = new Vector<>();
            row.add(book.getId());
            row.add(book.getTitle());
            row.add(book.getAuthor());
            row.add(book.getGenre().name());
            row.add(book.getISBN());
            row.add(book.getPublicationYear());
            row.add(book.getNumCopiesAvailable());
            tableModel.addRow(row);
        }
    }

}

