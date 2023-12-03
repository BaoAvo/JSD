import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import java.util.function.Consumer;

public class TransactionReport extends JPanel{
    private DefaultComboBoxModel<String> reportTypeComboBoxModel;
    private LibraryManager libraryManager;
    private JComboBox<String> reportTypeComboBox;
    private DefaultTableModel tableModel;
    private Constants constants = new Constants();
    private SimpleDateFormat dateFormat = new SimpleDateFormat(constants.PATTERN_DATE_FORMAT);


    public TransactionReport() {
        libraryManager = new LibraryManager();
        setLayout(new BorderLayout());

        JPanel northReportGui = new JPanel();
        northReportGui.setLayout(new FlowLayout());
        northReportGui.setBackground(Color.YELLOW);
        northReportGui.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        northReportGui.setFont(new Font("Arial", Font.BOLD, 18));
        reportTypeComboBoxModel = new DefaultComboBoxModel<>(new String[]{"All transactions", "All checked-out books", "Overdue books"});
        reportTypeComboBox = new JComboBox<>(reportTypeComboBoxModel);
        northReportGui.add(reportTypeComboBox);

        JButton getReportButton = new JButton("Get Report");
        getReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel = (DefaultTableModel) ((JTable) ((JScrollPane) getComponent(1)).getViewport().getView()).getModel();
                tableModel.setRowCount(0);
                String txtReportType = (String) reportTypeComboBox.getSelectedItem();
                List<LibraryTransaction> transactionList = null;
                Vector<Vector<Object>> data = new Vector<>();

                Consumer<LibraryTransaction> processTransaction = transaction -> {
                    Vector<Object> rowDataFollowReportType = new Vector<>();
                    rowDataFollowReportType.add(transaction.getPatronLib().getName());
                    rowDataFollowReportType.add(transaction.getBookLib().getTitle());
                    rowDataFollowReportType.add(dateFormat.format(transaction.getCheckoutDate()));
                    rowDataFollowReportType.add(dateFormat.format(transaction.getDueDate()));
                    data.add(rowDataFollowReportType);
                };

                if ("All transactions".equals(txtReportType) || "All checked-out books".equals(txtReportType)) {
                    transactionList = libraryManager.getAllCheckedOutBooks();
                } else {
                    transactionList = libraryManager.getOverdueBooks();
                }
                transactionList.forEach(processTransaction);
                data.forEach(tableModel::addRow);
            }
        });
        northReportGui.add(getReportButton);
        add(northReportGui, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Patron");
        tableModel.addColumn("Book");
        tableModel.addColumn("Checkout Date");
        tableModel.addColumn("Due Date");
        JTable reportTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reportTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}