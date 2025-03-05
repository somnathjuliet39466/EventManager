package src;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    private int nextId;

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public void addTask(String title, String description) {
        tasks.add(new Task(nextId++, title, description));
    }

    public void completeTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(true);
                return;
            }
        }
    }

    public boolean deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

    public void clearAllTasks() {
        tasks.clear();
        nextId = 1; // Reset ID counter
    }

    public List<Task> getTasks() {
        return tasks;
    }
}