package se.lexicon.romeobot.model;

import java.util.Objects;

public class Person {
    private int personId;
    private String firstName;
    private String lastName;

    public Person() {  // Empty constructor is needed
    }

    public Person(int personId, String firstName, String lastName) { // Constructor
        this.personId = personId; // PersonSequencer.nextPersonId(); Not needed
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPersonId() { // We need to read personId
        return this.personId;
    }                          // We have
    public void setPersonId(int personId) { // We can set the personId
        this.personId = personId;
    }

    public String getFirstName() { // We need to read the name
        return this.firstName;
    }

    public void setFirstName(String firstName) { // We need to set the name
        this.firstName = firstName;
    }

    public String getLastName() { // We need to read the name
        return this.lastName;
    }

    public void setLastName(String lastName) { // We need to set the name
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return personId == person.personId &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName);
    }

    @Override
    public String toString() { // Looks better
        return "Person{" +
                "personId = " + personId +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                '}';
    }
}