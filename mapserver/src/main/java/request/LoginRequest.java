package request;

/**
 * Created by jacob on 2/16/2017.
 */

public class LoginRequest {
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    //private data members
    private String userName = null;
    private String password = null;

    /**
     * Login constructor
     * @param userName the username of the person logging in .
     * @param password the password of the person logging in
     */
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
