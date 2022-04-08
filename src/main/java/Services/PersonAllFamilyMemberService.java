package Services;


import DataAccess.AuthTokenDAO;
import DataAccess.ConnectionManager;
import DataAccess.DataAccessException;
import DataAccess.PersonDAO;
import Model.AuthToken;
import Model.Person;
import Results.PersonAllFamilyMemberResult;
import Results.PersonSingleMemberResult;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This is the class that deals with the service for ALL Persons.
 */
public class PersonAllFamilyMemberService {

    /**
     * This is the method that returns ALL family members of the current user.
     */
    public PersonAllFamilyMemberResult getAllPersons(String authtoken) {

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
                return new PersonAllFamilyMemberResult("Error: Invalid Auth Token.", false, null);
            }


            // retrieve data
            ArrayList<Person> personList = personDAO.findAll(authToken.getUsername());

            cm.closeConnection(true);
            return new PersonAllFamilyMemberResult(null, true, personList);

        } catch (DataAccessException e) {
            e.printStackTrace();
            cm.closeConnection(false);
            return new PersonAllFamilyMemberResult(e.getMessage(), false, null);
        }
    }
}
