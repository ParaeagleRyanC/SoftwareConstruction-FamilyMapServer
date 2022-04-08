package Request;

/**
 * This is the class forRequests of Login Service
 */
public class LoginRequest {

    /**
     * This is the username for the Login Request class
     */
    private String username;
    /**
     * This is the password for the Login Request class
     */
    private String password;

    /**
     * This is the constructor for the Login Request class
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
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
}
