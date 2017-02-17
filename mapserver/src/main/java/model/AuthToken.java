package model;

/**
 * Created by jacob on 2/16/2017.
 */


public class AuthToken {
    // authToken will be the unique token that is used
    //username will be the username associated with the AuthToken
    private String authToken = null;
    private String username = null;

    /**
     * Generate a unique Auth Token using the UUI interface
     * to generate a unique token for each auth
     *
     * @return
     */
    private String generateAuthToken(){
        return "";
    }

    /**
     * Constructor
     * generates new instance of AuthToken with given username
     * @param username
     */
    public AuthToken(String username){
        this.username = username;
    }

}
