package DataAccess;

import Model.Event;
import Model.Person;
import Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This is the Person Data Access Object class
 */
public class PersonDAO {

    private final Connection conn;

    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * This inserts a user into Person table
     *
     */
    public void insert(Person person) throws DataAccessException {
        String sql = "INSERT INTO person (personID, associatedUsername, firstName, lastName, " +
                "fatherID, motherID, spouseID, gender) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getFatherID());
            stmt.setString(6, person.getMotherID());
            stmt.setString(7, person.getSpouseID());
            stmt.setString(8, person.getGender());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a person into the database");
        }
    }

    /**
     * This finds a person in the Person table with the personID
     *
     */
    public Person find(String personID) throws DataAccessException {
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM person WHERE personID = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("fatherID"), rs.getString("motherID"),
                        rs.getString("spouseID"), rs.getString("gender"));;
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a person in the database");
        }
    }

    /**
     * This clears the Person table
     *
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM person";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the person table");
        }
    }

    public void updateName(Person person) throws DataAccessException {
        String sql = "UPDATE person SET firstName = ?, lastName = ? WHERE personID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting a person into the database");
        }
    }

    public void updatePersonID(Person person, String id) throws DataAccessException {
        String sql = "UPDATE person SET personID = ? WHERE personID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, id);
            stmt.setString(2, person.getPersonID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while updating personID");
        }
    }

    public void updateSpouseID(Person firstHalf, Person secondHalf) throws DataAccessException {
        String sql = "UPDATE person SET spouseID = ? WHERE personID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, firstHalf.getPersonID());
            stmt.setString(2, secondHalf.getPersonID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while updating spouse IDs");
        }
    }

    public void updateWifeLastname(Person husband, Person wife) throws DataAccessException {
        String sql = "UPDATE person SET lastName = ? WHERE personID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, husband.getLastName());
            stmt.setString(2, wife.getPersonID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while updating wife's last name");
        }
    }

    public void removeDataAssociatedWithUser(String username) throws DataAccessException {
        String sql = "DELETE FROM person WHERE associatedUsername = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while removing data associated with provided username");
        }
    }

    /**
     * This is the method to find all persons
     */
    public ArrayList<Person> findAll(String username) throws DataAccessException {
        ArrayList<Person> personList = new ArrayList<>();
        ResultSet rs;
        String sql = "SELECT * FROM person WHERE associatedUsername = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while (rs.next()) {
                personList.add(new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"),
                        rs.getString("fatherID"), rs.getString("motherID"),
                        rs.getString("spouseID"), rs.getString("gender")));;
            }
            return personList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding all family members");
        }
    }
}
