package managementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InventoryManagerMenu extends JFrame implements ActionListener {

    JLabel lblTitle = new JLabel("    Welcome, Inventory Manager!");
    JButton btnViewItems = new JButton("View Item List");
    JButton btnUpdateItems = new JButton("Update Item Details");
    JButton btnStockIn = new JButton("Stock In Items");
    JButton btnStockOut = new JButton("Stock Out Items");
    JButton btnLogout = new JButton("Logout");

    public InventoryManagerMenu() {
        super("OWSB: Inventory Manager Menu");
        makeFrame();
        showFrame();
    }

    public void makeFrame() {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(230, 255, 230));
        titlePanel.add(lblTitle, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        centerPanel.add(btnViewItems);
        centerPanel.add(btnUpdateItems);
        centerPanel.add(btnStockIn);
        centerPanel.add(btnStockOut);
        centerPanel.add(btnLogout);
        add(centerPanel, BorderLayout.CENTER);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 153, 0));

        btnViewItems.addActionListener(this);
        btnUpdateItems.addActionListener(this);
        btnStockIn.addActionListener(this);
        btnStockOut.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    public void showFrame() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnViewItems) {
            JOptionPane.showMessageDialog(this, "View Item List...");
        } else if (evt.getSource() == btnUpdateItems) {
            JOptionPane.showMessageDialog(this, "Update Item Details...");
        } else if (evt.getSource() == btnStockIn) {
            JOptionPane.showMessageDialog(this, "Stock In Items...");
        } else if (evt.getSource() == btnStockOut) {
            JOptionPane.showMessageDialog(this, "Stock Out Items...");
        } else if (evt.getSource() == btnLogout) {
            dispose();
            new LoginPage();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryManagerMenu().setVisible(true);
            }
        });
    }
}
