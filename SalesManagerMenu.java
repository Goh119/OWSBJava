package managementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SalesManagerMenu extends JFrame implements ActionListener {
    
    JLabel lblTitle = new JLabel("    Welcome Sales Manager!");
    JButton btnItem = new JButton("Manage Items");
    JButton btnSupplier = new JButton("Manage Suppliers");
    JButton btnSales = new JButton("Daily Sales Entry");
    JButton btnPR = new JButton("Create/View PR");
    JButton btnPO = new JButton("View Purchase Orders");
    JButton btnLogout = new JButton("Logout");
    
//    private String username;
    
    public SalesManagerMenu() { //String username
        super("Purchase Order Management System");
//        this.username = username;
        makeFrame();
        showFrame();
    }
    
    public void makeFrame() {
        setLayout(new BorderLayout());
        
        JPanel titleContainer = new JPanel(new BorderLayout());
        titleContainer.setBackground(new Color(226, 240, 217));
        titleContainer.add(lblTitle, BorderLayout.CENTER);
        add(titleContainer, BorderLayout.PAGE_START);
        
        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        centerPanel.add(btnItem);
        centerPanel.add(btnSupplier);
        centerPanel.add(btnSales);
        centerPanel.add(btnPR);
        centerPanel.add(btnPO);
        centerPanel.add(btnLogout);
        add(centerPanel, BorderLayout.CENTER);
        
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(0, 102, 51));
        
        btnItem.addActionListener(this);
        btnSupplier.addActionListener(this);
        btnSales.addActionListener(this);
        btnPR.addActionListener(this);
        btnPO.addActionListener(this);
        btnLogout.addActionListener(this);
    }
    
    public void showFrame() {
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btnItem) {
            JOptionPane.showMessageDialog(this, "Opening Item Management...");
        } else if (evt.getSource() == btnSupplier) {
            JOptionPane.showMessageDialog(this, "Opening Supplier Management...");
        } else if (evt.getSource() == btnSales) {
            JOptionPane.showMessageDialog(this, "Opening Sales Entry...");
        } else if (evt.getSource() == btnPR) {
            JOptionPane.showMessageDialog(this, "Opening Purchase Requisition...");
        } else if (evt.getSource() == btnPO) {
            JOptionPane.showMessageDialog(this, "Opening Purchase Orders...");
        } else if (evt.getSource() == btnLogout) {
            dispose();
            new LoginPage();
        } 
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesManagerMenu().setVisible(true);
            }
        });
    }
}
