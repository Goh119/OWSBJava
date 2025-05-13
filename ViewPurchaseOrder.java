package managementsystem;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewPurchaseOrder extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTitle = new JLabel("Purchase Order List");
    private JButton btnBack = new JButton("Back");
    private ArrayList<PurchaseOrder> orderList;
    
    public ViewPurchaseOrder() {
        super("OWSB: View Purchase Orders");
        
        orderList = new ArrayList<>();
        makeFrame();
        populateOrderList();
        addOrdersToTable();
        showFrame();
    }
    
    public void makeFrame() {
        setLayout(new BorderLayout());
        
        //Title
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 102, 153));
        titlePanel.setBackground(new Color(204, 229, 255));
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);
        
        //Table
        tableModel = new DefaultTableModel(new Object[]{
            "Order ID",
            "Supplier Name",
            "Item Name",
            "Quantity Ordered",
            "Unit Price", 
            "Total Price"
        },0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        //Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBack.addActionListener(e -> dispose());
        bottomPanel.add(btnBack);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    public void showFrame() {
        setSize(1080, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    
    public void populateOrderList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PurchaseOrder.dat"))) {
            while (true) {
                try {
                   orderList.add((PurchaseOrder) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading purchase order: " + e.getMessage());
        }
    }
    
    public void addOrdersToTable() {
        if (orderList == null) return;
        for (PurchaseOrder po : orderList) {
            tableModel.addRow(new Object[]{
                po.getOrderId(),
                po.getSupplierName(),
                po.getItemName(),
                po.getQuantityOrdered(),
                String.format("%.2f", po.getUnitPrice()),
                String.format("%.2f", po.getTotalPrice())
            });
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPurchaseOrder().setVisible(true);
            }
        });
    }
}
