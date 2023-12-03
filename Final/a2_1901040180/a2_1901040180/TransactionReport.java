

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

public class TransactionReport extends JPanel {

    private DefaultComboBoxModel<String> reportTypeComboBoxModel;
    private LibraryManager manager;

    public TransactionReport() {
        manager = new LibraryManager();
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        reportTypeComboBoxModel = new DefaultComboBoxModel<>(new String[]{"All transactions", "All checked-out books", "Overdue books"});
        JComboBox<String> reportTypeComboBox = new JComboBox<>(reportTypeComboBoxModel);
        controlPanel.add(reportTypeComboBox);

        JButton getReportButton = new JButton("Get Report");
        getReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayReport((String) reportTypeComboBox.getSelectedItem());
            }
        });
        controlPanel.add(getReportButton);

        add(controlPanel, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Patron");
        tableModel.addColumn("Book");
        tableModel.addColumn("Checkout Date");
        tableModel.addColumn("Due Date");

        JTable reportTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(reportTable);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void displayReport(String reportType) {
        DefaultTableModel tableModel = (DefaultTableModel) ((JTable) ((JScrollPane) getComponent(1)).getViewport().getView()).getModel();
        tableModel.setRowCount(0);

        loadSampleData(reportType, tableModel);
    }

    private void loadSampleData(String reportType, DefaultTableModel tableModel) {
        List<LibraryTransaction> transactionList = null;
        Vector<Vector<Object>> data = new Vector<>();
        if (reportType.equals("All transactions") || reportType.equals("All checked-out books")) {
            transactionList = manager.getCheckedOutBooks(null);
            for (LibraryTransaction transaction : transactionList) {
                Vector<Object> row = new Vector<>();
                System.out.println("transaction: " + transaction);
                row.add(transaction.getPatron().getName());
                row.add(transaction.getBook().getTitle());

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                row.add(dateFormat.format(transaction.getCheckoutDate()));
                row.add(dateFormat.format(transaction.getDueDate()));

                data.add(row);
            }
        }

        else  {
            transactionList = manager.getOverdueBooks();
            for (LibraryTransaction transaction : transactionList) {
                Vector<Object> row = new Vector<>();
                row.add(transaction.getPatron().getName());
                row.add(transaction.getBook().getTitle());

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                row.add(dateFormat.format(transaction.getCheckoutDate()));
                row.add(dateFormat.format(transaction.getDueDate()));

                data.add(row);
            }
        }

        for (Vector<Object> rowData : data) {
            tableModel.addRow(rowData);
        }
    }
}

