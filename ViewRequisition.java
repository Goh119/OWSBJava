package managementsystem;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewRequisition extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblTitle = new JLabel("Requisition List");
    private JButton btnBack = new JButton("Back");
    private ArrayList<Requisition> requisitionList;
    
    public ViewRequisition() {
        super("OWSB: View Requisition");
        
        requisitionList = new ArrayList<>();
        populateRequisitionList();
        addRequisitionToTable();
        makeFrame();
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
            "Requisition ID",
            "Item Name",
            "Quantity Requested",
            "Requested by"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        //button
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
    
    public void populateRequisitionList() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Requisition.dat"))) {
            while (true) {
                try {
                    requisitionList.add((Requisition) ois.readObject());
                } catch(EOFException e) {
                    break;
                }
            }
        } catch(IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error loading requisitions: " + e.getMessage());
        }
    }
    
    public void addRequisitionToTable() {
        if (requisitionList == null) return;
        for (Requisition req : requisitionList) {
            tableModel.addRow(new Object[]{
                req.getRequisitionId(),
                req.getItemName(),
                req.getQuantityRequested(),
                req.getRequestedBy()
            });
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewRequisition().setVisible(true);
            }
        });
    }
}
