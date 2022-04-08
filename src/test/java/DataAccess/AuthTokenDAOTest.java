package DataAccess;

import Model.AuthToken;
import Model.Person;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class AuthTokenDAOTest {

    private ConnectionManager db;
    private AuthToken bestToken;
    private AuthTokenDAO aDao;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();
        bestToken = new AuthToken("test_token", "test_username_2");
        Connection conn = db.getConnection();
        aDao = new AuthTokenDAO(conn);
        aDao.clear();
    }

    @AfterEach
    public void tearDown() { db.closeConnection(false); }

    @Test
    public void authTokenInsertPass() throws DataAccessException {
        aDao.insert(bestToken);
        AuthToken compareTest = aDao.find(bestToken.getAuthToken());
        assertNotNull(compareTest);
        assertEquals(bestToken, compareTest);
    }

    @Test
    public void authTokenInsertFail() throws DataAccessException {
        aDao.insert(bestToken);
        assertThrows(DataAccessException.class, () -> aDao.insert(bestToken));
    }

    @Test
    public void authTokenFindPass() throws DataAccessException {
        aDao.insert(bestToken);
        AuthToken compareTest = aDao.find(bestToken.getAuthToken());
        assertNotNull(compareTest);
        assertEquals(bestToken, compareTest);
    }

    @Test
    public void authTokenFindFail() throws DataAccessException {
        AuthToken compareTest = aDao.find("no_such_token");
        assertNull(compareTest);
    }

    @Test
    public void authTokenClearPass() throws DataAccessException {
        aDao.insert(bestToken);
        assertNotNull(aDao.find(bestToken.getAuthToken()));
        aDao.clear();
        assertNull(aDao.find(bestToken.getAuthToken()));
    }

    @Test
    public void authTokenClearAnotherPass() throws DataAccessException {
        aDao.clear();
        assertNull(aDao.find(bestToken.getAuthToken()));
    }

}
