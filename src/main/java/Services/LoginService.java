package Services;


import DataAccess.*;
import Model.AuthToken;
import Model.Person;
import Model.User;
import Request.LoginRequest;
import Results.LoginResult;
import Results.RegisterResult;

import java.sql.Connection;

/**
 * This is the class that deals with Login Service
 */
public class LoginService {


    /**
     * This is the method that passes in a LoginRequest and returns the result
     */
    public LoginResult login(LoginRequest request) {

        System.out.println(request);

        // invalid input
        if (request.getUsername() == null || request.getPassword() == null) {
            return new LoginResult("Error: Contains invalid input.",
                    false, null, null, null);
        }

        ConnectionManager cm = new ConnectionManager();
        try {
            cm.openConnection();
            Connection conn = cm.getConnection();
            UserDAO userDAO = new UserDAO(conn);

            // bad username
            if (userDAO.find(request.getUsername()) == null) {
                cm.closeConnection(false);
                return new LoginResult("Error: Username does not exist.",
                        false, null, null, null);
            }

            User user = userDAO.find(request.getUsername());
            if (!user.getPassword().equals(request.getPassword())) {
                cm.closeConnection(false);
                return new LoginResult("Error: Password Incorrect.",
                        false, null, null, null);
            }

            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
            AuthToken authToken = new AuthToken();
            authToken.setUsername(user.getUsername());
            authTokenDAO.insert(authToken);

            cm.closeConnection(true);
            return new LoginResult(null, true, authToken.getAuthToken(),
                    user.getUsername(), user.getPersonID());

        } catch (DataAccessException e) {
            e.printStackTrace();
            cm.closeConnection(false);
            return new LoginResult(e.toString(), false,
                    null, null, null);
        }
    }

}
