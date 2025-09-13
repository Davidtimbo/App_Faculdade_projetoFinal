package com.example.faculdade;

public class Task {
    private long id;
    private String title;
    private String description;
    private int priority;
    private String dueDate;
    private boolean completed;

    // Construtor
    public Task(long id, String title, String description, int priority, String dueDate, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    // Getters e Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    // Método para representar a tarefa como string
    @Override
    public String toString() {
        return title + " (Prioridade: " + priority + ")";
    }
}
