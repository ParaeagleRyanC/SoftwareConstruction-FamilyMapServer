package Results;

/**
 * This is the class for Results of Single Event Service
 */
public class EventSingleResult extends ParentResult{

    /**
     * This is the username of which this event belongs to for the Event ID class
     */
    private String associatedUsername;
    /**
     * This is the event ID for the Event ID Result class
     */
    private String eventID;
    /**
     * This is the person ID for the Event ID Result class
     */
    private String personID;
    /**
     * This is the country for the Event ID Result class
     */
    private String country;
    /**
     * This is the city for the Event ID Result class
     */
    private String city;
    /**
     * This is the event type for the Event ID Result class
     */
    private String eventType;
    /**
     * This is the latitude of the event location for the Event ID Result class
     */
    private float latitude;
    /**
     * This is the longitude of the event location for the Event ID Result class
     */
    private float longitude;
    /**
     * This is the year of the event location for the Event ID Result class
     */
    private int year;

    /**
     * This is the constructor for the EventSingle Result class
     */
    public EventSingleResult(String message, boolean success, String associatedUsername,
                         String eventID, String personID, String country, String city,
                         String eventType, float latitude, float longitude, int year) {
        super(message, success);
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public EventSingleResult(String message, boolean success) { super(message, success); }

    // getters and setters
    public String getAssociatedUsername() {
        return associatedUsername;
    }
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }
    public String getEventID() {
        return eventID;
    }
    public void setEventID(String eventID) {
        this.eventID = eventID;
    }
    public String getPersonID() {
        return personID;
    }
    public void setPersonID(String personID) {
        this.personID = personID;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
    public float getLongitude() {
        return longitude;
    }
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
}
