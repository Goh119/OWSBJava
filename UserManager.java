package managementsystem;

import java.io.*;
import java.util.*;

public class UserManager {
    private static final String USER_FILE = "users.dat";

    // Register new user
    public static boolean registerUser(String username, String password, String role) {
        if (isUsernameTaken(username)) return false;

        List<User> users = loadUsers();
        users.add(new User(username, password, role));

        return saveUsers(users);
    }

    // Check if username already exists
    public static boolean isUsernameTaken(String username) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    // Login
    public static User login(String username, String password) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) &&
                user.getPassword().equals(password)) {
                return createUserObject(username, password, user.getRole());
            }
        }
        return null;
    }

    // Create user object based on role
    private static User createUserObject(String username, String password, String role) { // String userId,
        switch (role) {
            case "Sales Manager": 
                return new SalesManager(username, password); // userId,
            case "Purchase Manager": 
                return new PurchaseManager(username, password); // userId,
            case "Inventory Manager": 
                return new InventoryManager(username, password); // userId,
            case "Finance Manager": 
                return new FinanceManager(username, password); // userId,
            case "Admin": 
                return new Admin(username, password); // userId,
            default: 
                return null;
        }
    }

    // Load users from users.dat
    private static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            while (true) {
                try {
                    users.add((User) ois.readObject());
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            // First time, no users yet — ignore
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    // Save users to users.dat
    private static boolean saveUsers(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            for (User user : users) {
                oos.writeObject(user);
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
            return false;
        }
    }
}
