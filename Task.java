package src;

public class Task {
    private String title;
    private String description;
    private boolean isCompleted;
    private int id;

    public Task(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }

    @Override
    public String toString() {
        String status = isCompleted ? "Completed" : "Pending";
        return "ID: " + id + " | Title: " + title + " | Description: " + description + " | Status: " + status;
    }
}