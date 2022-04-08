package Services;

import DataAccess.*;
import Request.LoadRequest;
import Results.LoadResult;
import Model.*;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * This is the class that deals with Load Service
 */
public class LoadService {

    /**
     * This is the method that passes in a LoadRequest and returns the result
     */
    public LoadResult load(LoadRequest request) {

        ConnectionManager cm = new ConnectionManager();

        try {
            cm.openConnection();
            Connection conn = cm.getConnection();

            UserDAO userDAO = new UserDAO(conn);
            PersonDAO personDAO = new PersonDAO(conn);
            EventDAO eventDAO = new EventDAO(conn);
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);

            // clear all data
            userDAO.clear();
            personDAO.clear();
            eventDAO.clear();
            authTokenDAO.clear();

            ArrayList<User> users = request.getUsers();
            ArrayList<Person> persons = request.getPersons();
            ArrayList<Event> events = request.getEvents();

            int numUsers = 0;
            int numPersons = 0;
            int numEvents = 0;

            // load User data
            for (int index = 0; index < users.size(); index++) {
                if (isUserInfoValid(users.get(index))) {
                    if (userDAO.find(users.get(index).getUsername()) != null) {
                        cm.closeConnection(false);
                        return new LoadResult("Error: User already exists", false);
                    }
                    userDAO.insert(users.get(index));
                    numUsers++;
                }
                else {
                    cm.closeConnection(false);
                    return new LoadResult("Error: Missing user data", false);
                }
            }

            // load Person data
            for (int index = 0; index < persons.size(); index++) {
                if (isPersonInfoValid(persons.get(index))) {
                    if (userDAO.find(persons.get(index).getPersonID()) != null) {
                        cm.closeConnection(false);
                        return new LoadResult("Error: Person already exists", false);
                    }
                    personDAO.insert(persons.get(index));
                    numPersons++;
                }
                else {
                    cm.closeConnection(false);
                    return new LoadResult("Error: Missing person data", false);
                }
            }

            // load Event data
            for (int index = 0; index < events.size(); index++) {
                if (isEventInfoValid(events.get(index))) {
                    if (eventDAO.find(events.get(index).getEventID()) != null) {
                        cm.closeConnection(false);
                        return new LoadResult("Error: Event already exists", false);
                    }
                    eventDAO.insert(events.get(index));
                    numEvents++;
                }
                else {
                    cm.closeConnection(false);
                    return new LoadResult("Error: Missing event data", false);
                }
            }

            cm.closeConnection(true);
            String resultMsg = "Successfully added " + numUsers + " users, " +
                    numPersons + " persons, and " + numEvents + " events to the database.";
            return new LoadResult(resultMsg, true);

        } catch (DataAccessException e) {
            e.printStackTrace();
            cm.closeConnection(false);
            return new LoadResult(e.toString(), false);
        }
    }

    private boolean isUserInfoValid(User user) {
        return user.getUsername() != null && user.getPassword() != null &&
                user.getEmail() != null && user.getFirstName() != null &&
                user.getLastName() != null && user.getGender() != null &&
                user.getPersonID() != null;
    }

    private boolean isPersonInfoValid(Person person) {
        return person.getPersonID() != null && person.getAssociatedUsername() != null &&
                person.getFirstName() != null && person.getLastName() != null &&
                person.getGender() != null;
    }

    private boolean isEventInfoValid(Event event) {
        return event.getEventID() != null && event.getAssociatedUsername() != null &&
                event.getPersonID() != null && event.getCountry() != null &&
                event.getCity() != null && event.getEventType() != null;
    }

}
