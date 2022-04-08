package Request;

/**
 * This is the class for Requests of Register Service
 */
public class RegisterRequest {

    /**
     * This is the username for the Register Request class
     */
    private String username;
    /**
     * This is the password for the Register Request class
     */
    private String password;
    /**
     * This is the email for the Register Request class
     */
    private String email;
    /**
     * This is the first name for the Register Request class
     */
    private String firstName;
    /**
     * This is the last name for the Register Request class
     */
    private String lastName;
    /**
     * This is the gender for the Register Request class
     */
    private String gender;

    /**
     * This is the constructor for the Register Request class
     */
    public RegisterRequest(String username, String password, String email,
                           String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
