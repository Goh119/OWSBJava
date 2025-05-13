package managementsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;

public class AddPurchaseOrder extends JFrame implements ActionListener {
    ArrayList<PurchaseOrder> orderList;
    DecimalFormat formatter;
    
    JLabel lblTitle = new JLabel("Add Purchase Order");
    JLabel lblOrderId = new JLabel("Order ID: ");
    JLabel lblSupplierName = new JLabel("Supplier Name: ");
    JLabel lblItemName = new JLabel("Item Name: ");
    JLabel lblQuantity = new JLabel("Quantity: ");
    JLabel lblUnitPrice = new JLabel("Unit Price (RM): ");
    
    JTextField txtOrderId = new JTextField(10);
    JTextField txtSupplierName = new JTextField(20);
    JTextField txtItemName = new JTextField(20);
    JTextField txtQuantity = new JTextField(5);
    JTextField txtUnitPrice = new JTextField(10);
    
    JButton btnSave = new JButton("Save");
    
    public AddPurchaseOrder() {
        super("OWSB: Add Purchase Order");
        
        formatter = new DecimalFormat("#,##0.00");
        orderList = new ArrayList<>();
        makeFrame();
        showFrame();
        populateOrderList();
        generateNextOrderId();
    }
    
    public void makeFrame() {
        setLayout(new BorderLayout());
        
        txtOrderId.setEditable(false); //auto generated, user cannot edit                                                                                                                   
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(226, 240, 217));
        titlePanel.add(lblTitle);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 102, 51));
        add(titlePanel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.add(lblOrderId);
        formPanel.add(txtOrderId);
        formPanel.add(lblSupplierName);
        formPanel.add(txtSupplierName);
        formPanel.add(lblItemName);
        formPanel.add(txtItemName);
        formPanel.add(lblQuantity);
        formPanel.add(txtQuantity);
        formPanel.add(lblUnitPrice);
        formPanel.add(txtUnitPrice);
        add(formPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(btnSave);
        add(bottomPanel, BorderLayout.SOUTH);
        
        btnSave.addActionListener(this);
    }
    
    public void showFrame() {
        setSize(600, 400);
        //setSize(1080, 900);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void populateOrderList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PurchaseOrder.dat"))) {
            boolean end = false;
            while (!end) {
                try {
                    orderList.add((PurchaseOrder) ois.readObject());
                } catch (EOFException e) {
                    end = true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            //
        }
        
        int maxId = 0;
        for(PurchaseOrder po : orderList) {
            String idStr = po.getOrderId().replace("PO", "");
            try {
                int idNum = Integer.parseInt(idStr);
                if (idNum > maxId) {
                    maxId = idNum;
                }
            } catch (NumberFormatException ignored) {}
        }
        PurchaseOrder.setNextId(maxId + 1);
    }
    
    public void generateNextOrderId() {
        String nextIdStr = String.format("PO%04d", PurchaseOrder.getNextId());
        txtOrderId.setText(nextIdStr);
    }
    
    public void saveOrdersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PurchaseOrder.dat"))) {
            for (PurchaseOrder po : orderList) {
                oos.writeObject(po);
            }
            
            JOptionPane.showMessageDialog(this, "Purchase order saved successfully!");
            this.dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving: " + e.getMessage());
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if(txtSupplierName.getText().isEmpty() || txtItemName.getText().isEmpty() 
                || txtQuantity.getText().isEmpty() || txtUnitPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the information");
            return;
        }
        
        try {
            String supplierName = txtSupplierName.getText().trim();
            String itemName = txtItemName.getText().trim();
            int quantity = Integer.parseInt(txtQuantity.getText().trim());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText().trim());
            
            if (quantity <= 0 || unitPrice < 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be positive and price non-negative.");
                return;
            }
            
            PurchaseOrder newOrder = new PurchaseOrder(supplierName, itemName, quantity, unitPrice);
            txtOrderId.setText(newOrder.getOrderId()); //show generated ID
            orderList.add(newOrder);
            saveOrdersToFile();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity and price.");
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddPurchaseOrder().setVisible(true);
            }
        });
    }
}
