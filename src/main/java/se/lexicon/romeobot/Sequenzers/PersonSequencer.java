package se.lexicon.romeobot.Sequenzers;

public class PersonSequencer {
    private static int personId = 100; // This looks better if we have more than 100 persons

    public static int nextPersonId() { // New Id for each new person, starts with 101
        personId++;
        return personId;
    }

    public static void reset() { // Resets the Id to start with 101 again
        personId = 100;
    }
}