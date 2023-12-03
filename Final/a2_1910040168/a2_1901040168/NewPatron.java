import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPatron extends JPanel implements ActionListener {
    private JPanel northPatronGui, centrePanelContainGroupLayout;
    private JLabel title;
    private JButton save;
    private JLabel lbName, lbDob,lbEmail,lbPhone,lbPatronType;
    private JTextField txtName, txtDob,txtEmail,txtPhone;

    private JComboBox<String> txtPatronType;
    private LibraryManager libraryManager;
    private Constants constants = new Constants();


    public NewPatron(){
        createPatronGUI();
    }

    private void createPatronGUI() {
        libraryManager = new LibraryManager();
        setLayout(new BorderLayout());

        // the panels
        northPatronGui = new JPanel();
        title = new JLabel("Add New Patron");
        northPatronGui.add(title);
        northPatronGui.setBackground(Color.YELLOW);
        northPatronGui.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        northPatronGui.setFont(new Font("Arial", Font.BOLD, 18));
        add(northPatronGui, BorderLayout.NORTH);

        // Centre panel
        lbName = new JLabel("Name:");
        txtName = createSizeInput();

        lbDob = new JLabel("DOB (DD/MM/YYYY):");
        txtDob = createSizeInput();

        lbEmail = new JLabel("Email:");
        txtEmail = createSizeInput();

        lbPhone = new JLabel("Phone:");
        txtPhone = createSizeInput();

        lbPatronType = new JLabel("Patron Type:");
        txtPatronType = new JComboBox<>(new String[]{"REGULAR", "PREMIUM"});

        save = new JButton("Save");
        save.setBackground(new Color(0,153,255));
        save.addActionListener(this);

        centrePanelContainGroupLayout = new JPanel();
        GroupLayout layout = new GroupLayout(centrePanelContainGroupLayout);
        centrePanelContainGroupLayout.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(lbName)
                        .addComponent(lbDob)
                        .addComponent(lbEmail)
                        .addComponent(lbPhone)
                        .addComponent(lbPatronType)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup()
                        .addComponent(txtName)
                        .addComponent(txtDob)
                        .addComponent(txtEmail)
                        .addComponent(txtPhone)
                        .addComponent(txtPatronType)
                        .addComponent(save)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(save, GroupLayout.Alignment.CENTER)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbName)
                        .addComponent(txtName)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDob)
                        .addComponent(txtDob)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbEmail)
                        .addComponent(txtEmail)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPhone)
                        .addComponent(txtPhone)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPatronType)
                        .addComponent(txtPatronType)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(save)
                )
        );
        add(centrePanelContainGroupLayout, BorderLayout.CENTER);
    }

    private static JTextField createSizeInput() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 30));
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Save")){
            String newName = txtName.getText().trim();
            String newDob = txtDob.getText().trim();
            String newEmail = txtEmail.getText().trim();
            String newPhone = txtPhone.getText().trim();
            String newPatronType = (String) txtPatronType.getSelectedItem();
            if(newName.isEmpty() || newDob.isEmpty() || newEmail.isEmpty() || newPhone.isEmpty()){
                JOptionPane.showConfirmDialog(null,constants.MESSAGE_EMPTY_FIELD_FAIL,"Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!newDob.matches(constants.DATE_PATTERN_VALIDATE)){
                JOptionPane.showConfirmDialog(null,constants.MESSAGE_DATE_VALIDATE,"Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!newEmail.matches(constants.EMAIL_PATTERN_VALIDATE)){
                JOptionPane.showConfirmDialog(null,constants.MESSAGE_EMAIL_VALIDATE,"Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!newPhone.matches(constants.PHONE_REGEX_VALIDATE)){
                JOptionPane.showConfirmDialog(null,constants.MESSAGE_PHONE_VALIDATE,"Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            libraryManager.addPatron(new Patron(newName,newDob,newEmail,newPhone,PatronType.valueOf(newPatronType)));
            JOptionPane.showConfirmDialog(null,constants.MESSAGE_ADD_PATRON_SUCCESS, "Information", JOptionPane.INFORMATION_MESSAGE);
            resetFieldsInput();
        }
    }

    private void resetFieldsInput(){
        txtName.setText("");
        txtDob.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtPatronType.setSelectedIndex(0);
    }
}
