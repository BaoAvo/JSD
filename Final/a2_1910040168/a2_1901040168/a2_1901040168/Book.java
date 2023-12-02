package a2_1901040168;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Arrays;
import java.util.List;

public class Book extends WindowAdapter implements ActionListener {
    private JFrame bookGui;
    private JPanel northBookGui, southBookGui, centrePanelContainGroupLayout;
    private JLabel titleBookHeader;
    private JButton save, back;
    private JLabel lbTitle, lbAuthor,lbGenre,lbPublicationYear,lbNumberOfCopiesAvailable;
    private JTextField txtTitle, txtAuthor,txtPublicationYear,txtNumberOfCopiesAvailable;

    private JComboBox<String> txtGenre;
    private String isbn;
    private String title;
    private String author;
    private Genre genre;
    private int publicationYear;
    private int numberOfCopiesAvailable;

    public Book(){}
    public Book(String title, String author, Genre genre, int publicationYear, int numberOfCopiesAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
        this.setIsbn(this.generateISBN());
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getNumberOfCopiesAvailable() {
        return numberOfCopiesAvailable;
    }

    public void setNumberOfCopiesAvailable(int numberOfCopiesAvailable) {
        this.numberOfCopiesAvailable = numberOfCopiesAvailable;
    }

    @Override
    public String toString() {
        return "Books[" +
                "isbn = '" + isbn + '\'' +
                ", title = '" + title + '\'' +
                ", author = '" + author + '\'' +
                ", genre = " + genre +
                ", publicationYear = " + publicationYear +
                ", numberOfCopiesAvailable = " + numberOfCopiesAvailable +
                ']';
    }

    public String generateISBN(){
        String genreCode = ""; 
        int genreIndex = this.getGenre().ordinal() + 1;
        if(genreIndex < 10){
            genreCode = '0' + String.valueOf(genreIndex);
        }else{
            genreCode = String.valueOf(genreIndex);
        }
        String[] words = this.getAuthor().split(" ");
        String initials = "";
        for(String word : words){
            if(!word.isEmpty()){
                initials += word.charAt(0);
            }
        }
        return initials + "-" + genreCode + "-" + this.getPublicationYear();
    }

    //  Keep track of the number of copies available
    public int getNumCopiesAvailable(){
        return this.getNumberOfCopiesAvailable();
    }

    private void createBookGUI() {
        bookGui = new JFrame("Library Management");
        bookGui.addWindowListener(this);
        //Create a menu bar
        MenuCommon menuCommon = new MenuCommon();
        JMenuBar menuBar = menuCommon.createMenuBar();
        bookGui.setJMenuBar(menuBar);

        // the panels
        northBookGui = new JPanel();
        northBookGui.setBackground(Color.YELLOW);
        northBookGui.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        northBookGui.setFont(new Font("Arial", Font.BOLD, 18));
        bookGui.add(northBookGui, BorderLayout.NORTH);
        // the title
        titleBookHeader = new JLabel("Add New Patron");
        northBookGui.add(titleBookHeader);

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

        centrePanelContainGroupLayout = new JPanel();
        GroupLayout layout = new GroupLayout(centrePanelContainGroupLayout);
        centrePanelContainGroupLayout.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Thiết lập các nhóm ngang và dọc
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
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTitle)
                        .addComponent(txtTitle)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Khoảng cách 5px
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbAuthor)
                        .addComponent(txtAuthor)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Khoảng cách 5px
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbGenre)
                        .addComponent(txtGenre)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Khoảng cách 5px
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbPublicationYear)
                        .addComponent(txtPublicationYear)
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED) // Khoảng cách 5px
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lbNumberOfCopiesAvailable)
                        .addComponent(txtNumberOfCopiesAvailable)
                )
        );

        bookGui.add(centrePanelContainGroupLayout, BorderLayout.CENTER);
        // South
        southBookGui = new JPanel();
        bookGui.add(southBookGui, BorderLayout.SOUTH);
        back = new JButton("Back");
        back.setBackground(Color.GRAY);
        back.addActionListener(this);
        southBookGui.add(back);

        save = new JButton("Save");
        save.setBackground(new Color(0,153,255));
        save.setEnabled(false);
        save.addActionListener(this);
        southBookGui.add(save);

        bookGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        bookGui.setSize(600, 350);
        bookGui.setLocationRelativeTo(null);

        if (!bookGui.isVisible())
            bookGui.setVisible(true);
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
        Book book = new Book();
        book.createBookGUI();
    }
}
