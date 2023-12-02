

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class ListPatron extends JPanel {

    private DefaultTableModel tableModel;
    private LibraryManager manager;

    public ListPatron() {
        manager = new LibraryManager();
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("DOB");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");
        tableModel.addColumn("Patron Type");

        JTable patronTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(patronTable);

        add(scrollPane, BorderLayout.CENTER);

        List<Patron> patrons = this.manager.getPatrons();
        for (Patron patron : patrons) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(patron.getId());
            rowData.add(patron.getName());
            rowData.add(patron.getDob());
            rowData.add(patron.getEmail());
            rowData.add(patron.getPhoneNumber());
            rowData.add(patron.getPatronType().toString());

            tableModel.addRow(rowData);
        }
    }
}
