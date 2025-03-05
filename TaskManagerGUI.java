package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TaskManagerGUI extends JFrame {
    private TaskManager taskManager;
    private FileHandler fileHandler;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;
    private JTextField titleField;
    private JTextField descField;

    public TaskManagerGUI() {
        taskManager = new TaskManager();
        fileHandler = new FileHandler();

        // Load tasks from file
        taskManager.getTasks().addAll(fileHandler.loadTasks());

        setTitle("Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Input panel for adding tasks
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));

        inputPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        inputPanel.add(titleField);

        inputPanel.add(new JLabel("Description:"));
        descField = new JTextField();
        inputPanel.add(descField);

        // Create buttons with GitHub-like green style
        JButton addButton = new JButton("Add Task");
        styleButton(addButton, new Color(40, 167, 69)); // GitHub green

        JButton deleteButton = new JButton("Delete Task");
        styleButton(deleteButton, new Color(40, 167, 69)); // Same green

        inputPanel.add(addButton);
        inputPanel.add(deleteButton);

        // Task list with a scroll pane
        listModel = new DefaultListModel<>();
        updateTaskList(); // Load existing tasks into the list
        taskList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Add components to the main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to the frame
        add(mainPanel);

        // Add task button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText().trim();
                String desc = descField.getText().trim();

                if (!title.isEmpty() && !desc.isEmpty()) {
                    taskManager.addTask(title, desc);
                    updateTaskList(); // Refresh the task list
                    titleField.setText("");
                    descField.setText("");
                } else {
                    JOptionPane.showMessageDialog(TaskManagerGUI.this, "Please fill in both fields!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Delete task button action
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Task selectedTask = taskManager.getTasks().get(selectedIndex);
                    taskManager.deleteTask(selectedTask.getId());
                    updateTaskList(); // Refresh the task list
                } else {
                    JOptionPane.showMessageDialog(TaskManagerGUI.this, "Please select a task to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Update the task list in the GUI
    private void updateTaskList() {
        listModel.clear();
        for (Task task : taskManager.getTasks()) {
            listModel.addElement(task.toString());
        }
    }

    // Method to style buttons like GitHub green
    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Century Gothic", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaskManagerGUI().setVisible(true);
            }
        });
    }
}
