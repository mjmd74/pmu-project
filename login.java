import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class login {

    // Mock database (email and password storage)
    private static HashMap<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        // Adding mock user credentials
        userDatabase.put("user1@pmu.edu", "password123");
        userDatabase.put("user2@pmu.edu", "securepass456");

        // Create JFrame for login window
        JFrame loginFrame = new JFrame("PmuMentor Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(450, 350);
        loginFrame.setLocationRelativeTo(null); // Center window on screen
        loginFrame.setLayout(new BorderLayout());
        loginFrame.getContentPane().setBackground(new Color(245, 245, 245)); // Light background color

        // Create a panel for login form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Add padding to the panel
        panel.setBackground(new Color(255, 255, 255)); // White background for the form

        // Add labels and text fields
        JLabel emailLabel = new JLabel("PMU Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150))); // Gray border for text field

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150))); // Gray border for text field

        // Add Login button with a modern look
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(58, 123, 255)); // Blue color for the button
        loginButton.setForeground(Color.WHITE); // White text color
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside the button
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovered
        loginButton.setBorderPainted(false); // Remove border

        // Add components to panel
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        // Add panel to frame
        loginFrame.add(panel, BorderLayout.CENTER);

        // Add a status label at the bottom
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginFrame.add(statusLabel, BorderLayout.SOUTH);

        // Set frame visibility
        loginFrame.setVisible(true);

        // Login button action listener
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (validateEmail(email)) {
                    if (authenticateUser(email, password)) {
                        statusLabel.setText("Login successful! Welcome to PmuMentor.");
                        statusLabel.setForeground(new Color(34, 177, 76)); // Green color for success
                        // Open user dashboard (placeholder)
                        JOptionPane.showMessageDialog(loginFrame, "Welcome, " + email + "!");
                        loginFrame.dispose(); // Close login frame
                    } else {
                        statusLabel.setText("Invalid email or password.");
                        statusLabel.setForeground(Color.RED);
                    }
                } else {
                    statusLabel.setText("Please use a valid PMU email address.");
                    statusLabel.setForeground(Color.RED);
                }
            }
        });
    }

    // Validate PMU email
    private static boolean validateEmail(String email) {
        return email.endsWith("@pmu.edu");
    }

    // Authenticate user credentials
    private static boolean authenticateUser(String email, String password) {
        return userDatabase.containsKey(email) && userDatabase.get(email).equals(password);
    }
}
