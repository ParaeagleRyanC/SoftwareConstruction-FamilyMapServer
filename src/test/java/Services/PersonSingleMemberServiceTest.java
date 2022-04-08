package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.ConnectionManager;
import DataAccess.DataAccessException;
import DataAccess.PersonDAO;
import Model.AuthToken;
import Model.Person;
import Results.PersonAllFamilyMemberResult;
import Results.PersonSingleMemberResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonSingleMemberServiceTest {
    private ConnectionManager db;
    private PersonSingleMemberResult result;

    private Person person1;
    private PersonDAO pDao;
    private AuthToken authToken;
    private AuthTokenDAO aDao;
    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();

        person1 = new Person("personID_1", "username",
                "first_name", "last_name", "fatherID",
                "motherID", "spouseID", "f");
        authToken = new AuthToken("token", "username");
        Connection conn = db.getConnection();
        pDao = new PersonDAO(conn);
        aDao = new AuthTokenDAO(conn);
        pDao.clear();
        aDao.clear();
        pDao.insert(person1);
        aDao.insert(authToken);
        db.closeConnection(true);
    }


    @Test
    public void getSinglePersonsPass() {
        result = new PersonSingleMemberService().getSinglePerson(person1.getPersonID(), authToken.getAuthToken());
        assertNull(result.getMessage());
    }

    @Test
    public void getSinglePersonsFail() {
        result = new PersonSingleMemberService().getSinglePerson(person1.getPersonID(), "badToken");
        assertTrue(result.getMessage().contains("Invalid"));
    }

}
