package result;

import model.AuthToken;

/**
 * Created by jacob on 2/16/2017.
 */

public class LoginResult {
    String authToken = null;
    String userName = null;
    String personID= null;

    String message = null;

    /**
     * if the login was an error give it a message
     * @param message
     */
    public LoginResult(String message) {
        this.message = message;
    }

    /**
     * create a login result
     * @param authToken
     * @param userName
     * @param personID
     */
    public LoginResult(String authToken, String userName, String personID) {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }
}
