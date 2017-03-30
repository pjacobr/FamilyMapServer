package result;

import model.AuthToken;

/**
 * Created by jacob on 2/16/2017.
 */

public class LoginResult {

    public String getUserName() {
        return userName;
    }

    public String getPersonID() {
        return personID;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthToken() {

        return authToken;
    }

    private String authToken = null;
    private String userName = null;
    private String personID= null;

    private String message = null;

    /**
     * if the login was an error give it a message
     * @param message error message that explains the error
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
