package Services;

import DataAccess.AuthTokenDAO;
import DataAccess.ConnectionManager;
import DataAccess.DataAccessException;
import DataAccess.EventDAO;
import Model.AuthToken;
import Model.Event;
import Results.EventSingleResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventSingleServiceTest {

    private ConnectionManager db;
    private EventSingleResult result;

    private Event event1;
    private EventDAO eDao;
    private AuthToken authToken;
    private AuthTokenDAO aDao;
    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();

        event1 = new Event("Biking_123A", "username", "personID_1",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        authToken = new AuthToken("token", "username");
        Connection conn = db.getConnection();
        eDao = new EventDAO(conn);
        aDao = new AuthTokenDAO(conn);
        eDao.clear();
        aDao.clear();
        eDao.insert(event1);
        aDao.insert(authToken);
        db.closeConnection(true);
    }


    @Test
    public void getAllEventsPass() {
        result = new EventSingleService().getSingleEvent(event1.getEventID(), authToken.getAuthToken());
        assertNull(result.getMessage());
    }

    @Test
    public void getAllEventsFail() {
        result = new EventSingleService().getSingleEvent(event1.getEventID(), "badToken");
        assertTrue(result.getMessage().contains("Invalid"));
    }
}
