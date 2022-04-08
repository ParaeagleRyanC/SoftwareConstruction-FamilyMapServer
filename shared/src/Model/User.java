package Model;

import java.util.Objects;
import java.util.UUID;

/**
 * This is the User Model class
 */
public class User {

    /**
     * This is the username for the user object
     *
     */
    private String username;
    /**
     * This is the password for the user object
     *
     */
    private String password;
    /**
     * This is the email for the user object
     *
     */
    private String email;
    /**
     * This is the first name for the user object
     *
     */
    private String firstName;
    /**
     * This is the last name for the user object
     *
     */
    private String lastName;
    /**
     * This is the gender for the user object
     *
     */
    private String gender;
    /**
     * This is the person ID for the user object
     *
     */
    private String personID;

    /**
     * This is the Constructor for the User Model class
     */
    public User(String username, String password, String email, String firstName,
                String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public User() {
        username = UUID.randomUUID().toString().substring(0,13);
        password = null;
        email = null;
        firstName = null;
        lastName = null;
        gender = null;
        personID = null;
    }

    // getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPersonID() { return personID; }
    public void setPersonID(String personID) { this.personID = personID; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(personID, user.personID);
    }
}
