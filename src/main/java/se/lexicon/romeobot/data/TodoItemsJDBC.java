package se.lexicon.romeobot.data;

import se.lexicon.romeobot.db.DbConnection;
import se.lexicon.romeobot.db.MySqlException;
import se.lexicon.romeobot.model.Person;
import se.lexicon.romeobot.model.Todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TodoItemsJDBC implements TodoItems {

    People dbPersons = new PeopleJDBC();

    @Override
    public Todo create(Todo todo) {

        try {
            String ADD_TODO = "INSERT INTO todo_item VALUES (?,?,?,?,?,?)";
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlAddTodo = connection.prepareStatement(ADD_TODO);

            sqlAddTodo.setInt(1, todo.getTodoId());
            sqlAddTodo.setString(2, todo.getTitle());
            sqlAddTodo.setString(3, todo.getDescription());
            sqlAddTodo.setDate(4, Date.valueOf(todo.getDeadline()));
            sqlAddTodo.setBoolean(5, todo.isDone());
            if(todo.getAssignee() != null) {
                sqlAddTodo.setInt(6, (todo.getAssignee().getPersonId()));
            } else {
                sqlAddTodo.setNull(6,6);
            }

            sqlAddTodo.executeUpdate();
            return todo;

        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Todo> findAll() {

        List<Todo> todoList = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM todo_item";

        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindAll = connection.prepareStatement(SELECT_ALL)
            ) {
            ResultSet resultSet = sqlFindAll.executeQuery();

            while (resultSet.next()) {
                todoList.add(new Todo(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getBoolean(5),
                    dbPersons.findById(resultSet.getInt(6))));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Todo findById(int todoId) {

        Todo todo = new Todo();
        String SELECT_BY_ID = "SELECT * FROM todo_item WHERE todo_id = ?";

        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindById = connection.prepareStatement(SELECT_BY_ID)
            ) {
            sqlFindById.setInt(1, todoId);
            ResultSet resultSet = sqlFindById.executeQuery();

            if (resultSet.next()) {
                todo.setTodoId(resultSet.getInt(1));
                todo.setTitle(resultSet.getString(2));
                todo.setDescription(resultSet.getString(3));
                todo.setDeadline(resultSet.getDate(4).toLocalDate());
                todo.setDone(resultSet.getBoolean(5));
                todo.setAssignee(dbPersons.findById(resultSet.getInt(6)));

                return todo;
            }
        } catch (SQLException | MySqlException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Todo> findByDoneStatus(boolean done) {

        List<Todo> todoList = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM todo_item WHERE done = ?";
        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindById = connection.prepareStatement(SELECT_ALL)
            ) {
            sqlFindById.setBoolean(1, done);
            ResultSet resultSet = sqlFindById.executeQuery();

            while (resultSet.next()) {
                todoList.add(new Todo(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getBoolean(5),
                    dbPersons.findById(resultSet.getInt(6))));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Collection<Todo> findByAssignee(int assigneeId) {

        List<Todo> todoList = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM todo_item WHERE assignee_id = ?";
        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindById = connection.prepareStatement(SELECT_ALL)
            ) {
            sqlFindById.setInt(1, assigneeId);
            ResultSet resultSet = sqlFindById.executeQuery();

            while (resultSet.next()) {
                todoList.add(new Todo(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getBoolean(5),
                    dbPersons.findById(resultSet.getInt(6))));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Collection<Todo> findByAssignee(Person person) {

        return findByAssignee(person.getPersonId());
    }

    public Collection<Todo> findByUnassignedTodoItems() {

        List<Todo> todoList = new ArrayList<>();
        String SELECT_ALL = "SELECT * FROM todo_item WHERE assignee_id IS NULL";
        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlFindById = connection.prepareStatement(SELECT_ALL)
            ) {
            ResultSet resultSet = sqlFindById.executeQuery();

            while (resultSet.next()) {
                todoList.add(new Todo(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getBoolean(5),
                    dbPersons.findById(resultSet.getInt(6))));
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return todoList;
    }

    @Override
    public Todo update(Todo todo) {
        String UPDATE_PERSON = "UPDATE todo_item SET title = ?, description = ?, deadline = ?, done = ?, assignee_id = ? "
        + "WHERE todo_id = ?";

        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlUpdateTodo = connection.prepareStatement(UPDATE_PERSON)
            ) {
            sqlUpdateTodo.setString(1, todo.getTitle());
            sqlUpdateTodo.setString(2, todo.getDescription());
            sqlUpdateTodo.setDate(3, Date.valueOf(todo.getDeadline()));
            sqlUpdateTodo.setBoolean(4, todo.isDone());

            if (todo.getAssignee() != null) {
                sqlUpdateTodo.setInt(5, todo.getAssignee().getPersonId());
            } else {
                sqlUpdateTodo.setNull(5, 6);
            }
            sqlUpdateTodo.setInt(6, todo.getTodoId());
            sqlUpdateTodo.executeUpdate();
            return todo;

        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int todoId) {

        String DELETE_PERSON = "DELETE FROM todo_item WHERE todo_id = ?";
        try (
            Connection connection = DbConnection.mySqlConnection();
            PreparedStatement sqlDeleteCity = connection.prepareStatement(DELETE_PERSON)
            ) {
            sqlDeleteCity.setInt(1, todoId);
            if (sqlDeleteCity.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException | MySqlException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}