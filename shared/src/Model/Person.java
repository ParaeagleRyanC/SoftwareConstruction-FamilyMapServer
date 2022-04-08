package Model;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * This is the Person Model class
 */
public class Person {

    /**
     * This is the person ID for the person object
     *
     */
    private String personID;
    /**
     * This is the associatedUsername for the person object
     *
     */
    private String associatedUsername;
    /**
     * This is the first name for the person object
     *
     */
    private String firstName;
    /**
     * This is the last name for the person object
     *
     */
    private String lastName;
    /**
     * This is the gender for the person object
     *
     */
    private String gender;
    /**
     * This is the father ID for the person object
     *
     */
    private String fatherID;
    /**
     * This is the mother ID for the person object
     *
     */
    private String motherID;
    /**
     * This is the spouse ID for the person object
     *
     */
    private String spouseID;


    /**
     * This is the Constructor for the Person Model class
     */
    public Person(String personID, String username, String firstName,
                  String lastName, String fatherID, String motherID,
                  String spouseID, String gender) {
        this.personID = personID;
        this.associatedUsername = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.gender = gender;
    }

    public Person() {
        personID = UUID.randomUUID().toString().substring(0,13);
        associatedUsername = null;
        firstName = null;
        lastName = null;
        fatherID = null;
        motherID = null;
        spouseID = null;
        gender = null;
    }

    // getters and setters
    public String getPersonID() { return personID; }
    public void setPersonID(String personID) { this.personID = personID; }
    public String getAssociatedUsername() { return associatedUsername; }
    public void setAssociatedUsername(String associatedUsername) { this.associatedUsername = associatedUsername; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getFatherID() { return fatherID; }
    public void setFatherID(String fatherID) { this.fatherID = fatherID; }
    public String getMotherID() { return motherID; }
    public void setMotherID(String motherID) { this.motherID = motherID; }
    public String getSpouseID() { return spouseID; }
    public void setSpouseID(String spouseID) { this.spouseID = spouseID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(personID, person.personID) &&
                Objects.equals(associatedUsername, person.associatedUsername) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(gender, person.gender) &&
                Objects.equals(fatherID, person.fatherID) &&
                Objects.equals(motherID, person.motherID) &&
                Objects.equals(spouseID, person.spouseID);
    }
}
