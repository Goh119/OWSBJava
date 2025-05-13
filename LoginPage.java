package managementsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class LoginPage extends JFrame implements ActionListener {
    
    ArrayList<User> userList;

    JLabel lblTitle = new JLabel("    Welcome to OWSB Purchase Order System");
    JLabel lblUsername = new JLabel("Username:");
    JLabel lblPassword = new JLabel("Password:");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton btnLogin = new JButton("Login");
    JButton btnSignup = new JButton("Sign Up");

    public LoginPage() {
        super("OWSB: Login Page");
        
        userList = new ArrayList<>();
        
        makeFrame();
        showFrame();
        populateArrayList();
    }

    public void makeFrame() {
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(204, 255, 229));
        titlePanel.add(lblTitle, BorderLayout.CENTER);
        add(titlePanel, BorderLayout.PAGE_START);

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));
        formPanel.add(lblUsername);
        formPanel.add(usernameField);
        formPanel.add(lblPassword);
        formPanel.add(passwordField);
        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnSignup);
        add(buttonPanel, BorderLayout.SOUTH);

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 153, 102));

        btnLogin.addActionListener(this);
        btnSignup.addActionListener(this);
    }

    public void showFrame() {
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnLogin) {
            login();
        } else if (evt.getSource() == btnSignup) {
            dispose();
            new SignupPage(); // lead to the Sign up page
        }
    }
    
    public void populateArrayList() {
        try {
            FileInputStream file = new FileInputStream("Users.dat");
            ObjectInputStream inputFile = new ObjectInputStream(file);

            while (true) {
                try {
                    userList.add((User) inputFile.readObject());
                } catch (EOFException e) {
                    break;
                }
            }

            inputFile.close();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void login() {
        populateArrayList(); // read through all the existings users

        String username = usernameField.getText().trim();
        String password = String.valueOf(passwordField.getPassword()).trim();

        boolean found = false;
        User matchedUser = null;

        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                matchedUser = user;
                found = true;
                break;
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome " + matchedUser.getRole());
            dispose();

            switch (matchedUser.getRole()) {
                case "Sales Manager": 
                    new SalesManagerMenu(); 
                    break;
                case "Purchase Manager": 
                    new PurchaseManagerMenu(); 
                    break;
                case "Inventory Manager": 
                    new InventoryManagerMenu(); 
                    break;
                case "Finance Manager": 
                    new FinanceManagerMenu(); 
                    break;
                case "Admin": 
                    new AdminMenu(); 
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Unknown role: " + matchedUser.getRole());
            }

        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
