package managementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PurchaseManagerMenu extends JFrame implements ActionListener {
    JLabel lblTitle = new JLabel ("Welcome, Purchase Manager!");
    JButton btnViewItems = new JButton("View Item List");
    JButton btnViewSuppliers = new JButton("View Supplier List");
    JButton btnViewPRs = new JButton("View Requisitions");
    JButton btnGeneratePO = new JButton("Generate Purchase Order");
    JButton btnViewPOs = new JButton("View Purchase Orders");
    JButton btnLogout = new JButton("Logout");
    
    //private String username;
    
    public PurchaseManagerMenu() { //String username
        super("OWSB: Purchase Manager Menu");
        //this.username = username;
        makeFrame();
        showFrame();
    }
    
    public void makeFrame() {
        setLayout(new BorderLayout());
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(204, 229, 255));
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        centerPanel.add(btnViewItems);
        centerPanel.add(btnViewSuppliers);
        centerPanel.add(btnViewPRs);
        centerPanel.add(btnGeneratePO);
        centerPanel.add(btnViewPOs);
        centerPanel.add(btnLogout);
        add(centerPanel, BorderLayout.CENTER);
        
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 102, 153));
        
        btnViewItems.addActionListener(this);
        btnViewSuppliers.addActionListener(this);
        btnViewPRs.addActionListener(this);
        btnGeneratePO.addActionListener(this);
        btnViewPOs.addActionListener(this);
        btnLogout.addActionListener(this);
    }
    
    public void showFrame() {
        setSize(500, 400);
        //setSize(1080, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void showPOOptionsDialog() {
        JDialog poDialog = new JDialog(this, "Generate Purchase Order Options", true);
        //poDialog.setLayout(new GridLayout(4, 1, 10, 10));
        poDialog.setSize(500, 400);
        poDialog.setLocationRelativeTo(this);
        
         JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
         centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
         //centerPanel.setBackground(Color.WHITE); 

        JButton btnAdd = new JButton("Add Purchase Order");
        JButton btnEdit = new JButton("Edit Purchase Order");
        JButton btnDelete = new JButton("Delete Purchase Order");
        JButton btnCancel = new JButton("Cancel");

        btnAdd.addActionListener(e -> {
            poDialog.dispose();
            new AddPurchaseOrder();
        });

        btnEdit.addActionListener(e -> {
            poDialog.dispose();
            new EditPurchaseOrder();
        });

        btnDelete.addActionListener(e -> {
            poDialog.dispose();
            new DeletePurchaseOrder();
        });

        btnCancel.addActionListener(e -> poDialog.dispose());

        centerPanel.add(btnAdd);
        centerPanel.add(btnEdit);
        centerPanel.add(btnDelete);
        centerPanel.add(btnCancel);
        
        poDialog.setLayout(new BorderLayout());
        poDialog.add(centerPanel, BorderLayout.CENTER);

        poDialog.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnViewItems) {
            //JOptionPane.showMessageDialog(this, "View Item List...");
            new ViewItemList();
        } else if (evt.getSource() == btnViewSuppliers) {
            //JOptionPane.showMessageDialog(this, "View Supplier List...");
            new ViewSupplierList();
        } else if (evt.getSource() == btnViewPRs) {
            //JOptionPane.showMessageDialog(this, "View PRs List...");
            new ViewRequisition();
        } else if (evt.getSource() == btnGeneratePO) {
            //JOptionPane.showMessageDialog(this, "Generate PO...");
            showPOOptionsDialog();
        } else if (evt.getSource() == btnViewPOs) {
            //JOptionPane.showMessageDialog(this, "View POs List...");
            new ViewPurchaseOrder();
        } else if (evt.getSource() == btnLogout) {
            dispose();
            new LoginPage();
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
