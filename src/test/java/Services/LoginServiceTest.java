package Services;

import DataAccess.*;
import Model.User;
import Request.LoginRequest;
import Results.LoginResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginServiceTest {

    private ConnectionManager db = new ConnectionManager();
    private LoginRequest request = new LoginRequest("username", "password");
    private User user;
    private UserDAO uDao;
    private LoginResult result;

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();

        user = new User("username", "password",
                "email@byu.edu", "firstname",
                "last_name", "f", "personID_1");
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
        uDao.insert(user);

        db.closeConnection(true);
    }

    @Test
    public void loginPass() throws DataAccessException {
        result = new LoginService().login(request);
        assertNull(result.getMessage());
    }

    @Test
    public void loginFail() throws DataAccessException {
        request.setPassword("wrongPassword");
        result = new LoginService().login(request);
        assertTrue(result.getMessage().contains("Incorrect"));
    }
}
