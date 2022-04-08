package Model;

import java.util.Objects;
import java.util.UUID;

/**
 * This is the Authorization Token Model class
 */
public class AuthToken {

    /**
     * This is the authorization token for the authToken object
     *
     */
    private String authToken;
    /**
     * This is the username for the authToken object
     *
     */
    private String username;

    /**
     * This is the Constructor for the Authorization Token Model class
     */
    public AuthToken(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    public AuthToken() {
        authToken = UUID.randomUUID().toString().substring(0,13);
        username = null;
    }

    // getters and setters
    public String getAuthToken() { return authToken; }
    public void setAuthToken(String authToken) { this.authToken = authToken; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthToken token = (AuthToken) o;
        return Objects.equals(authToken, token.authToken) &&
                Objects.equals(username, token.username);
    }
}
