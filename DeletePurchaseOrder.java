package managementsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DeletePurchaseOrder extends JFrame implements ActionListener {
    ArrayList<PurchaseOrder> orderList;
    DecimalFormat formatter;
    
    JLabel lblTitle = new JLabel("Delete Purchase Order");
    JLabel lblOrderId = new JLabel("Select Order ID: ");
    JComboBox<String> boxList = new JComboBox<String>();
    JButton btnDelete = new JButton("Delete");
    JTable table;
    DefaultTableModel tableModel;
    
    public DeletePurchaseOrder() {
        super("OWSB: Delete Purchase Order");
        
        formatter = new DecimalFormat("#,###.00");
        orderList = new ArrayList<>();
        
        makeFrame();
        populateArrayList();
        updateDropdownAndTable();
        showFrame();
        
        /*String[] orderArray = new String[orderList.size()];
        for(int i = 0; i < orderList.size(); i++) {
            orderArray[i] = orderList.get(i).getOrderId() + " - " + orderList.get(i).getSupplierName();
        }
        boxList.setModel(new DefaultComboBoxModel<String>(orderArray));
        boxList.setSelectedIndex(0);*/
        
        if(!orderList.isEmpty()) {
            String[] orderArray = new String[orderList.size()];
            for(int i = 0; i < orderList.size(); i++) {
                orderArray[i] = orderList.get(i).getOrderId() + " - " + orderList.get(i).getSupplierName();
            }
            boxList.setModel(new DefaultComboBoxModel<String>(orderArray));
            boxList.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this, "No purchase order found.");
            btnDelete.setEnabled(false);
        }
    }
    
    public void makeFrame() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(226, 240, 217));
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);
        
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(lblOrderId);
        topPanel.add(boxList);
        add(topPanel, BorderLayout.BEFORE_FIRST_LINE);

        // Table setup
        String[] columnNames = {"Order ID", "Item Name", "Quantity", "Supplier"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setEnabled(false);  // Read-only table

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(580, 200));
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(btnDelete);
        add(btnPanel, BorderLayout.SOUTH);
        btnDelete.setForeground(Color.RED);
        
        btnDelete.addActionListener(this);
    }
    
    public void showFrame() {
        //setSize(600, 400);
        setSize(1080, 900);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void populateArrayList() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("PurchaseOrder.dat"))) {
            boolean end = false;
            while(!end) {
                try {
                    PurchaseOrder order = (PurchaseOrder) ois.readObject();
                    orderList.add(order);
                } catch (EOFException e) {
                    end = true;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error loading orders: " + e.getMessage());
        }
    }
    
    public void updateDropdownAndTable() {
        boxList.removeAllItems();
        tableModel.setRowCount(0); // Clear table

        if (!orderList.isEmpty()) {
            for (PurchaseOrder order : orderList) {
                String display = order.getOrderId() + " - " + order.getSupplierName();
                boxList.addItem(display);

                tableModel.addRow(new Object[]{
                        order.getOrderId(),
                        order.getItemName(),
                        order.getQuantityOrdered(),
                        order.getSupplierName()
                });
            }
        } else {
            JOptionPane.showMessageDialog(this, "No purchase order found.");
            btnDelete.setEnabled(false);
        }
    }
    
    public void saveOrdersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("PurchaseOrder.dat"))) {
            for (PurchaseOrder order : orderList) {
                oos.writeObject(order);
            }
            //JOptionPane.showMessageDialog(null, "Purchase order deleted successfully!");
            //this.dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving: " + e.getMessage());
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnDelete) {
            int index = boxList.getSelectedIndex();
            if(index == -1) {
                JOptionPane.showMessageDialog(null, "Please select an order to delete.");
            } else {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this purchase order?", 
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                
                if(confirm == JOptionPane.YES_OPTION) {
                    orderList.remove(index);
                    saveOrdersToFile();
                    updateDropdownAndTable();
                    JOptionPane.showMessageDialog(null, "Purchase order deleted successfully!");
                }
            }
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeletePurchaseOrder().setVisible(true);
            }
        });
    }
}
