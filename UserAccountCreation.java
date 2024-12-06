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
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create a panel for account creation form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

        // Add labels and text fields
        JLabel emailLabel = new JLabel("PMU Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField();

        // Add Create Account button
        JButton createAccountButton = new JButton("Create Account");

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
                            statusLabel.setForeground(Color.GREEN);
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
