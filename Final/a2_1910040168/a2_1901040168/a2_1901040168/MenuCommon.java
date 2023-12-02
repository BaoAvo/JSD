package a2_1901040168;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class MenuCommon extends WindowAdapter implements ActionListener {
    private JMenu fileMenu, patronMenu, bookMenu, transactionMenu;
    private JMenuItem exitMenuItem, newPatronMenuItem, listPatronsMenuItem, newBookMenuItem, listBooksMenuItem,
            checkoutBookMenuItem, transactionReportMenuItem, returnBookMenuItem;

    public MenuCommon(){
        createMenuBar();
    }
    public JMenuBar createMenuBar(){
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

        return menuBar;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd){
            case "Exit":
                shutDown();
                break;
            case "New Patron":
            case "List Patrons":
            case "New Book":
            case "List Books":
            case "Checkout Book":
            case "Transaction Report":
            case "Return Book":
            default:
        }
    }

    private void shutDown() {
        System.exit(0);
    }
}
