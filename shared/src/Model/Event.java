package Model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * This is the Event Model class
 */
public class Event {

    /**
     * This is the event ID for the event object
     *
     */
    private String eventID;
    /**
     * This is the associatedUsername for the event object
     *
     */
    private String associatedUsername;
    /**
     * This is the person ID for the event object
     *
     */
    private String personID;
    /**
     * This is the latitude for the event object
     *
     */
    private float latitude;
    /**
     * This is the longitude for the event object
     *
     */
    private float longitude;
    /**
     * This is the country for the event object
     *
     */
    private String country;
    /**
     * This is the city for the event object
     *
     */
    private String city;
    /**
     * This is the event type for the event object
     *
     */
    private String eventType;
    /**
     * This is the year for the event object
     *
     */
    private int year;

    /**
     * This is the Constructor for the Event Model class
     */
    public Event(String eventID, String username, String personID,
                 float latitude, float longitude, String country,
                 String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = username;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public Event() {
        eventID = UUID.randomUUID().toString().substring(0,13);
        associatedUsername = null;
        personID = null;
        latitude = 0;
        longitude = 0;
        country = null;
        city = null;
        eventType = null;
        year = 0;
    }

    // getters and setters
    public String getEventID() { return eventID; }
    public void setEventID(String eventID) { this.eventID = eventID; }
    public String getAssociatedUsername() { return associatedUsername; }
    public void setAssociatedUsername(String associatedUsername) { this.associatedUsername = associatedUsername; }
    public String getPersonID() { return personID; }
    public void setPersonID(String personID) { this.personID = personID; }
    public float getLatitude() { return latitude; }
    public void setLatitude(float latitude) { this.latitude = latitude; }
    public float getLongitude() { return longitude; }
    public void setLongitude(float longitude) { this.longitude = longitude; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(eventID, event.eventID) &&
                Objects.equals(associatedUsername, event.associatedUsername) &&
                Objects.equals(personID, event.personID) &&
                Objects.equals(latitude, event.latitude) &&
                Objects.equals(longitude, event.longitude) &&
                Objects.equals(country, event.country) &&
                Objects.equals(city, event.city) &&
                Objects.equals(eventType, event.eventType) &&
                Objects.equals(year, event.year);
    }
}
