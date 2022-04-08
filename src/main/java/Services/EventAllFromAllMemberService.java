package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.Event;
import Results.EventAllFromAllMemberResult;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This is the class that deals with the service for ALL Events.
 */
public class EventAllFromAllMemberService {
    /**
     * This is the method that returns ALL events from ALL family members of the current user.
     */
    public EventAllFromAllMemberResult getAllEvents(String authtoken) {

        ConnectionManager cm = new ConnectionManager();
        try {
            cm.openConnection();
            Connection conn = cm.getConnection();

            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
            EventDAO eventDAO = new EventDAO(conn);

            // bad token
            AuthToken authToken = authTokenDAO.find(authtoken);
            if (authToken == null) {
                cm.closeConnection(false);
                return new EventAllFromAllMemberResult("Error: Invalid Auth Token.", false, null);
            }


            // retrieve data
            ArrayList<Event> eventList = eventDAO.findAll(authToken.getUsername());

            cm.closeConnection(true);
            return new EventAllFromAllMemberResult(null, true, eventList);

        } catch (DataAccessException e) {
            e.printStackTrace();
            cm.closeConnection(false);
            return new EventAllFromAllMemberResult(e.getMessage(), false, null);
        }
    }

}
