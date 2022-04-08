package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.Event;
import Results.EventAllFromAllMemberResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;


public class EventAllFromAllMemberServiceTest {

    private ConnectionManager db;
    private EventAllFromAllMemberResult result;

    private Event event1;
    private Event event2;
    private EventDAO eDao;
    private AuthToken authToken;
    private AuthTokenDAO aDao;
    @BeforeEach
    public void setUp() throws DataAccessException {
        db = new ConnectionManager();

        event1 = new Event("Biking_123A", "username", "personID_1",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        event2 = new Event("Biking_123B", "username", "personID_1",
                35.9f, 140.1f, "Japan", "Ushiku",
                "Biking_Around", 2016);
        authToken = new AuthToken("token", "username");
        Connection conn = db.getConnection();
        eDao = new EventDAO(conn);
        aDao = new AuthTokenDAO(conn);
        eDao.clear();
        aDao.clear();
        eDao.insert(event1);
        eDao.insert(event2);
        aDao.insert(authToken);
        db.closeConnection(true);
    }


    @Test
    public void getAllEventsPass() {
        result = new EventAllFromAllMemberService().getAllEvents(authToken.getAuthToken());
        assertNull(result.getMessage());
    }

    @Test
    public void getAllEventsFail() {
        result = new EventAllFromAllMemberService().getAllEvents("badToken");
        assertTrue(result.getMessage().contains("Invalid"));
    }
}
