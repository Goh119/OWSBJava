package managementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FinanceManagerMenu extends JFrame implements ActionListener {

    JLabel lblTitle = new JLabel("    Welcome, Finance Manager!");
    JButton btnViewPOs = new JButton("View Purchase Orders");
    JButton btnViewInvoices = new JButton("View Invoices");
    JButton btnGenerateReport = new JButton("Generate Financial Report");
    JButton btnLogout = new JButton("Logout");

    public FinanceManagerMenu() {
        super("OWSB: Finance Manager Menu");
        makeFrame();
        showFrame();
    }

    public void makeFrame() {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(255, 245, 204));
        titlePanel.add(lblTitle, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.PAGE_START);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        centerPanel.add(btnViewPOs);
        centerPanel.add(btnViewInvoices);
        centerPanel.add(btnGenerateReport);
        centerPanel.add(btnLogout);
        add(centerPanel, BorderLayout.CENTER);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(153, 102, 0));

        btnViewPOs.addActionListener(this);
        btnViewInvoices.addActionListener(this);
        btnGenerateReport.addActionListener(this);
        btnLogout.addActionListener(this);
    }

    public void showFrame() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnViewPOs) {
            JOptionPane.showMessageDialog(this, "Viewing Purchase Orders...");
        } else if (evt.getSource() == btnViewInvoices) {
            JOptionPane.showMessageDialog(this, "Viewing Invoices...");
        } else if (evt.getSource() == btnGenerateReport) {
            JOptionPane.showMessageDialog(this, "Generating Financial Report...");
        } else if (evt.getSource() == btnLogout) {
            dispose();
            new LoginPage();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new FinanceManagerMenu().setVisible(true);
        });
    }
}
