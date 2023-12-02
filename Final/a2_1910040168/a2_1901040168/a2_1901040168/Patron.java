package a2_1901040168;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class Patron  extends WindowAdapter implements ActionListener {
    private JFrame patronGui;
    private JPanel north, south, centrePanelContainGroupLayout;
    private JLabel title;
    private JButton save, back;
    private JLabel lbName, lbDob,lbEmail,lbPhone,lbPatronType;
    private JTextField txtName, txtDob,txtEmail,txtPhone;

    private JComboBox<String> txtPatronType;
    private String patronId, name, dob, email, phoneNumber;
    private PatronType patronType;

    private static int index = 0;

    public Patron(){}
    public Patron(String name, String dob, String email, String phoneNumber, PatronType patronType) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.patronType = patronType;
        this.setPatronId(generatePatronID());
    }

    public String getPatronId() {
        return patronId;
    }

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }
    public PatronType getPatronType() {
        return patronType;
    }

    @Override
    public String toString() {
        return "Patrons[" +
                "patronId = '" + patronId + '\'' +
                ", name = '" + name + '\'' +
                ", dob = " + dob +
                ", email = '" + email + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", patronType = " + patronType +
                ']';
    }

    public String generatePatronID(){
        this.index++;
        return String.format("P%03d", this.index);
    }

    private void createPatronGUI() {
        patronGui = new JFrame("Library Management");
        patronGui.addWindowListener(this);
        //Create a menu bar
        MenuCommon menuCommon = new MenuCommon();
        JMenuBar menuBar = menuCommon.createMenuBar();
        patronGui.setJMenuBar(menuBar);

        // the panels
        north = new JPanel();
        north.setBackground(Color.YELLOW);
        north.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        north.setFont(new Font("Arial", Font.BOLD, 18));
        patronGui.add(north, BorderLayout.NORTH);
        // the title
        title = new JLabel("Add New Patron");
        north.add(title);

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

        centrePanelContainGroupLayout = new JPanel();
        GroupLayout layout = new GroupLayout(centrePanelContainGroupLayout);
        centrePanelContainGroupLayout.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Thiết lập các nhóm ngang và dọc
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
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbName)
                        .addComponent(txtName)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Khoảng cách 5px
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbDob)
                        .addComponent(txtDob)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Khoảng cách 5px
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbEmail)
                        .addComponent(txtEmail)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Khoảng cách 5px
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPhone)
                        .addComponent(txtPhone)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Khoảng cách 5px
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPatronType)
                        .addComponent(txtPatronType)
                )
        );

        patronGui.add(centrePanelContainGroupLayout, BorderLayout.CENTER);
        // South
        south = new JPanel();
        patronGui.add(south, BorderLayout.SOUTH);
        back = new JButton("Back");
        back.setBackground(Color.GRAY);
        back.addActionListener(this);
        south.add(back);

        save = new JButton("Save");
        save.setBackground(new Color(0,153,255));
        save.addActionListener(this);
        south.add(save);

        patronGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        patronGui.setSize(600, 350);
        patronGui.setLocationRelativeTo(null);

        if (!patronGui.isVisible())
            patronGui.setVisible(true);
    }

    private static JTextField createSizeInput() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 30));
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        Patron patron = new Patron();
        patron.createPatronGUI();
    }
}
