import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.UUID;
import javax.swing.*;

public class EventCreation {

    // Mock event storage (in-memory database)
    private static HashMap<String, Event> eventDatabase = new HashMap<>();

    public static void main(String[] args) {
        // Create JFrame for event creation window
        JFrame frame = new JFrame("Event Creation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);  // Center the frame
        frame.setLayout(new BorderLayout());

        // Create a panel for event form
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.setBackground(Color.WHITE);

        // Event form components
        JLabel titleLabel = new JLabel("Event Title:");
        JTextField titleField = new JTextField();
        JLabel dateLabel = new JLabel("Event Date (yyyy-mm-dd):");
        JTextField dateField = new JTextField();
        JLabel timeLabel = new JLabel("Event Time (HH:mm):");
        JTextField timeField = new JTextField();
        JLabel locationLabel = new JLabel("Location:");
        JTextField locationField = new JTextField();
        JLabel descriptionLabel = new JLabel("Event Description:");
        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);

        // Create Event button
        JButton createEventButton = new JButton("Create Event");
        createEventButton.setBackground(new Color(84, 194, 66));
        createEventButton.setForeground(Color.WHITE);

        // Add components to the panel
        panel.add(titleLabel);
        panel.add(titleField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(timeLabel);
        panel.add(timeField);
        panel.add(locationLabel);
        panel.add(locationField);
        panel.add(descriptionLabel);
        panel.add(new JScrollPane(descriptionArea));
        panel.add(new JLabel());  // Empty label for spacing
        panel.add(createEventButton);

        // Add panel to frame
        frame.add(panel, BorderLayout.CENTER);

        // Status label
        JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);

        // Set frame visibility
        frame.setVisible(true);

        // Event creation action listener
        createEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim();
                String date = dateField.getText().trim();
                String time = timeField.getText().trim();
                String location = locationField.getText().trim();
                String description = descriptionArea.getText().trim();

                if (!title.isEmpty() && !date.isEmpty() && !time.isEmpty()) {
                    // Generate a unique event ID
                    String eventId = UUID.randomUUID().toString();

                    // Create an Event object
                    Event newEvent = new Event(eventId, title, date, time, location, description);

                    // Store event in the database
                    eventDatabase.put(eventId, newEvent);

                    // Generate shareable link
                    String shareableLink = "http://eventsystem.com/event/" + eventId;

                    // Show event creation success and shareable link
                    statusLabel.setText("Event Created! Shareable Link: " + shareableLink);
                    statusLabel.setForeground(new Color(0, 128, 0));  // Green for success

                    JOptionPane.showMessageDialog(frame, "Event created successfully!\nShareable Link: " + shareableLink);
                } else {
                    statusLabel.setText("Please fill in all required fields.");
                    statusLabel.setForeground(Color.RED);
                }
            }
        });
    }

    // Event class to represent the event
    static class Event {
        private String eventId;
        private String title;
        private String date;
        private String time;
        private String location;
        private String description;

        public Event(String eventId, String title, String date, String time, String location, String description) {
            this.eventId = eventId;
            this.title = title;
            this.date = date;
            this.time = time;
            this.location = location;
            this.description = description;
        }

        // Getters
        public String getEventId() {
            return eventId;
        }

        public String getTitle() {
            return title;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getLocation() {
            return location;
        }

        public String getDescription() {
            return description;
        }

        // Update event details
        public void updateEvent(String title, String date, String time, String location, String description) {
            this.title = title;
            this.date = date;
            this.time = time;
            this.location = location;
            this.description = description;
        }
    }
}
