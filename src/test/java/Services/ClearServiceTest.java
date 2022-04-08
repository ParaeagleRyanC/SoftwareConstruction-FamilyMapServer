package Services;

import DataAccess.ConnectionManager;
import DataAccess.DataAccessException;
import DataAccess.*;
import Model.*;
import Results.ClearResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;


public class ClearServiceTest {

    private ConnectionManager db;
    private ClearResult result;

    private User user;
    private Person person;
    private Event event;
    private AuthToken authToken;

    private UserDAO uDao;
    private PersonDAO pDao;
    private EventDAO eDao;
    private AuthTokenDAO aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();
        user = new User("username", "password",
                "email@byu.edu", "firstname",
                "last_name", "f", "personID_1");
        person = new Person("personID_1", "username",
                "first_name", "last_name", "fatherID",
                "motherID", "spouseID", "f");
        event = new Event("Biking_123A", "Gale", "personID_1",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        authToken = new AuthToken("token", "username");
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        pDao = new PersonDAO(conn);
        eDao = new EventDAO(conn);
        aDao = new AuthTokenDAO(conn);
        uDao.clear();
        pDao.clear();
        eDao.clear();
        aDao.clear();
        uDao.insert(user);
        pDao.insert(person);
        eDao.insert(event);
        aDao.insert(authToken);
        db.closeConnection(true);
    }

    @Test
    public void clearPass() throws DataAccessException {
        result = new ClearService().clear();
        assertTrue(result.getMessage().contains("succeeded"));
    }

    @Test
    public void clearAnotherPass() throws DataAccessException {
        db.openConnection();
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        pDao = new PersonDAO(conn);
        eDao = new EventDAO(conn);
        aDao = new AuthTokenDAO(conn);
        uDao.clear();
        pDao.clear();
        eDao.clear();
        aDao.clear();
        db.closeConnection(true);
        result = new ClearService().clear();
        assertTrue(result.getMessage().contains("succeeded"));
    }
}
