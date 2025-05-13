package managementsystem;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ViewItemList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTitle = new JLabel("Item List");
    private JButton btnBack = new JButton("Back");
    private ArrayList<Item> itemList;
    
    public ViewItemList() {
        super("OWSB: View Item List");
        
        itemList = new ArrayList<>();
        populateItemList();
        addItemsToTable();
        makeFrame();
        showFrame();
    }
    
    public void makeFrame() {
        setLayout(new BorderLayout());
        
        //Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 102, 153));
        titlePanel.setBackground(new Color(204, 229, 255));
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);
        
        //Table
        tableModel = new DefaultTableModel(new Object[] {
            "Item ID",
            "Item Name",
            "Quantity",
            "Unit Price"
        }, 0);
        
        table = new JTable(tableModel);
        //loadItemsFromFile();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        //Back button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBack.addActionListener(e -> dispose());
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    public void showFrame() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    public void populateItemList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Items.dat"))) {
            while (true) {
                try {
                    itemList.add((Item) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading items: " + e.getMessage());
        }
    }
    
    public void addItemsToTable() {
        if (itemList == null) return;
        for (Item item : itemList) {
            tableModel.addRow(new Object[]{
                item.getItemId(),
                item.getItemName(),
                item.getQuantity(),
                item.getUnitPrice()
            });
        }
    }
    
//    private void loadItemsFromFile() {
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Items.dat"))) {
//            while (true) {
//                try {
//                    Item item = (Item) ois.readObject();
//                    tableModel.addRow(new Object[]{
//                        item.getItemId(),
//                        item.getItemName(),
//                        item.getQuantity(),
//                        item.getUnitPrice()
//                    });
//                } catch (EOFException e) {
//                    break; // End of file
//                }
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            JOptionPane.showMessageDialog(this, "Error loading items: " + e.getMessage());
//        }
//    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewItemList().setVisible(true);
            }
        });
    }
}
