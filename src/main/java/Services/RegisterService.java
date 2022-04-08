package Services;


import DataAccess.*;
import DataGenertor.GenerationsGenerator;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Request.RegisterRequest;
import Results.RegisterResult;

import java.io.IOException;
import java.sql.Connection;
import java.util.Random;

/**
 * This is the class that deals with Register Service
 */
public class RegisterService {

    /**
     * This is the method that passes in a RegisterRequest and returns the result
     */
    public RegisterResult register(RegisterRequest request) {

        // invalid input
        if (request.getUsername() == null || request.getPassword() == null ||
            request.getEmail() == null || request.getFirstName() == null ||
            request.getLastName() == null || request.getGender() == null ) {
            return new RegisterResult("Error: Contains invalid input.",
                    false, null, null, null);
        }

        if (!request.getGender().equalsIgnoreCase("m") &&
                !request.getGender().equalsIgnoreCase("f")) {
            return new RegisterResult("Error: Contains invalid input.",
                    false, null, null, null);
        }

        ConnectionManager cm = new ConnectionManager();
        try {
            cm.openConnection();
            Connection conn = cm.getConnection();
            UserDAO userDAO = new UserDAO(conn);

            // username taken
            if (userDAO.find(request.getUsername()) != null) {
                cm.closeConnection(false);
                return new RegisterResult("Error: Username already taken.",
                        false, null, null, null);
            }

            // generate generations
            Random random = new Random();
            int year = 2045 + random.nextInt(15);
            GenerationsGenerator generations = new GenerationsGenerator(conn);
            Person person = generations.generatePerson(request.getGender(),
                    4, request.getUsername(), year);

            PersonDAO personDAO = new PersonDAO(conn);
            person.setFirstName(request.getFirstName());
            person.setLastName(request.getLastName());
            personDAO.updateName(person);

            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setGender(request.getGender());
            user.setPersonID(person.getPersonID());
            userDAO.insert(user);

            // remove death event from user, user is still alive
            EventDAO eventDAO = new EventDAO(conn);
            eventDAO.removeDeathEventFromUser(user.getPersonID());

            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
            AuthToken authToken = new AuthToken();
            authToken.setUsername(request.getUsername());
            authTokenDAO.insert(authToken);

            cm.closeConnection(true);
            return new RegisterResult(null,true, authToken.getAuthToken(),
                                        user.getUsername(), user.getPersonID());

        } catch (DataAccessException | IOException e) {
            e.printStackTrace();
            cm.closeConnection(false);
            return new RegisterResult(e.toString(), false,
                                        null, null, null);
        }
    }
}
