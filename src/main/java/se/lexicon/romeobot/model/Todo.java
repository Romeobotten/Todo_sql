package se.lexicon.romeobot.model;

import java.time.LocalDate;

public class Todo {
    private int todoId;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
    private Person assignee;

    public Todo() {
    }

    public Todo(int todoId, String title, String description, LocalDate deadline, boolean done, Person assignee) { // Constructor
        this.todoId = todoId; //TodoSequencer.nextTodoId();
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.done = done;
        this.assignee = assignee;
    }

    public int getTodoId() { // We need the ID number of the task
        return todoId;
    }                        // We have no setTodoId because it is set only once and final

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() { // We need to read the description
        return description;
    }

    public void setDescription(String description) { // We might need to change the description
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() { // We want to know if the task is done
        return done;
    }

    public void setDone(boolean done) { // We want to set the task as done when it is done
        this.done = done;
    }

    public Person getAssignee() { // We want to know if a person is assigned to the task
        return assignee;
    }

    public void setAssignee(Person assignee) { // We want to get someone to do the job
        this.assignee = assignee;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "todoId=" + todoId +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", assignee=" + assignee +
                '}';
    }
}