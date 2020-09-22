package se.lexicon.romeobot.model;

public class Person {
    private int personId;
    private String firstName;
    private String lastName;

    public Person() {
    }

    public Person(int personId, String firstName, String lastName) { // Constructor
        this.personId = personId; // PersonSequencer.nextPersonId();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getPersonId() { // We need to read personId
        return this.personId;
    }                          // We have no setPersonId because it is set only once and final
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
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}