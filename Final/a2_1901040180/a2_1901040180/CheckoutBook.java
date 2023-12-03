
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CheckoutBook extends JPanel {
    private LibraryManager manager;

    public CheckoutBook() {
        manager = new LibraryManager();
        setLayout(new GridLayout(5, 2));
        List<Patron> patronList = manager.getPatrons();
        List<Book> bookList = manager.getBooks();
        add(new JLabel("Patron:"));
        String[] patronNames = new String[patronList.size()];
        for (int i = 0; i < patronList.size(); i++) {
            patronNames[i] = patronList.get(i).getName();
        }

        JComboBox<String> patronComboBox = new JComboBox<>(patronNames);
        add(patronComboBox);

        add(new JLabel("Book:"));
        String[] bookTitles = new String[bookList.size()];
        for (int i = 0; i < bookList.size(); i++) {
            bookTitles[i] = bookList.get(i).getTitle();
        }
        JComboBox<String> bookComboBox = new JComboBox<>(bookTitles);
        add(bookComboBox);

        add(new JLabel("Checkout Date:"));
        JLabel checkoutDateLabel = new JLabel(getCurrentDate());
        add(checkoutDateLabel);

        add(new JLabel("Due Date (DD/MM/YYYY):"));
        JTextField dueDateField = new JTextField();
        add(dueDateField);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPatronName = (String) patronComboBox.getSelectedItem();
                String selectedBookTitle = (String) bookComboBox.getSelectedItem();
                String checkoutDate = getCurrentDate();
                String dueDate = dueDateField.getText();

                // Find the corresponding Patron and Book objects
                Patron selectedPatron = findPatronByName(selectedPatronName, patronList);
                Book selectedBook = findBookByTitle(selectedBookTitle, bookList);

                // Check if Patron and Book were found
                if (selectedPatron != null && selectedBook != null) {
                    // Convert date strings to Date objects
                    if (!dueDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                        JOptionPane.showMessageDialog(null, "Invalid dob format. Please use DD/MM/YYYY.");
                        return;
                    }
                    Date checkoutDateObj = parseDate(checkoutDate);
                    Date dueDateObj = parseDate(dueDate);

                    System.out.println("patron: " + selectedPatron);
                    LibraryTransaction transaction = new LibraryTransaction(selectedPatron, selectedBook, checkoutDateObj, dueDateObj);

                    showCheckoutDialog(transaction);
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Patron or Book not found.");
                }
            }
        });
        add(checkoutButton);
    }

    // Method to get the current date in the specified format
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(new Date());
    }

    // Method to find a Patron by name in the patronList
    private Patron findPatronByName(String name, List<Patron> patronList) {
        for (Patron patron : patronList) {
            if (patron.getName().equals(name)) {
                return patron;
            }
        }
        return null;
    }

    // Method to find a Book by title in the bookList
    private Book findBookByTitle(String title, List<Book> bookList) {
        for (Book book : bookList) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    // Method to show the checkout dialog
    private void showCheckoutDialog(LibraryTransaction transaction) {
        // Create a dialog
        JDialog checkoutDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Checkout Confirmation", true);
        checkoutDialog.setSize(300, 200);

        checkoutDialog.setLayout(new BorderLayout());

        // Create a JTextArea to display the transaction details
        JTextArea textArea = new JTextArea(transaction.toString());
        textArea.setEditable(false);

        // Add components to the dialog
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add the JScrollPane to the dialog
        checkoutDialog.add(scrollPane, BorderLayout.CENTER);
        // Add a button to confirm the transaction
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.addTransaction(transaction);
                checkoutDialog.dispose();
            }
        });
        checkoutDialog.add(confirmButton, BorderLayout.SOUTH);

        // Set the dialog to be visible
        checkoutDialog.setVisible(true);
    }

    private Date parseDate(String dateStr) {
        String[] parts = dateStr.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new Date(year - 1900, month - 1, day);
    }

}

