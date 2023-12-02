import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPatron extends JPanel {
    private LibraryManager manager;

    public NewPatron() {
        manager = new LibraryManager();
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        add(nameField);

        add(new JLabel("DOB (DD/MM/YYYY):"));
        JTextField dobField = new JTextField();
        add(dobField);

        add(new JLabel("Email:"));
        JTextField emailField = new JTextField();
        add(emailField);

        add(new JLabel("Phone:"));
        JTextField phoneField = new JTextField();
        add(phoneField);

        add(new JLabel("Patron Type:"));
        String[] patronTypes = {"REGULAR", "PREMIUM"};
        JComboBox<String> patronTypeComboBox = new JComboBox<>(patronTypes);
        add(patronTypeComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String dob = dobField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                String patronType = (String) patronTypeComboBox.getSelectedItem();

                if (!dob.matches("\\d{2}/\\d{2}/\\d{4}")) {
                    JOptionPane.showMessageDialog(null, "Invalid dob format. Please use DD/MM/YYYY.");
                    return;
                }

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter all fields.");
                    return;
                }

                Patron patron = new Patron(name, dob, email, phone, PatronType.valueOf(patronType));
                manager.addPatron(patron);

                System.out.println("Name: " + name);
                System.out.println("DOB: " + dob);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println("Patron Type: " + patronType);

                nameField.setText("");
                dobField.setText("");
                emailField.setText("");
                phoneField.setText("");
                patronTypeComboBox.setSelectedIndex(0);
            }
        });
        add(submitButton);
    }


}
