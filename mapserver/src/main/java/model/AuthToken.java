package model;

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
    private int timeStamp;

    public AuthToken(String username, String authToken, int timeStamp) {
        this.authToken = authToken;
        this.username = username;
        this.timeStamp = timeStamp;
    }

    /**
     * Constructor
     * generates new instance of AuthToken with given username
     *
     * @param username
     */
    public AuthToken(String username) {
        this.username = username;
    }

    /**
     * Generate a unique Auth Token using the UUI interface
     * to generate a unique token for each auth
     *
     * @return
     */
    private String generateAuthToken() {
        return "";
    }

}
