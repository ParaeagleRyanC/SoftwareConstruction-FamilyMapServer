package DataGenertor;

import DataAccess.DataAccessException;
import DataAccess.EventDAO;
import Model.Event;
import Model.Person;

import java.io.IOException;
import java.sql.Connection;

public class EventGenerator {


    private static Connection conn;

    public EventGenerator(Connection conn) {
        EventGenerator.conn = conn;
    }

    public static void birthGenerator(Person person, int year) throws IOException {
        LocationGenerator.Location location = LocationGenerator.locationGenerator();
        int birthYear = year - 20;
        Event event = new Event();
        event.setAssociatedUsername(person.getAssociatedUsername());
        event.setPersonID(person.getPersonID());
        event.setLatitude(Float.parseFloat(location.latitude));
        event.setLongitude(Float.parseFloat(location.longitude));
        event.setCountry(location.country);
        event.setCity(location.city);
        event.setYear(birthYear);
        event.setEventType("Birth");

        try {
            EventDAO eventDAO = new EventDAO(conn);
            eventDAO.insert(event);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void deathGenerator(Person person, int year) throws IOException {
        LocationGenerator.Location location = LocationGenerator.locationGenerator();
        int deathYear = year + 40;
        Event event = new Event();
        event.setAssociatedUsername(person.getAssociatedUsername());
        event.setPersonID(person.getPersonID());
        event.setLatitude(Float.parseFloat(location.latitude));
        event.setLongitude(Float.parseFloat(location.longitude));
        event.setCountry(location.country);
        event.setCity(location.city);
        event.setYear(deathYear);
        event.setEventType("Death");


        try {
            EventDAO eventDAO = new EventDAO(conn);
            eventDAO.insert(event);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    public static void marriageGenerator(Person husband, Person wife, int year) throws IOException {
        LocationGenerator.Location location = LocationGenerator.locationGenerator();
        int marriageYear = year + 15;

        Event husbandEvent = new Event();
        husbandEvent.setAssociatedUsername(husband.getAssociatedUsername());
        husbandEvent.setPersonID(husband.getPersonID());
        husbandEvent.setLatitude(Float.parseFloat(location.latitude));
        husbandEvent.setLongitude(Float.parseFloat(location.longitude));
        husbandEvent.setCountry(location.country);
        husbandEvent.setCity(location.city);
        husbandEvent.setYear(marriageYear);
        husbandEvent.setEventType("Marriage");

        try {
            EventDAO eventDAO = new EventDAO(conn);
            eventDAO.insert(husbandEvent);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        Event wifeEvent = new Event();
        wifeEvent.setAssociatedUsername(wife.getAssociatedUsername());
        wifeEvent.setPersonID(wife.getPersonID());
        wifeEvent.setLatitude(Float.parseFloat(location.latitude));
        wifeEvent.setLongitude(Float.parseFloat(location.longitude));
        wifeEvent.setCountry(location.country);
        wifeEvent.setCity(location.city);
        wifeEvent.setYear(marriageYear);
        wifeEvent.setEventType("Marriage");

        try {
            EventDAO eventDAO = new EventDAO(conn);
            eventDAO.insert(wifeEvent);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
