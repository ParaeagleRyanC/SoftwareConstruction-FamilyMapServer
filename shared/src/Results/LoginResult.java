package Results;


/**
 * This is the class for Results of Login Service
 */
public class LoginResult extends ParentResult{

    /**
     * This is the authorization token for the Login Result class
     */
    private String authtoken;
    /**
     * This is the username for the Login Result class
     */
    private String username;
    /**
     * This is the personID for the Login Result class
     */
    private String personID;

    /**
     * This is the constructor for the Login Result class
     */
    public LoginResult(String message, boolean success, String authToken,
                       String username, String personID) {
        super(message, success);
        this.authtoken = authToken;
        this.username = username;
        this.personID = personID;
    }

    // getters and setters
    public String getAuthToken() {
        return authtoken;
    }
    public void setAuthToken(String authToken) {
        this.authtoken = authToken;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPersonID() {
        return personID;
    }
    public void setPersonID(String personID) {
        this.personID = personID;
    }

}
