import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;


public class ListPatrons extends JPanel {
    private DefaultTableModel tablePatronList;

    public ListPatrons(){
        createPatronsListGUI();
    }
    private void createPatronsListGUI() {
        LibraryManager libraryManager = new LibraryManager();
        setLayout(new BorderLayout());
        // the panels
        JPanel northPatronList = new JPanel();
        northPatronList.add(new JLabel("List Patrons"));
        northPatronList.setBackground(Color.YELLOW);
        northPatronList.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        northPatronList.setFont(new Font("Arial", Font.BOLD, 18));
        add(northPatronList, BorderLayout.NORTH);

        tablePatronList = new DefaultTableModel();
        tablePatronList.addColumn("ID");
        tablePatronList.addColumn("Name");
        tablePatronList.addColumn("DOB");
        tablePatronList.addColumn("Email");
        tablePatronList.addColumn("Phone");
        tablePatronList.addColumn("Patron Type");

        JTable bookTable = new JTable(tablePatronList);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        add(scrollPane, BorderLayout.CENTER);

        libraryManager.getPatrons().forEach(p ->{
            Vector<Object> rowPatronList = new Vector<>();
            rowPatronList.add(p.getId());
            rowPatronList.add(p.getName());
            rowPatronList.add(p.getDob());
            rowPatronList.add(p.getEmail());
            rowPatronList.add(p.getPhoneNumber());
            rowPatronList.add(p.getPatronType().toString());
            tablePatronList.addRow(rowPatronList);
        });


    }
}
