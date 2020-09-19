package se.lexicon.romeobot.data;

//import se.lexicon.romeobot.db.DbConnection;
//import se.lexicon.romeobot.db.MySqlException;

import se.lexicon.romeobot.db.DbConnection;
import se.lexicon.romeobot.db.MySqlException;
import se.lexicon.romeobot.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class People {
    private static Person[] peopleArray = new Person[0]; // An Array of Persons


    public static Person create(Person person) {

        try {
            String ADD_PERSON = "INSERT INTO person VALUES (?,?,?)";
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlAddPerson = connection.prepareStatement(ADD_PERSON);

            sqlAddPerson.setInt(1, person.getPersonId());
            sqlAddPerson.setString(2, person.getFirstName());
            sqlAddPerson.setString(3, person.getLastName());

            sqlAddPerson.executeUpdate();

        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public static Collection<Person> findAll() {

        List<Person> personList = new ArrayList<>();

        try {
            String SELECT_ALL = "SELECT * FROM person";
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindAll = connection.prepareStatement(SELECT_ALL);

//            sqlFindAll.setInt(1, 3040);
//            sqlFindAll.setInt(2, 40);
            ResultSet resultSet = sqlFindAll.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(
//                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    public static Person findById(int personId) {

        Person person = new Person();

        try {
            String SELECT_BY_ID = "SELECT * FROM person WHERE person_id = ?";
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindById = connection.prepareStatement(SELECT_BY_ID);
            sqlFindById.setInt(1, personId);
            ResultSet resultSet = sqlFindById.executeQuery();

            if (resultSet.next()) {
//                person.setPersonId(resultSet.getInt("id"));
                person.setFirstName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
            }
        } catch (SQLException | MySqlException e) {
            e.printStackTrace();
        }
        return person;
    }

    public static Collection<Person> findByName(String name) {

        List<Person> personList = new ArrayList<>();

        try {
            String SELECT_BY_NAME = "SELECT * FROM person WHERE first_name LIKE ? OR last_name LIKE ?";
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindByName = connection.prepareStatement(SELECT_BY_NAME);
            sqlFindByName.setString(1, name);
            sqlFindByName.setString(2, name);
            ResultSet resultSet = sqlFindByName.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(
//                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    public static Person update(Person person) {

        try {
            String UPDATE_PERSON = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlUpdatePerson = connection.prepareStatement(UPDATE_PERSON);

            sqlUpdatePerson.setString(1, person.getFirstName());
            sqlUpdatePerson.setString(2, person.getLastName());
            sqlUpdatePerson.setInt(3, person.getPersonId());
            sqlUpdatePerson.executeUpdate();

        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public static boolean deleteById(int personId) {

        try {
            String DELETE_PERSON = "DELETE FROM person WHERE person_id = ?";
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlDeleteCity = connection.prepareStatement(DELETE_PERSON);

            sqlDeleteCity.setInt(1, personId);
            if(sqlDeleteCity.executeUpdate() == 1){
                return true;
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public static int size() {
        return peopleArray.length;
    }

//    public static Person[] findAll() {
//        return peopleArray;
//    }

//    public static Person findById(int personId) {
//        for (int i = 0; i < size(); i++) {
//            if (peopleArray[i].getPersonId() == personId) {
//                return peopleArray[i];
//            }
//        }
//        return null;
//    }

//    public static Person CreateNewPerson(String firstName, String lastName) {
//        peopleArray = Arrays.copyOf(peopleArray, size() + 1);
//        peopleArray[size() - 1] = new Person(firstName, lastName);
//        return peopleArray[size() - 1];
//    }

    public static void Clear() {
        peopleArray = Arrays.copyOf(peopleArray, 0);
    }

//    public static boolean removePerson(int personId) {
//        for (int i = 0; i < size(); i++) {
//            if (peopleArray[i].getPersonId() == personId) {
//                for (int j = i; j < size() - 1; j++) {
//                    peopleArray[j] = peopleArray[j + 1];
//                }
//                peopleArray = Arrays.copyOf(peopleArray, size() - 1);
//                return true;
//            }
//        }
//        return false;
//    }
}