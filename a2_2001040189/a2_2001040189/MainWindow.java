import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JPanel currentPanel;
    public MainWindow () {
        setTitle("LibMan2 Application");

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        JMenu patronMenu = new JMenu("Patron");
        JMenuItem newPatronMenuItem = new JMenuItem("New patron");
        JMenuItem listPatronsMenuItem = new JMenuItem("List patrons");
        newPatronMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPanel(new NewPatron());
            }
        });
        listPatronsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPanel(new ListPatron());
            }
        });
        patronMenu.add(newPatronMenuItem);
        patronMenu.add(listPatronsMenuItem);
        menuBar.add(patronMenu);

        JMenu bookMenu = new JMenu("Book");
        JMenuItem newBookMenuItem = new JMenuItem("New book");
        JMenuItem listBooksMenuItem = new JMenuItem("List books");
        newBookMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPanel(new NewBook());
            }
        });
        listBooksMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPanel(new ListBook());
            }
        });
        bookMenu.add(newBookMenuItem);
        bookMenu.add(listBooksMenuItem);
        menuBar.add(bookMenu);

        JMenu transactionMenu = new JMenu("Transaction");
        JMenuItem checkoutBookMenuItem = new JMenuItem("Checkout book");
        JMenuItem transactionReportMenuItem = new JMenuItem("Transaction report");
        JMenuItem returnBookMenuItem = new JMenuItem("Return book");
        checkoutBookMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPanel(new CheckoutBook());
            }
        });
        transactionReportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPanel(new TransactionReport());
            }
        });
        returnBookMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentPanel(new ReturnBook());
            }
        });
        transactionMenu.add(checkoutBookMenuItem);
        transactionMenu.add(transactionReportMenuItem);
        transactionMenu.add(returnBookMenuItem);
        menuBar.add(transactionMenu);

        setJMenuBar(menuBar);

        setVisible(true);
    }


    private void setCurrentPanel(JPanel newPanel) {
        if (currentPanel != null) {
            getContentPane().remove(currentPanel);
        }

        currentPanel = newPanel;

        getContentPane().add(currentPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

}

