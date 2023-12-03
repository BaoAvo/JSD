import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class NewBook extends JPanel implements ActionListener {
    private JPanel northBookGui, centrePanelContainGroupLayout;
    private JLabel titleBookHeader;
    private JButton save;
    private JLabel lbTitle, lbAuthor,lbGenre,lbPublicationYear,lbNumberOfCopiesAvailable;
    private JTextField txtTitle, txtAuthor,txtPublicationYear,txtNumberOfCopiesAvailable;

    private Constants constants = new Constants();

    private LibraryManager libraryManager;
    private JComboBox<String> txtGenre;
    public NewBook(){
        createBookGUI();
    }
    private void createBookGUI() {
        libraryManager = new LibraryManager();
        setLayout(new BorderLayout());

        // the panels
        northBookGui = new JPanel();
        titleBookHeader = new JLabel("Add New Book");
        northBookGui.add(titleBookHeader);
        northBookGui.setBackground(Color.YELLOW);
        northBookGui.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        northBookGui.setFont(new Font("Arial", Font.BOLD, 18));
        add(northBookGui, BorderLayout.NORTH);

        // Centre panel
        lbTitle = new JLabel("Title:");
        txtTitle = createSizeInput();

        lbAuthor = new JLabel("Author:");
        txtAuthor = createSizeInput();

        lbGenre = new JLabel("Genre:");
        String[] genreBox = Arrays.stream(Genre.values())
                .map(Enum::name)
                .toArray(String[]::new);
        txtGenre = new JComboBox<>(genreBox);

        lbPublicationYear = new JLabel("Publication Year:");
        txtPublicationYear = createSizeInput();

        lbNumberOfCopiesAvailable = new JLabel("Number of Copies Available:");
        txtNumberOfCopiesAvailable = createSizeInput();

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
                        .addComponent(lbTitle)
                        .addComponent(lbAuthor)
                        .addComponent(lbGenre)
                        .addComponent(lbPublicationYear)
                        .addComponent(lbNumberOfCopiesAvailable)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup()
                        .addComponent(txtTitle)
                        .addComponent(txtAuthor)
                        .addComponent(txtGenre)
                        .addComponent(txtPublicationYear)
                        .addComponent(txtNumberOfCopiesAvailable)
                        .addComponent(save)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(save, GroupLayout.Alignment.CENTER)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTitle)
                        .addComponent(txtTitle)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbAuthor)
                        .addComponent(txtAuthor)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbGenre)
                        .addComponent(txtGenre)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPublicationYear)
                        .addComponent(txtPublicationYear)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNumberOfCopiesAvailable)
                        .addComponent(txtNumberOfCopiesAvailable)
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
            String newTitle = txtTitle.getText().trim();
            String newAuthor = txtAuthor.getText().trim();
            String newGenre = (String) txtGenre.getSelectedItem();
            String newPublicYear = txtPublicationYear.getText().trim();
            String newNumCopiesAvailableString = txtNumberOfCopiesAvailable.getText().trim();
            int newNumCopiesAvailable = 0;
            try{
                if(newTitle.isEmpty() || newAuthor.isEmpty() || newPublicYear.isEmpty() || newNumCopiesAvailableString.isEmpty()){
                    JOptionPane.showConfirmDialog(null,constants.MESSAGE_EMPTY_FIELD_FAIL,"Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Integer.parseInt(newPublicYear);
                newNumCopiesAvailable = Integer.parseInt(newNumCopiesAvailableString);
            }catch (NumberFormatException numberFormatException){
                JOptionPane.showConfirmDialog(null,constants.MESSAGE_ADD_BOOK_FAIL,"Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            libraryManager.addBook(new Book(newTitle,newAuthor,Genre.valueOf(newGenre),newPublicYear,newNumCopiesAvailable));
            JOptionPane.showConfirmDialog(null,constants.MESSAGE_ADD_BOOK_SUCCESS, "Information", JOptionPane.INFORMATION_MESSAGE);
            resetFieldsInput();
        }
    }

    private void resetFieldsInput(){
        txtTitle.setText("");
        txtAuthor.setText("");
        txtGenre.setSelectedIndex(0);
        txtPublicationYear.setText("");
        txtNumberOfCopiesAvailable.setText("");
    }
}
