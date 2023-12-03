import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPatron extends JPanel implements ActionListener {
    private final LibraryManager libraryManager;
    private final JLabel lbDob,lbEmail,lbPhone,lbPatronType;
    private final JTextField txtName, txtDob, txtEmail, txtPhone;
    private final JComboBox<String> cbPatronType;
    private final JButton addPatronBtn;
    private final MessageConstants messageConstants = new MessageConstants();
    private final Dialog dialog = new Dialog();
    private final Validator validator = new Validator();

    public NewPatron() {
        libraryManager = new LibraryManager();
        setLayout(new BorderLayout());

        JPanel northNewPatron = new JPanel();
        northNewPatron.add(new JLabel("Add New Patron"));
        add(northNewPatron, BorderLayout.NORTH);

        setLayout(new GridLayout(6, 2));
        JPanel centerNewPatron = new JPanel();
        JPanel layout = new JPanel(new GridLayout(6, 2));
        JLabel lbName = new JLabel("Name:");
        txtName = new JTextField();
        layout.add(lbName);
        layout.add(txtName);

        lbDob = new JLabel("DOB (DD/MM/YYYY):");
        txtDob = new JTextField();
        layout.add(lbDob);
        layout.add(txtDob);

        lbEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        layout.add(lbEmail);
        layout.add(txtEmail);

        lbPhone = new JLabel("Phone:");
        txtPhone = new JTextField();
        layout.add(lbPhone);
        layout.add(txtPhone);

        lbPatronType = new JLabel("Patron Type:");
        cbPatronType = new JComboBox<>(new String[]{"REGULAR", "PREMIUM"});
        layout.add(lbPatronType);
        layout.add(cbPatronType);
        centerNewPatron.add(layout, BorderLayout.CENTER);

        JPanel southNewPatron = new JPanel();
        addPatronBtn = new JButton("Add Patron");
        addPatronBtn.setBackground(Color.BLUE);
        addPatronBtn.addActionListener(this);
        southNewPatron.add(addPatronBtn, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Patron")) {
            String nameInput = txtName.getText().trim();
            String dobInput = txtDob.getText().trim();
            String emailInput = txtEmail.getText().trim();
            String phoneInput = txtPhone.getText().trim();
            String patronTypeSelected = (String) cbPatronType.getSelectedItem();
            if (!validator.validateInputPatron(nameInput, dobInput, emailInput, phoneInput)) {
                return;
            }

            if (!validator.validateDate(dobInput)) {
                dialog.showError(messageConstants.MESSAGE_VALIDATE_DOB_FAIL);
                return;
            }

            if (!validator.validateEmail(emailInput)) {
                dialog.showError(messageConstants.MESSAGE_VALIDATE_EMAIL_FAIL);
                return;
            }

            if (!validator.validatePhone(phoneInput)) {
                dialog.showError(messageConstants.MESSAGE_VALIDATE_PHONE_FAIL);
                return;
            }
            libraryManager.addPatron(new Patron(nameInput, dobInput, emailInput, phoneInput, PatronType.valueOf(patronTypeSelected)));
            dialog.showInformation(messageConstants.MESSAGE_ADD_PATRON_SUCCESS);
            clearAllInput();
        }
    }
    private void clearAllInput(){
        txtName.setText("");
        txtEmail.setText("");
        txtDob.setText("");
        txtPhone.setText("");
        cbPatronType.setSelectedIndex(0);
    }
}
