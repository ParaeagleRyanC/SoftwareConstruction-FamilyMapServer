package DataAccess;

import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {

    private ConnectionManager db;
    private User bestUser;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();
        bestUser = new User("test_username_1", "test_password",
                "test_email@byu.edu", "test_first_name",
                "test_last_name", "test_gender", "test_personID_2");
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
    }

    @AfterEach
    public void tearDown() { db.closeConnection(false); }

    @Test
    public void userInsertPass() throws DataAccessException {
        uDao.insert(bestUser);
        User compareTest = uDao.find(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void userInsertFail() throws DataAccessException {
        uDao.insert(bestUser);
        assertThrows(DataAccessException.class, () -> uDao.insert(bestUser));
    }

    @Test
    public void userFindPass() throws DataAccessException {
        uDao.insert(bestUser);
        User compareTest = uDao.find(bestUser.getUsername());
        assertNotNull(compareTest);
        assertEquals(bestUser, compareTest);
    }

    @Test
    public void userFindFail() throws DataAccessException {
        User compareTest = uDao.find("no_such_username");
        assertNull(compareTest);
    }

    @Test
    public void userClearPass() throws DataAccessException {
        uDao.insert(bestUser);
        assertNotNull(uDao.find(bestUser.getUsername()));
        uDao.clear();
        assertNull(uDao.find(bestUser.getUsername()));
    }

    @Test
    public void userClearAnotherPass() throws DataAccessException {
        uDao.clear();
        assertNull(uDao.find(bestUser.getUsername()));
    }
}
