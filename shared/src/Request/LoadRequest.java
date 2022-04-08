package Request;

import Model.Event;
import Model.Person;
import Model.User;
import java.util.ArrayList;

/**
 * This is the class for Requests of Load Service
 */
public class LoadRequest {

    /**
     * This is an array of User Objects in the LoadRequest class
     */
    private ArrayList<User> users;
    /**
     * This is an array of Person Objects in the LoadRequest class
     */
    private ArrayList<Person> persons;
    /**
     * This is an array of Event Objects in the LoadRequest class
     */
    private ArrayList<Event> events;

    /**
     * This is the constructor for the Load Request class
     */
    public LoadRequest(ArrayList<User> users, ArrayList<Person> persons, ArrayList<Event> events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }

    // getters and setters
    public ArrayList<User> getUsers() {
        return users;
    }
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    public ArrayList<Person> getPersons() {
        return persons;
    }
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
