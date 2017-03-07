package model;

import java.util.UUID;

/**
 * Created by jacob on 2/16/2017.
 */


public class AuthToken {
    /**
     * @param authToken will be the unique token that is used
     * @param username will be the username associated with the AuthToken
     */
    private String authToken = null;
    private String username = null;


    public AuthToken(String username, String authToken) {
        this.authToken = authToken;
        this.username = username;

    }


    /**
     * Constructor
     * generates new instance of AuthToken with given username
     *
     * @param username
     */
    public AuthToken(String username) {
        this.authToken = UUID.randomUUID().toString();
        this.username = username;
    }


    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
