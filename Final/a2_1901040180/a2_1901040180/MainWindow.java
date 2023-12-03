import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MainWindow extends JFrame implements ActionListener {
    private JPanel currentScreen;
    private JMenuItem exitItem, newPatronItem, listPatronsItem, newBookItem, listBooksItem, checkoutBookitem, returnBookItem, transactionReportItem;

    private Map<String, Supplier<JPanel>> panelAllScreenMap;
    public MainWindow() {
        panelAllScreenMap = initPanel();
        createMainWindowUI();
    }

    private void createMainWindowUI() {
        setTitle("Library");
        JMenuBar menuBarMainWindow = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        menuBarMainWindow.add(fileMenu);

        JMenu patronMenu = new JMenu("Patron");
        newPatronItem = new JMenuItem("New Patron");
        newPatronItem.addActionListener(this);
        patronMenu.add(newPatronItem);

        listPatronsItem = new JMenuItem("List Patrons");
        listPatronsItem.addActionListener(this);
        patronMenu.add(listPatronsItem);

        menuBarMainWindow.add(patronMenu);

        JMenu bookMenu = new JMenu("Book");
        newBookItem = new JMenuItem("New Book");
        newBookItem.addActionListener(this);
        bookMenu.add(newBookItem);

        listBooksItem = new JMenuItem("List Books");
        listBooksItem.addActionListener(this);
        bookMenu.add(listBooksItem);

        menuBarMainWindow.add(bookMenu);

        JMenu transactionMenu = new JMenu("Transaction");
        checkoutBookitem = new JMenuItem("Checkout Book");
        checkoutBookitem.addActionListener(this);
        transactionMenu.add(checkoutBookitem);

        transactionReportItem = new JMenuItem("Transaction Report");
        transactionReportItem.addActionListener(this);
        transactionMenu.add(transactionReportItem);

        returnBookItem = new JMenuItem("Return Book");
        returnBookItem.addActionListener(this);
        transactionMenu.add(returnBookItem);

        menuBarMainWindow.add(transactionMenu);
        setJMenuBar(menuBarMainWindow);

        pack();
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        JPanel selectedPanel = panelAllScreenMap.get(actionCommand);

        if ("Exit".equals(actionCommand)) {
            System.exit(0);
            dispose();
        } else if (selectedPanel != null) {
            setCurrentPanel(selectedPanel);
        }
    }
    public Map<String, JPanel> initPanel() {
        Map<String, JPanel> panelMap = new HashMap<>();
        panelMap.put("Exit", null);
        panelMap.put("New Patron", new NewPatron());
        panelMap.put("List Patrons", new ListPatrons();
        panelMap.put("New Book", new NewBook();
        panelMap.put("List Books", ListBooks::new);
        panelMap.put("Checkout Book", CheckoutBook::new);
        panelMap.put("Transaction Report", TransactionReport::new);
        panelMap.put("Return Book", ReturnBook::new);
        return panelMap;
    }
}

