package managementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminMenu extends JFrame implements ActionListener {

    JLabel lblTitle = new JLabel("    Welcome, Administrator!");
    JButton btnViewUsers = new JButton("View Users");
    JButton btnAddUser = new JButton("Add New User");
    JButton btnDeleteUser = new JButton("Delete User");
    JButton btnViewAllData = new JButton("View All System Data");
    JButton btnLogout = new JButton("Logout");

    public AdminMenu() {
        super("OWSB: Administrator Menu");
        makeFrame();
        showFrame();
    }

    public void makeFrame() {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(255, 230, 204));
        titlePanel.add(lblTitle, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        centerPanel.add(btnViewUsers);
        centerPanel.add(btnAddUser);
        centerPanel.add(btnDeleteUser);
        centerPanel.add(btnViewAllData);
        centerPanel.add(btnLogout);
        add(centerPanel, BorderLayout.CENTER);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(153, 51, 0));

        btnViewUsers.addActionListener(this);
        btnAddUser.addActionListener(this);
        btnDeleteUser.addActionListener(this);
        btnViewAllData.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    public void showFrame() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnViewUsers) {
            JOptionPane.showMessageDialog(this, "View Users...");
        } else if (evt.getSource() == btnAddUser) {
            JOptionPane.showMessageDialog(this, "Add New User...");
        } else if (evt.getSource() == btnDeleteUser) {
            JOptionPane.showMessageDialog(this, "Delete User...");
        } else if (evt.getSource() == btnViewAllData) {
            JOptionPane.showMessageDialog(this, "View All System Data...");
        } else if (evt.getSource() == btnLogout) {
            dispose();
            new LoginPage();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminMenu().setVisible(true);
            }
        });
    }
}
