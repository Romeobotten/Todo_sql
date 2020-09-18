package se.lexicon.romeobot.Sequenzers;

public class TodoSequencer {
    private static int todoId = 1000; // This looks better if we have more than 1000 tasks to do

    public static int nextTodoId() { // New Id for each new thing to do, starts with 1001
        todoId++;
        return todoId;
    }

    public static void reset() { // Resets the Id to start with 1001 again
        todoId = 1000;
    }
}