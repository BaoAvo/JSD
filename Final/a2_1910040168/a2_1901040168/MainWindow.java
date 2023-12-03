import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame implements ActionListener {
    private JMenu fileMenu, patronMenu, bookMenu, transactionMenu;
    private JMenuItem exitMenuItem, newPatronMenuItem, listPatronsMenuItem, newBookMenuItem, listBooksMenuItem,
            checkoutBookMenuItem, transactionReportMenuItem, returnBookMenuItem;

    private JPanel currentScreen;
    public MainWindow(){
        createMenuBar();
    }
    public void createMenuBar(){
        setTitle("Library Management");
        //Create a menu bar
        JMenuBar menuBar = new JMenuBar();

        //Create menu headers
        fileMenu = new JMenu("File");
        patronMenu = new JMenu("Patron");
        bookMenu = new JMenu("Book");
        transactionMenu = new JMenu("Transaction");

        //Add menu items to fileMenu
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(this);
        fileMenu.add(exitMenuItem);

        //Add menu items to patronMenu
        newPatronMenuItem = new JMenuItem("New Patron");
        newPatronMenuItem.addActionListener(this);
        patronMenu.add(newPatronMenuItem);

        listPatronsMenuItem = new JMenuItem("List Patrons");
        listPatronsMenuItem.addActionListener(this);
        patronMenu.add(listPatronsMenuItem);

        //Add menu items to bookMenu
        newBookMenuItem = new JMenuItem("New Book");
        newBookMenuItem.addActionListener(this);
        bookMenu.add(newBookMenuItem);

        listBooksMenuItem = new JMenuItem("List Books");
        listBooksMenuItem.addActionListener(this);
        bookMenu.add(listBooksMenuItem);

        //Add menu items to transactionMenu
        checkoutBookMenuItem = new JMenuItem("Checkout Book");
        checkoutBookMenuItem.addActionListener(this);
        transactionMenu.add(checkoutBookMenuItem);

        transactionReportMenuItem = new JMenuItem("Transaction Report");
        transactionReportMenuItem.addActionListener(this);
        transactionMenu.add(transactionReportMenuItem);

        returnBookMenuItem = new JMenuItem("Return Book");
        returnBookMenuItem.addActionListener(this);
        transactionMenu.add(returnBookMenuItem);

        //Add headers to the menu bar
        menuBar.add(fileMenu);
        menuBar.add(patronMenu);
        menuBar.add(bookMenu);
        menuBar.add(transactionMenu);

        setJMenuBar(menuBar);

        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "Exit":
                shutDown();
                dispose();
                break;
            case "New Patron":
                setCurrentPanel(new NewPatron());
                break;
            case "List Patrons":
                setCurrentPanel(new ListPatrons());
                break;
            case "New Book":
                setCurrentPanel(new NewBook());
                break;
            case "List Books":
                setCurrentPanel(new ListBooks());
                break;
            case "Checkout Book":
                setCurrentPanel(new CheckoutBook());
                break;
            case "Transaction Report":
                setCurrentPanel(new TransactionReport());
                break;
            case "Return Book":
                setCurrentPanel(new ReturnBook());
                break;
        }
    }

    private void setCurrentPanel(JPanel newScreen) {
        if (currentScreen != null) {
            getContentPane().remove(currentScreen);
        }
        currentScreen = newScreen;
        getContentPane().add(currentScreen, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void shutDown() {
        System.exit(0);
    }
}
