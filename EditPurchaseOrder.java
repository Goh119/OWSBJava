package managementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class EditPurchaseOrder extends JFrame implements ActionListener {
    ArrayList<PurchaseOrder> orderList;
    DecimalFormat formatter;
    
    JLabel lblTitle = new JLabel("Edit Purchase Order");
    JLabel lblSelect = new JLabel("Select Purchase Order: ");
    JLabel lblOrderId = new JLabel("Edit Order ID: ");
    JLabel lblItemName = new JLabel("Edit Item Name: ");
    JLabel lblQuantity = new JLabel("Edit Quantity: ");
    JLabel lblSupplier = new JLabel("Edit Supplier: ");
    JLabel lblUnitPrice = new JLabel("Edit Unit Price: ");
    
    JComboBox<String> boxList = new JComboBox<>();
    
    JTextField txtOrderId = new JTextField(15);
    JTextField txtItemName = new JTextField(20);
    JTextField txtQuantity = new JTextField(5);
    JTextField txtSupplier = new JTextField(20);
    JTextField txtUnitPrice = new JTextField(10);
    
    JButton btnSave = new JButton("Save");
    
    
    public EditPurchaseOrder() {
        super("OWSB: Edit Purchase Order");
        
        formatter = new DecimalFormat("#, ###.00");
        orderList = new ArrayList<>();
        
        makeFrame();
        showFrame();
        populateArrayList();
        updateComboBox();

        boxList.addActionListener(this);
        btnSave.addActionListener(this);
    }
    
    public void makeFrame() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(220, 240, 230));
        titlePanel.add(lblTitle);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 102, 102));
        add(titlePanel, BorderLayout.NORTH);
        
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.add(lblSelect);
        formPanel.add(boxList);
        formPanel.add(lblOrderId);
        txtOrderId.setEditable(false); //to let Order ID cannot be editable
        formPanel.add(txtOrderId);
        formPanel.add(lblItemName);
        formPanel.add(txtItemName);
        formPanel.add(lblQuantity);
        formPanel.add(txtQuantity);
        formPanel.add(lblSupplier);
        formPanel.add(txtSupplier);
        formPanel.add(lblUnitPrice);
        formPanel.add(txtUnitPrice); 
        add(formPanel, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 20));
        btnPanel.add(btnSave);
        add(btnPanel, BorderLayout.SOUTH);
    }
    
    public void showFrame() {
        setSize(600, 400);
        //setSize(1080, 900);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boxList) {
            int index = boxList.getSelectedIndex();
            if(index >= 0) {
                PurchaseOrder selectedPO = orderList.get(index);
                txtOrderId.setText(selectedPO.getOrderId());
                txtItemName.setText(selectedPO.getItemName());
                txtQuantity.setText(String.valueOf(selectedPO.getQuantityOrdered()));
                txtSupplier.setText(selectedPO.getSupplierName());
                txtUnitPrice.setText(String.valueOf(selectedPO.getUnitPrice()));
            }
        } else if (e.getSource() == btnSave) {
            int index = boxList.getSelectedIndex();
            if (index >= 0) {
                PurchaseOrder selectedPO = orderList.get(index);
                selectedPO.setOrderId(txtOrderId.getText());
                selectedPO.setItemName(txtItemName.getText());
                
                //Validation for number of quantity must not lower than 0.
                try {
                    int quantity = Integer.parseInt(txtQuantity.getText());
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.");
                        return;
                    }
                    selectedPO.setQuantityOrdered(quantity);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity entered.");
                    return;
                }
                
                //Validation for unit price must not lower than 0.
                try {
                    double price = Double.parseDouble(txtUnitPrice.getText());
                    if (price < 0) {
                        JOptionPane.showMessageDialog(this, "Unit price must be zero or positive.");
                        return;
                    }
                    selectedPO.setUnitPrice(price);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid unit price entered.");
                    return;
                }
                
                selectedPO.setSupplierName(txtSupplier.getText()); 
                
                saveOrdersToFile();
                JOptionPane.showMessageDialog(this, "Purchase order updated successfully!");
                this.dispose();
            }
        }
    }
    
    public void populateArrayList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PurchaseOrder.dat"))) {
            boolean end = false;
            while (!end) {
                try {
                    orderList.add((PurchaseOrder)ois.readObject());
                } catch (EOFException e) {
                    end = true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            
            ois.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading purchase orders: " + e.getMessage());
        }
    }
    
    public void updateComboBox() {
        boxList.removeAllItems();
        for (PurchaseOrder po : orderList) {
            boxList.addItem(po.getOrderId() + " - " + po.getItemName());
        }
    }
    
    public void saveOrdersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PurchaseOrder.dat"))) {
            for (PurchaseOrder po : orderList) {
                oos.writeObject(po);
            }
            oos.close();
        } catch(IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving purchase orders: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditPurchaseOrder().setVisible(true);
            }
        });
    }
}
