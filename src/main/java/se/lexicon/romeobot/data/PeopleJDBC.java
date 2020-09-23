package se.lexicon.romeobot.data;

import se.lexicon.romeobot.db.DbConnection;
import se.lexicon.romeobot.db.MySqlException;
import se.lexicon.romeobot.model.Person;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleJDBC implements People {

    @Override
    public Person create(Person person) {

        String ADD_PERSON = "INSERT INTO person VALUES (?,?,?)";
        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlAddPerson = connection.prepareStatement(ADD_PERSON)
            ) {
            sqlAddPerson.setInt(1, person.getPersonId());
            sqlAddPerson.setString(2, person.getFirstName());
            sqlAddPerson.setString(3, person.getLastName());

            sqlAddPerson.executeUpdate();
            return person;

        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findAll() {

        List<Person> personList = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM person";
        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindAll = connection.prepareStatement(SELECT_ALL)
            ) {
            ResultSet resultSet = sqlFindAll.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    @Override
    public Person findById(int personId) {

        Person person = new Person();
        String SELECT_BY_ID = "SELECT * FROM person WHERE person_id = ?";

        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindById = connection.prepareStatement(SELECT_BY_ID)
            ) {
            sqlFindById.setInt(1, personId);
            ResultSet resultSet = sqlFindById.executeQuery();

            if (resultSet.next()) {
                person.setPersonId(resultSet.getInt(1));
                person.setFirstName(resultSet.getString(2));
                person.setLastName(resultSet.getString(3));
                return person;
            }
        } catch (SQLException | MySqlException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {

        List<Person> personList = new ArrayList<>();
        String SELECT_BY_NAME = "SELECT * FROM person WHERE first_name LIKE ? OR last_name LIKE ?";

        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindByName = connection.prepareStatement(SELECT_BY_NAME)
            ) {
            sqlFindByName.setString(1, name);
            sqlFindByName.setString(2, name);
            ResultSet resultSet = sqlFindByName.executeQuery();

            while (resultSet.next()) {
                personList.add(new Person(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3)));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    @Override
    public Person update(Person person) {

        String UPDATE_PERSON = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";

        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlUpdatePerson = connection.prepareStatement(UPDATE_PERSON)
            ) {
            sqlUpdatePerson.setString(1, person.getFirstName());
            sqlUpdatePerson.setString(2, person.getLastName());
            sqlUpdatePerson.setInt(3, person.getPersonId());
            sqlUpdatePerson.executeUpdate();
            return person;

        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int personId) {

        String DELETE_PERSON = "DELETE FROM person WHERE person_id = ?";
        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlDeletePerson = connection.prepareStatement(DELETE_PERSON)
            ) {
            sqlDeletePerson.setInt(1, personId);

            if(sqlDeletePerson.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}