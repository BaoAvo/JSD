import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewBook extends JPanel {
    private LibraryManager libraryManager;

    public NewBook() {
        libraryManager = new LibraryManager();

        setLayout(new GridLayout(6, 2));

        add(new JLabel("Title:"));
        JTextField titleField = new JTextField();
        add(titleField);

        add(new JLabel("Author:"));
        JTextField authorField = new JTextField();
        add(authorField);

        add(new JLabel("Genre:"));
        Genre[] genreValues = Genre.values();
        String[] genres = new String[genreValues.length];

        for (int i = 0; i < genreValues.length; i++) {
            genres[i] = genreValues[i].name();
        }
        JComboBox<String> genreComboBox = new JComboBox<>(genres);
        add(genreComboBox);

        add(new JLabel("Publication Year:"));
        JTextField publicationYearField = new JTextField();
        add(publicationYearField);

        add(new JLabel("Number of Copies Available:"));
        JTextField copiesField = new JTextField();
        add(copiesField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String author = authorField.getText();
                String genre = (String) genreComboBox.getSelectedItem();
                String publicationYear = publicationYearField.getText();
                String copiesAvailable = copiesField.getText();

                try {
                    int year = Integer.parseInt(publicationYear);
                    int copies = Integer.parseInt(copiesAvailable);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                    return;
                }

                if (title.isEmpty() || author.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter all fields.");
                    return;
                }

                Book book = new Book(title, author, Genre.valueOf(genre), publicationYear, Integer.parseInt(copiesAvailable));
                libraryManager.addBook(book);
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Genre: " + genre);
                System.out.println("Publication Year: " + publicationYear);
                System.out.println("Copies Available: " + copiesAvailable);

                titleField.setText("");
                authorField.setText("");
                genreComboBox.setSelectedIndex(0);
                publicationYearField.setText("");
                copiesField.setText("");
            }
        });
        add(submitButton);
    }
}
