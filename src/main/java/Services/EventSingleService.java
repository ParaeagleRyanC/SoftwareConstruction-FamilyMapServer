package Services;

import DataAccess.*;
import Model.AuthToken;
import Model.Event;
import Results.EventSingleResult;

import java.sql.Connection;

/**
 * This is the class that deals with the service for ONE Event.
 */
public class EventSingleService {

    /**
     * This is the method that passes in an eventID and returns the result.
     */
    public EventSingleResult getSingleEvent(String eventID, String authtoken) {
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
                return new EventSingleResult("Error: Invalid Auth Token.", false);
            }

            // bad eventID
            Event event = eventDAO.find(eventID);
            if (event == null) {
                cm.closeConnection(false);
                return new EventSingleResult("Error: Invalid eventID.", false);
            }

            // event does not belong to current user
            if (!event.getAssociatedUsername().equals(authToken.getUsername())) {
                cm.closeConnection(false);
                return new EventSingleResult("Error: Requested event does not belong to this user", false);
            }

            cm.closeConnection(true);
            return new EventSingleResult(null, true, event.getAssociatedUsername(),
                    event.getEventID(), event.getPersonID(), event.getCountry(),
                    event.getCity(), event.getEventType(), event.getLatitude(),
                    event.getLongitude(),event.getYear());

        } catch (DataAccessException e) {
            e.printStackTrace();
            cm.closeConnection(false);
            return new EventSingleResult(e.getMessage(), false);
        }
    }

}





