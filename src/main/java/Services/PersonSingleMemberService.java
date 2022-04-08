package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Results.ClearResult;
import Results.EventSingleResult;
import Results.PersonSingleMemberResult;

import java.sql.Connection;

/**
 * This is the class that deals with the service for ONE Person.
 */
public class PersonSingleMemberService {

    /**
     * This is the method that passes in a personID and returns the result.
     */
    public PersonSingleMemberResult getSinglePerson(String personID, String authtoken) {
        ConnectionManager cm = new ConnectionManager();
        try {
            cm.openConnection();
            Connection conn = cm.getConnection();

            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
            PersonDAO personDAO = new PersonDAO(conn);

            // bad token
            AuthToken authToken = authTokenDAO.find(authtoken);
            if (authToken == null) {
                cm.closeConnection(false);
                return new PersonSingleMemberResult("Error: Invalid Auth Token.", false);
            }

            // bad personID
            Person person = personDAO.find(personID);
            if (person == null) {
                cm.closeConnection(false);
                return new PersonSingleMemberResult("Error: Invalid personID.", false);
            }

            // person does not belong to current user
            if (!person.getAssociatedUsername().equals(authToken.getUsername())) {
                cm.closeConnection(false);
                return new PersonSingleMemberResult("Error: Requested person does not belong to this user", false);
            }

            cm.closeConnection(true);
            return new PersonSingleMemberResult(null, true, person.getAssociatedUsername(),
                    person.getPersonID(), person.getFirstName(), person.getLastName(),
                    person.getFatherID(), person.getMotherID(), person.getSpouseID(), person.getGender());

        } catch (DataAccessException e) {
            e.printStackTrace();
            cm.closeConnection(false);
            return new PersonSingleMemberResult(e.getMessage(), false);
        }
    }
}
