package Services;

import DataAccess.ConnectionManager;
import DataAccess.DataAccessException;
import DataAccess.UserDAO;
import Model.User;
import Results.FillResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FillServiceTest {

    private User user = new User("username", "password",
            "email@byu.edu", "firstname",
            "last_name", "f", "personID_1");
    private FillResult result;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        ConnectionManager db = new ConnectionManager();
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
        uDao.insert(user);
        db.closeConnection(true);
    }


    @Test
    public void fillPass() {
        result = new FillService().fill(user.getUsername(), "3");
        assertTrue(result.getMessage().contains("Successfully"));
    }

    @Test
    public void fillFail() {
        result = new FillService().fill("wrongUsername", "3");
        assertTrue(result.getMessage().contains("Error"));
    }
}
