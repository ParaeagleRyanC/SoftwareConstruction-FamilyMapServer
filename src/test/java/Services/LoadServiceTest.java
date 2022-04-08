package Services;

import DataAccess.*;
import Model.*;
import Request.LoadRequest;
import Results.LoadResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadServiceTest {

    User user = new User("username", "password",
            "email@byu.edu", "firstname",
            "last_name", "f", "personID_1");
    Person person = new Person("personID_1", "username",
            "first_name", "last_name", "fatherID",
            "motherID", "spouseID", "f");
    Event event = new Event("Biking_123A", "Gale", "personID_1",
            35.9f, 140.1f, "Japan", "Ushiku",
            "Biking_Around", 2016);

    private LoadResult result;
    private LoadRequest request;
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<Person> personList = new ArrayList<>();
    private ArrayList<Event> eventList = new ArrayList<>();
    private UserDAO uDao;
    private PersonDAO pDao;
    private EventDAO eDao;
    ConnectionManager db;


    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();
        Connection conn = db.getConnection();
        uDao = new UserDAO(conn);
        pDao = new PersonDAO(conn);
        eDao = new EventDAO(conn);
        uDao.clear();
        pDao.clear();
        eDao.clear();
        db.closeConnection(true);
        userList.add(user);
        personList.add(person);
        eventList.add(event);
        request = new LoadRequest(userList,personList,eventList);
    }

    @Test
    public void loadPass() throws DataAccessException {
        result = new LoadService().load(request);
        assertTrue(result.getMessage().contains("Successfully"));
    }

    @Test
    public void loadFail() throws DataAccessException {
        user.setUsername(null);
        userList.clear();
        userList.add(user);
        request = new LoadRequest(userList,personList,eventList);
        result = new LoadService().load(request);
        assertTrue(result.getMessage().contains("Error"));
    }
}
