package se.lexicon.romeobot;

import se.lexicon.romeobot.data.People;
import se.lexicon.romeobot.data.PeopleJDBC;
import se.lexicon.romeobot.data.TodoItems;
import se.lexicon.romeobot.data.TodoItemsJDBC;
import se.lexicon.romeobot.model.Person;
import se.lexicon.romeobot.model.Todo;

import java.time.LocalDate;

public class App {
    public static void main( String[] args ) {

        People personDB = new PeopleJDBC();
        TodoItems todoDB = new TodoItemsJDBC();

        System.out.println("Adding Nick to the database");
        Person nicke = new Person(111,"Niklas","Lind");

        if(personDB.findById(111) == null) {
            System.out.println(personDB.create(nicke).toString());
        } else {
            System.out.println("Person already exists!");
        }

        System.out.println("--------------------------------------------------");

        System.out.println("Adding Eat Mango to the database");
        Todo mango = new Todo(1111, "Eat Mango", "Peel the mango and remove the stone so you can put it in your mouth.",
        LocalDate.of(2022,3,10),false,null);

        if(todoDB.findById(1111) == null) {
            System.out.println(todoDB.create(mango).toString());
        } else {
            System.out.println("TodoItem already exists!");
        }

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Printing all objects in database");
        personDB.findAll().forEach(System.out::println);
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        todoDB.findAll().forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Updating Nick name into the database");
        nicke.setLastName("Lindström");
        System.out.println(personDB.update(nicke).toString());
        System.out.println("--------------------------------------------------");

        System.out.println("Updating Eat Mango, Nick should do it");
        mango.setAssignee(nicke);
        System.out.println(todoDB.update(mango));
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");

        System.out.println("Updating Eat Mango, Nick have now eaten it");
        mango.setDone(true);
        System.out.println(todoDB.update(mango));
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Finding all person with -son");
        personDB.findByName("%son").forEach(System.out::println);
        System.out.println("--------------------------------------------------");
        System.out.println("Finding all person with -ers-");
        personDB.findByName("%ers%").forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");

        System.out.println("Finding all eaten fruit");
        todoDB.findByDoneStatus(true).forEach(System.out::println);
        System.out.println("--------------------------------------------------");

        System.out.println("Finding all fruit Nick should eat");
        todoDB.findByAssignee(nicke).forEach(System.out::println);
        System.out.println("--------------------------------------------------");

        System.out.println("Finding all fruit nr 106 should eat");
        todoDB.findByAssignee(106).forEach(System.out::println);
        System.out.println("==================================================");

        System.out.println("Finding all fruit nobody wants to eat");
        todoDB.findByUnassignedTodoItems().forEach(System.out::println);
        System.out.println("--------------------------------------------------");

        System.out.println("Deleting Nick and Eat Mango in the database");
        if(todoDB.deleteById(1111)){
            System.out.println("Eat Mango deleted"); // Delete Mango first to avoid Exception
        } else {
            System.out.println("Nobody was deleted");
        }
        if(personDB.deleteById(111)){
            System.out.println("Nick deleted");
        } else {
            System.out.println("Nobody was deleted");
        }
        System.out.println("--------------------------------------------------");

        if(personDB.deleteById(1)){
            System.out.println("Nr 1 was deleted");
        } else {
            System.out.println("Nobody was deleted");
        }
        if(todoDB.deleteById(1)){
            System.out.println("Nr 1 was deleted");
        } else {
            System.out.println("Nobody was deleted");
        }
        System.out.println("--------------------------------------------------");

        System.out.println("Printing all objects in database");
        personDB.findAll().forEach(System.out::println);
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
        todoDB.findAll().forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Trying to find People and things to do");

        Person person = personDB.findById(0);
        if (person != null) {
            System.out.println(person.toString());
        } else {
            System.out.println("Person nr " + (0) + " was null");
        }
        System.out.println("--------------------------------------------------");

        Todo todo = todoDB.findById(0);
        if (todo != null) {
            System.out.println(todo.toString());
        } else {
            System.out.println("Todo nr " + (0) + " was null");
        }
        System.out.println("==================================================");

        for (int i = 0; i < 11; i++) {

            person = personDB.findById(101 + i);
            if (person != null) {
                System.out.println(person.toString());
            } else {
                System.out.println("Person nr " + (101+i) + " was null");
            }
            System.out.println("--------------------------------------------------");

            todo = todoDB.findById(1001 + i);
            if (todo != null) {
                System.out.println(todo.toString());
            } else {
                System.out.println("Todo nr " + (1001+i) + " was null");
            }
            System.out.println("==================================================");
        }
    }
}
