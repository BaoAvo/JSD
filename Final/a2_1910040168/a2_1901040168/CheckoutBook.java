import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckoutBook extends JPanel implements ActionListener {
    private LibraryManager libraryManager;

    private JLabel titleBookHeader;
    private JButton checkoutBtn, confirmBtn;
    private JLabel lbPatron, lbBook, lbCheckoutDate, lbDueDate;
    private JTextField txtCheckoutDate, txtDueDate;

    private Constants constants = new Constants();
    private JComboBox<String> txtPatron, txtBook;
    private List<Patron> patronList;
    private List<Book> bookList;

    private JDialog checkoutDialog;
    private LibraryTransaction newTransaction;

    public CheckoutBook() {
        createCheckoutBookGui();
    }

    public void createCheckoutBookGui() {
        libraryManager = new LibraryManager();
        patronList = new ArrayList<>();
        bookList = new ArrayList<>();
        setLayout(new BorderLayout());
        // the panels
        JPanel northCheckOutGui = new JPanel();
        titleBookHeader = new JLabel("Checkout Book");
        northCheckOutGui.add(titleBookHeader);
        northCheckOutGui.setBackground(Color.YELLOW);
        northCheckOutGui.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        northCheckOutGui.setFont(new Font("Arial", Font.BOLD, 18));
        add(northCheckOutGui, BorderLayout.NORTH);

        // Centre panel
        lbPatron = new JLabel("Patron:");
        patronList = libraryManager.getPatrons();
        txtPatron = new JComboBox<>(patronList.stream()
                .map(Patron::getName)
                .toArray(String[]::new));

        lbBook = new JLabel("Book:");
        bookList = libraryManager.getBooks();
        txtBook = new JComboBox<>(bookList.stream()
                .map(Book::getTitle)
                .toArray(String[]::new));

        lbCheckoutDate = new JLabel("Checkout Date:");
        txtCheckoutDate = createSizeInput();
        txtCheckoutDate.setText(getFormatDateToCheckout(new Date()));
        txtCheckoutDate.setEnabled(false);

        lbDueDate = new JLabel("Due Date(DD/MM/YYYY):");
        txtDueDate = createSizeInput();

        checkoutBtn = new JButton("Checkout");
        checkoutBtn.setBackground(new Color(0, 153, 255));
        checkoutBtn.addActionListener(this);

        JPanel centrePanelContainGroupLayout = new JPanel();
        GroupLayout layout = new GroupLayout(centrePanelContainGroupLayout);
        centrePanelContainGroupLayout.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lbPatron)
                        .addComponent(lbBook)
                        .addComponent(lbCheckoutDate)
                        .addComponent(lbDueDate)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup()
                        .addComponent(txtPatron)
                        .addComponent(txtBook)
                        .addComponent(txtCheckoutDate)
                        .addComponent(txtDueDate)
                        .addComponent(checkoutBtn)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(checkoutBtn, GroupLayout.Alignment.CENTER)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPatron)
                        .addComponent(txtPatron)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbBook)
                        .addComponent(txtBook)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbCheckoutDate)
                        .addComponent(txtCheckoutDate)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDueDate)
                        .addComponent(txtDueDate)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(checkoutBtn)
                )
        );
        add(centrePanelContainGroupLayout, BorderLayout.CENTER);
    }

    private static JTextField createSizeInput() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 30));
        return textField;
    }

    private String getFormatDateToCheckout(Date date) {
        return new SimpleDateFormat(constants.PATTERN_DATE_FORMAT).format(date);
    }

    private Patron getPatronByName(String name, List<Patron> patronList) {
        return patronList.stream()
                .filter(patron -> patron.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    private Book getBookByTitle(String title, List<Book> bookList) {
        return bookList.stream()
                .filter(book -> book.getTitle().equals(title))
                .findFirst()
                .orElse(null);
    }

    private void showConfirmCheckoutDialog(LibraryTransaction transaction) {
        checkoutDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Checkout Confirmation", true);
        checkoutDialog.setLayout(new BorderLayout());
        JTextArea txtConfirmDetail = new JTextArea(showConfirmDetail(transaction));
        txtConfirmDetail.setEditable(false);
        checkoutDialog.add(txtConfirmDetail, BorderLayout.CENTER);
        confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(this);
        checkoutDialog.add(confirmBtn, BorderLayout.SOUTH);
        checkoutDialog.pack();
        checkoutDialog.setLocationRelativeTo(null);
        checkoutDialog.setVisible(true);
    }

    private Date parseDate(String date) {
        String[] dateSplit = date.split("/");
        return new Date(Integer.parseInt(dateSplit[2]) - 1900, Integer.parseInt(dateSplit[1]) - 1, Integer.parseInt(dateSplit[0]));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Checkout")) {
            String newSelectedPatron = (String) txtPatron.getSelectedItem();
            String newSelectedBook = (String) txtBook.getSelectedItem();
            String checkoutDate = getFormatDateToCheckout(new Date());
            String newDueDateToReturnBook = txtDueDate.getText().trim();
            if (newDueDateToReturnBook.isEmpty()) {
                JOptionPane.showConfirmDialog(null, constants.MESSAGE_EMPTY_FIELD_FAIL, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!newDueDateToReturnBook.matches(constants.DATE_PATTERN_VALIDATE)) {
                JOptionPane.showConfirmDialog(null, constants.MESSAGE_DATE_VALIDATE, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Patron selectedPatron = getPatronByName(newSelectedPatron, patronList);
            Book selectedBook = getBookByTitle(newSelectedBook, bookList);
            int checkoutLimit = 0;
            if (selectedPatron.getPatronType().equals(PatronType.REGULAR)) {
                checkoutLimit = 3;
            } else if (selectedPatron.getPatronType().equals(PatronType.PREMIUM)) {
                checkoutLimit = 5;
            } else {
                checkoutLimit = 0;
            }
            int booksCheckedOut = libraryManager.getCheckedOutBooks(selectedPatron).size();

            if (booksCheckedOut < checkoutLimit) {
                if (selectedBook.getNumCopiesAvailable() > 0) {
                    newTransaction = new LibraryTransaction(selectedPatron, selectedBook, parseDate(checkoutDate), parseDate(newDueDateToReturnBook));
                    showConfirmCheckoutDialog(newTransaction);
                } else {
                    JOptionPane.showConfirmDialog(null, constants.MESSAGE_BOOK_OUT_OF_STOCK, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                JOptionPane.showConfirmDialog(null, constants.MESSAGE_EXCEEDED_CHECKOUT, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (e.getActionCommand().equals("Confirm")) {
            libraryManager.addTransaction(newTransaction);
            int newNumberOfCopiesAvailable = newTransaction.getBookLib().getNumberOfCopiesAvailable() - 1;
            newTransaction.getBookLib().setNumberOfCopiesAvailable(newNumberOfCopiesAvailable);
            libraryManager.updateBook(newTransaction.getBookLib());
            checkoutDialog.dispose();
            JOptionPane.showConfirmDialog(null, constants.MESSAGE_ADD_TRANSACTION_SUCCESS, "Information", JOptionPane.INFORMATION_MESSAGE);
            resetFieldsInput();
        }
    }

    private void resetFieldsInput() {
        txtDueDate.setText("");
        txtBook.setSelectedIndex(0);
        txtPatron.setSelectedIndex(0);

    }

    public String showConfirmDetail(LibraryTransaction transaction) {
        return String.format("Transaction detail: \nPatron Name: %s\nBook Title: %s\nCheckout Date: %s\nDue Date: %s", transaction.getPatronLib().getName(),
                transaction.getBookLib().getTitle(), getFormatDateToCheckout(transaction.getCheckoutDate()), getFormatDateToCheckout(transaction.getDueDate()));
    }
}
