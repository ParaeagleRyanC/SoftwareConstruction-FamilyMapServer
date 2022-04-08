package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.Event;
import Model.Person;
import Results.EventAllFromAllMemberResult;
import Results.PersonAllFamilyMemberResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;


public class PersonAllFamilyMemberServiceTest {

    private ConnectionManager db;
    private PersonAllFamilyMemberResult result;

    private Person person1;
    private Person person2;
    private PersonDAO pDao;
    private AuthToken authToken;
    private AuthTokenDAO aDao;
    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();

        person1 = new Person("personID_1", "username",
                "first_name", "last_name", "fatherID",
                "motherID", "spouseID", "f");
        person2 = new Person("personID_2", "username",
                "first_name", "last_name", "fatherID",
                "motherID", "spouseID", "f");
        authToken = new AuthToken("token", "username");
        Connection conn = db.getConnection();
        pDao = new PersonDAO(conn);
        aDao = new AuthTokenDAO(conn);
        pDao.clear();
        aDao.clear();
        pDao.insert(person1);
        pDao.insert(person2);
        aDao.insert(authToken);
        db.closeConnection(true);
    }


    @Test
    public void getAllPersonsPass() {
        result = new PersonAllFamilyMemberService().getAllPersons(authToken.getAuthToken());
        assertNull(result.getMessage());
    }

    @Test
    public void getAllPersonsFail() {
        result = new PersonAllFamilyMemberService().getAllPersons("badToken");
        assertTrue(result.getMessage().contains("Invalid"));
    }
}
