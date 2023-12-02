package a2_1901040168;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.util.Vector;

public class ListPatrons extends WindowAdapter {
    private void createPatronGUI() {
        JFrame listPatronGui = new JFrame("Library Management");

        //Create a menu bar
        MenuCommon menuCommon = new MenuCommon();
        JMenuBar menuBar = menuCommon.createMenuBar();
        listPatronGui.setJMenuBar(menuBar);

        // the panels
        JPanel north = new JPanel();
        north.setBackground(Color.YELLOW);
        north.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        north.setFont(new Font("Arial", Font.BOLD, 18));
        listPatronGui.add(north, BorderLayout.NORTH);
        // the title
        north.add(new JLabel("List Patrons"));

        // Dữ liệu mẫu cho bảng (chỉ là ví dụ, bạn cần thay thế bằng dữ liệu thực)
        Vector<Vector<String>> data = new Vector<>();
        Vector<String> columnNames = new Vector<>();
        columnNames.add("Name");
        columnNames.add("DOB");
        columnNames.add("Email");
        columnNames.add("Phone");
        columnNames.add("Patron Type");

        // Thêm một vài dữ liệu mẫu
        Vector<String> row1 = new Vector<>();
        row1.add("John Doe");
        row1.add("01/01/1990");
        row1.add("john.doe@example.com");
        row1.add("123-456-7890");
        row1.add("Regular");

        Vector<String> row2 = new Vector<>();
        row2.add("Jane Smith");
        row2.add("05/15/1985");
        row2.add("jane.smith@example.com");
        row2.add("987-654-3210");
        row2.add("Premium");

        data.add(row1);
        data.add(row2);

        // Tạo JTable
        JTable patronTable = new JTable(data, columnNames);

        // Tạo JScrollPane để thêm khả năng cuộn
        JScrollPane scrollPane = new JScrollPane(patronTable);

        // Thêm JScrollPane vào JFrame
        listPatronGui.add(scrollPane);

        JPanel centre = new JPanel();
        centre.setPreferredSize(new Dimension(500,250));
        centre.add(patronTable);
        listPatronGui.add(centre,BorderLayout.CENTER);

        listPatronGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        listPatronGui.setSize(600, 350);
        listPatronGui.setLocationRelativeTo(null);

        if (!listPatronGui.isVisible())
            listPatronGui.setVisible(true);
    }

    public static void main(String[] args) {
        ListPatrons listPatrons = new ListPatrons();
        listPatrons.createPatronGUI();
    }
}
