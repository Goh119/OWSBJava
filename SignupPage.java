package managementsystem;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

public class SignupPage extends JFrame implements ActionListener {

    ArrayList<User> userList;
    
    JLabel lblTitle = new JLabel("    User Registration");
    JLabel lblUsername = new JLabel("Username:");
    JLabel lblPassword = new JLabel("Password:");
    JLabel lblRole = new JLabel("Role:");

    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JComboBox<String> roleBox = new JComboBox<>(new String[]{
        "Sales Manager",
        "Purchase Manager",
        "Inventory Manager",
        "Finance Manager"
        //"Admin" //intentionally excluded
    });

    JButton btnRegister = new JButton("Register");
    JButton btnBack = new JButton("Back to Login");

    public SignupPage() {
        super("OWSB: Sign Up Page");
        
        userList = new ArrayList<>();
        
        makeFrame();
        showFrame();
        populateArrayList();
    }

    public void makeFrame() {
        setLayout(new BorderLayout(10, 10));

        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        formPanel.add(lblUsername);
        formPanel.add(usernameField);
        formPanel.add(lblPassword);
        formPanel.add(passwordField);
        formPanel.add(lblRole);
        formPanel.add(roleBox);
        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnBack);
        add(buttonPanel, BorderLayout.SOUTH);

        btnRegister.addActionListener(this);
        btnBack.addActionListener(this);
    }

    public void showFrame() {
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == btnRegister) {
            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword());
            String role = (String) roleBox.getSelectedItem();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            populateArrayList(); // 先加载旧用户

            for (User user : userList) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    JOptionPane.showMessageDialog(this, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            userList.add(new User(username, password, role));
            saveUsersToFile();

            JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginPage();

        } else if (evt.getSource() == btnBack) {
            dispose();
            new LoginPage();
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
    
    private void saveUsersToFile() {
        try {
            FileOutputStream file = new FileOutputStream("Users.dat");
            ObjectOutputStream outputFile = new ObjectOutputStream(file);

            for (User user : userList) {
                outputFile.writeObject(user);
            }

            outputFile.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new SignupPage().setVisible(true);
        });
    }
}
