package Results;

/**
 * This is the class for Results of Single Person Service
 */
public class PersonSingleMemberResult extends ParentResult {
    /**
     * This is the username of this person for the Single Person Result class
     */
    private String associatedUsername;
    /**
     * This is the person ID for the Single Person Result class
     */
    private String personID;
    /**
     * This is the first name of this person for the Single Person Result class
     */
    private String firstName;
    /**
     * This is the last name of this person for the Single Person Result class
     */
    private String lastName;
    /**
     * This is the person ID of the father of this person for the Single Person Result class
     * Can be null
     */
    private String fatherID;
    /**
     * This is the person ID of the mother of this person for the Single Person Result class
     * Can be null
     */
    private String motherID;
    /**
     * This is the person ID of the spouse of this person for the Single Person Result class
     * Can be null
     */
    private String spouseID;
    /**
     * This is the gender of this person for the Single Person Result class
     */
    private String gender;

    /**
     * This is the constructor for the Single Person Result class
     */
    public PersonSingleMemberResult(String message, boolean success, String associatedUsername,
                          String personID, String firstName, String lastName,
                          String fatherID, String motherID, String spouseID, String gender) {
        super(message, success);
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
        this.gender = gender;
    }

    public PersonSingleMemberResult(String message, boolean success) { super(message, success); }

    // getters and setters
    public String getAssociatedUsername() {
        return associatedUsername;
    }
    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }
    public String getPersonID() {
        return personID;
    }
    public void setPersonID(String personID) {
        this.personID = personID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFatherID() {
        return fatherID;
    }
    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }
    public String getMotherID() {
        return motherID;
    }
    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }
    public String getSpouseID() {
        return spouseID;
    }
    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
