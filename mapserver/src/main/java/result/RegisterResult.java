package result;

/**
 * Created by jacob on 2/16/2017.
 */

public class RegisterResult {


    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken(){
        return authToken;
    }


    String authToken = null;

    public String getUserName() {
        return userName;
    }

    public String getPersonID() {
        return personID;
    }

    public String getMessage() {
        return message;
    }

    String userName = null;
    String personID = null;
    String message = null;

    /**
     * Error result!
     * @param message
     */
    public RegisterResult(String message) {
        this.message = message;
    }

    /**
     *  Success body! Returns the recently registered user
     * @param authToken
     * @param userName
     * @param personID
     */
    public RegisterResult(String authToken, String userName, String personID) {
        this.authToken = authToken;
        this.userName = userName;
        this.personID = personID;
    }
}
