package se.lexicon.romeobot.model;

import se.lexicon.romeobot.Sequenzers.TodoSequencer;

public class Todo {
    private final int todoId;
//    private String title;
    private String description;
//    private LocalDate deadline;
    private boolean done;
    private Person assignee;

    public Todo(String description) { // Constructor
        this.todoId = TodoSequencer.nextTodoId();
        this.description = description;
        this.done = false;
        this.assignee = null;
    }

    public int getTodoId() { // We need the ID number of the task
        return todoId;
    }                        // We have no setTodoId because it is set only once and final

    public String getDescription() { // We need to read the description
        return description;
    }

    public void setDescription(String description) { // We might need to change the description
        this.description = description;
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
}