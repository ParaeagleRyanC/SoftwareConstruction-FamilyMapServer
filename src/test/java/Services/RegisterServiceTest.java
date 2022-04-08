package Services;

import DataAccess.ConnectionManager;
import DataAccess.DataAccessException;
import DataAccess.UserDAO;
import Model.User;
import Request.RegisterRequest;
import Results.RegisterResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterServiceTest {

    RegisterResult result;
    RegisterRequest request;
    UserDAO uDao;
    private ConnectionManager db = new ConnectionManager();

    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();

        request = new RegisterRequest("username", "password", "email@byu.edu",
                "firstname", "last_name", "f");
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        uDao.clear();
        db.closeConnection(true);
    }

    @Test
    public void registerPass() throws DataAccessException {
        result = new RegisterService().register(request);
        assertNull(result.getMessage());
    }

    @Test
    public void registerFail() throws DataAccessException {
        request.setPassword(null);
        result = new RegisterService().register(request);
        assertTrue(result.getMessage().contains("Error"));
    }
}
