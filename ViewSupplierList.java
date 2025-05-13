package managementsystem;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewSupplierList extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTitle = new JLabel("Supplier List");
    private JButton btnBack = new JButton("Back");
    private ArrayList<Supplier> supplierList;
    
    public ViewSupplierList() {
        super("OWSB: View Supplier List");
        
        supplierList = new ArrayList<>();
        populateSupplierList();
        addSuppliersToTable();
        makeFrame();
        showFrame();
    }
    
    public void makeFrame() {
        setLayout(new BorderLayout());
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 102, 153));
        titlePanel.setBackground(new Color(204, 229, 255));
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);
        
        //table
        tableModel = new DefaultTableModel(new Object[]{
            "Supplier ID",
            "Supplier Name",
            "Contact Number",
            "Address"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        //Back Button
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
    
    public void populateSupplierList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Suppliers.dat"))) {
            while (true) {
                try {
                    supplierList.add((Supplier) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading suppliers: " + e.getMessage());
        }
    }
    
    public void addSuppliersToTable() {
        if (supplierList == null) return;
        for (Supplier supplier : supplierList) {
            tableModel.addRow(new Object[]{
                supplier.getSupplierId(),
                supplier.getSupplierName(),
                supplier.getContactNum(),
                supplier.getAddress()
            });
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseManagerMenu().setVisible(true);
            }
        });
    }
}
