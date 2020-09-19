package se.lexicon.romeobot;

import se.lexicon.romeobot.data.People;
import se.lexicon.romeobot.model.Person;

public class App {
    public static void main( String[] args ) {

        Person jocke = new Person("Joakim","Andersson");
        Person nisse = new Person("Nils","Persson");
        Person ante = new Person("Anders","Svensson");
//        System.out.println(People.create(jocke).toString());
//        System.out.println(People.create(nisse).toString());
//        System.out.println(People.create(ante).toString());

        System.out.println(People.findByName("%son").toString());

        System.out.println("----------------------------");
        System.out.println(People.findById(102).toString());

        System.out.println("----------------------------");
        System.out.println(People.findByName("And%").toString());

        System.out.println("----------------------------");
        nisse.setLastName("Pettersson");
        System.out.println(People.update(nisse).toString());

        System.out.println("----------------------------");
        System.out.println(People.findAll().toString());

        System.out.println("----------------------------");
        System.out.println();
    }
}
