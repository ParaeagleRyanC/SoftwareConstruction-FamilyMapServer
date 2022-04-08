package Services;


import DataAccess.*;
import Results.ClearResult;

/**
 * This is the class that deals with Clear Service
 */
public class ClearService {

    /**
     * This is the method that clears the database and returns the result
     */
    public ClearResult clear() {
        ConnectionManager cm = new ConnectionManager();
        try {
            cm.openConnection();
            new UserDAO(cm.getConnection()).clear();
            new PersonDAO(cm.getConnection()).clear();
            new EventDAO(cm.getConnection()).clear();
            new AuthTokenDAO(cm.getConnection()).clear();

            cm.closeConnection(true);
            return new ClearResult("Clear succeeded!", true);

        } catch (DataAccessException e){
            e.printStackTrace();
            cm.closeConnection(false);
            return new ClearResult("Error: Clear failed!", false);
        }
    }
}
