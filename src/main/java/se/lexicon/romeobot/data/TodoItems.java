package se.lexicon.romeobot.data;

import se.lexicon.romeobot.model.Person;
import se.lexicon.romeobot.model.Todo;

import java.util.Collection;

public interface TodoItems {

    Todo create(Todo todo);

    Collection<Todo> findAll();

    Todo findById(int id);

    Collection<Todo> findByDoneStatus(boolean done);
    Collection<Todo> findByAssegnee(int id);
    Collection<Todo> findByAssegnee(Person person);
    Collection<Todo> findByUnassignedTodoItems();

    Todo update(Todo todo);

    boolean deleteById(int id);

}

//    private static Todo[] todoArray = new Todo[0]; // Array of Things to do
//
//    public static int size() { // Number of tasks in the array
//        return todoArray.length;
//    }
//
//    public static Todo[] findAll() { // Returns the complete array
//        return todoArray;
//    }
//
//    public static Todo findById(int todoId) {
//        for (int i = 0; i < size(); i++) {
//            if (todoArray[i].getTodoId() == todoId) {
//                return todoArray[i];
//            }
//        }
//        return null;
//    }
//
//    public static Todo createNewTodo(String description) {
//        todoArray = Arrays.copyOf(todoArray, size() + 1);
//        todoArray[size() - 1] = new Todo(description);
//        return todoArray[size() - 1];
//    }
//
//    public static void Clear() {
//        todoArray = Arrays.copyOf(todoArray, 0);
//    }
//
//    public static Todo[] findByDoneStatus(boolean doneStatus) {
//        Todo[] doneStatusArray = new Todo[0];
//        for (int i = 0; i < size(); i++) {
//            if (todoArray[i].isDone() == doneStatus) {
//                doneStatusArray = Arrays.copyOf(doneStatusArray, doneStatusArray.length + 1);
//                doneStatusArray[doneStatusArray.length - 1] = todoArray[i];
//            }
//        }
//        return doneStatusArray;
//    }
//
//    public static Todo[] findByAssignee(int personId) {
//        Todo[] assigneeArray = new Todo[0];
//        for (int i = 0; i < size(); i++) {
//            if (todoArray[i].getAssignee().getPersonId() == personId) {
//                assigneeArray = Arrays.copyOf(assigneeArray, assigneeArray.length + 1);
//                assigneeArray[assigneeArray.length - 1] = todoArray[i];
//            }
//        }
//        return assigneeArray;
//    }
//
//    public static Todo[] findByAssignee(Person assignee) {
//        Todo[] assigneeArray = new Todo[0];
//        for (int i = 0; i < size(); i++) {
//            if (todoArray[i].getAssignee().equals(assignee)) {
//                assigneeArray = Arrays.copyOf(assigneeArray, assigneeArray.length + 1);
//                assigneeArray[assigneeArray.length - 1] = todoArray[i];
//            }
//        }
//        return assigneeArray;
//    }
//
//    public static Todo[] findUnassignedTodoItems() {
//        Todo[] unassignedArray = new Todo[0];
//        for (int i = 0; i < size(); i++) {
//            if (todoArray[i].getAssignee() == null) {
//                unassignedArray = Arrays.copyOf(unassignedArray, unassignedArray.length + 1);
//                unassignedArray[unassignedArray.length - 1] = todoArray[i];
//            }
//        }
//        return unassignedArray;
//    }
//
//    public static boolean removeTodoItem(int todoId) {
//        for (int i = 0; i < size(); i++) {
//            if (todoArray[i].getTodoId() == todoId) {
//                for (int j = i; j < size() - 1; j++) {
//                    todoArray[j] = todoArray[j + 1];
//                }
//                todoArray = Arrays.copyOf(todoArray, size() - 1);
//                return true;
//            }
//        }
//        return false;
//    }
//}