package se.lexicon.romeobot;

import se.lexicon.romeobot.data.PeopleJDBC;
import se.lexicon.romeobot.data.TodoItemsJDBC;
import se.lexicon.romeobot.model.Person;
import se.lexicon.romeobot.model.Todo;

import java.time.LocalDate;

public class App {
    public static void main( String[] args ) {

//        Person jocke = new Person(0,"Joakim","Nilsson");
//        Person nisse = new Person(0,"Sven","Persson");
//        Person ante = new Person(103,"Anders","Johansson");
//        System.out.println(PeopleJDBC.create(jocke).toString());
//        System.out.println(PeopleJDBC.create(nisse).toString());
//        System.out.println(PeopleJDBC.create(ante).toString());
        Person olle = new Person(0,"Olof","Hård");
        Todo apple = new Todo(1001, "Eat Apple", "Peel the apple and cut it in pieces so you can put it in your mouth.",
                LocalDate.of(2020,12,13),false,null);
        Todo banana = new Todo(0, "Eat Banana", "Peel the banana and cut it in slices so you can put it in your mouth.",
                LocalDate.of(2020,10,18),false,null);

        PeopleJDBC.findAll().forEach(System.out::println);

        System.out.println(TodoItemsJDBC.create(apple).toString());
        System.out.println(TodoItemsJDBC.create(banana).toString());

        apple.setAssignee(PeopleJDBC.findById(102));
        TodoItemsJDBC.update(apple);

        TodoItemsJDBC.findAll().forEach(System.out::println);

        System.out.println(PeopleJDBC.findByName("%son").toString());

        System.out.println("----------------------------");
        System.out.println(PeopleJDBC.findById(102).toString());

        System.out.println("----------------------------");
        System.out.println(PeopleJDBC.findByName("And%").toString());

        System.out.println("----------------------------");
//        nisse.setLastName("Pettersson");
        System.out.println(PeopleJDBC.deleteById(109));
//        System.out.println(PeopleJDBC.deleteById(106));
//        System.out.println(PeopleJDBC.deleteById(107));
        System.out.println("----------------------------");
//        System.out.println(PeopleJDBC.findAll().toString());
        PeopleJDBC.findAll().forEach(System.out::println);

        System.out.println("----------------------------");
        System.out.println();
    }
}
