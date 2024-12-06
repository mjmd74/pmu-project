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
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(new BorderLayout());

        // Create a panel for login form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // Add labels and text fields
        JLabel emailLabel = new JLabel("PMU Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        // Add Login button
        JButton loginButton = new JButton("Login");

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
                        statusLabel.setForeground(Color.GREEN);
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
