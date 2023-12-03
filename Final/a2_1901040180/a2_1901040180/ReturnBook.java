import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ReturnBook extends JPanel {

    private JComboBox<String> patronComboBox;
    private JComboBox<String> bookComboBox;
    private JTextField returnDateField;
    private LibraryManager manager;

    public ReturnBook() {
        manager = new LibraryManager();
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Select Patron:"));
        List<Patron> patronList = manager.getPatrons();
        String[] patrons = new String[patronList.size()];
        for (int i = 0; i < patronList.size(); i++) {
            patrons[i] = patronList.get(i).getName();
        }
        patronComboBox = new JComboBox<>(patrons);
        patronComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBookComboBox((String) patronComboBox.getSelectedItem());
            }
        });
        add(patronComboBox);

        add(new JLabel("Select Book:"));
        bookComboBox = new JComboBox<>();
        add(bookComboBox);

        add(new JLabel("Return Date (DD/MM/YYYY):"));
        returnDateField = new JTextField();
        add(returnDateField);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performReturn((String) patronComboBox.getSelectedItem(), (String) bookComboBox.getSelectedItem(), returnDateField.getText());
            }
        });
        add(returnButton);
    }

    private void updateBookComboBox(String selectedPatron) {
        bookComboBox.removeAllItems();

        loadSampleBooks(selectedPatron);
    }

    private void loadSampleBooks(String selectedPatron) {
        List<LibraryTransaction> transactionList = this.manager.getCheckedOutBooks(null);
        for (LibraryTransaction book : transactionList) {
           if (book.getPatron().getName().equals(selectedPatron)) bookComboBox.addItem(book.getBook().getTitle());
        }
    }

    private void performReturn(String patron, String book, String returnDate) {
        JOptionPane.showMessageDialog(this, "Book returned:\nPatron: " + patron + "\nBook: " + book + "\nReturn Date: " + returnDate);
        manager.removeTransaction(patron,book);
        patronComboBox.setSelectedIndex(0);
        bookComboBox.removeAllItems();
        returnDateField.setText("");
    }

}

