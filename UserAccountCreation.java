import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class UserAccountCreation {

    // Mock database (email and password storage)
    private static HashMap<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        // Create JFrame for account creation window
        JFrame frame = new JFrame("Create New Account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLocationRelativeTo(null);  // Center the frame on the screen
        frame.setLayout(new BorderLayout());

        // Set background color for the frame
        frame.getContentPane().setBackground(new Color(245, 245, 245));

        // Create a panel for account creation form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 15, 15));
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around panel

        // Add labels and text fields
        JLabel emailLabel = new JLabel("PMU Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JTextField emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1, true));
        emailField.setCaretColor(new Color(0, 102, 204));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1, true));
        passwordField.setCaretColor(new Color(0, 102, 204));

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        JPasswordField confirmPasswordField = new JPasswordField();
        confirmPasswordField.setFont(new Font("Arial", Font.PLAIN, 14));
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(173, 216, 230), 1, true));
        confirmPasswordField.setCaretColor(new Color(0, 102, 204));

        // Add Create Account button
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setFont(new Font("Arial", Font.BOLD, 14));
        createAccountButton.setForeground(Color.WHITE);
        createAccountButton.setBackground(new Color(0, 102, 204));
        createAccountButton.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2, true));
        createAccountButton.setFocusPainted(false);
        createAccountButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add components to panel
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(createAccountButton);

        // Add panel to frame
        frame.add(panel, BorderLayout.CENTER);

        // Add a status label at the bottom
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        frame.add(statusLabel, BorderLayout.SOUTH);

        // Set frame visibility
        frame.setVisible(true);

        // Create Account button action listener
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                if (validateEmail(email)) {
                    if (password.equals(confirmPassword)) {
                        if (!userDatabase.containsKey(email)) {
                            userDatabase.put(email, password);
                            statusLabel.setText("Account created successfully!");
                            statusLabel.setForeground(new Color(0, 128, 0));  // Green color for success
                            JOptionPane.showMessageDialog(frame, "Account created for " + email);
                            frame.dispose(); // Close frame after successful account creation
                        } else {
                            statusLabel.setText("Email already exists!");
                            statusLabel.setForeground(Color.RED);
                        }
                    } else {
                        statusLabel.setText("Passwords do not match.");
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
}
