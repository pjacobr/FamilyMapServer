package request;

/**
 * Created by jacob on 2/16/2017.
 */

public class LoginRequest {
    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //private data members
    private String username = null;
    private String password = null;

    /**
     * Login constructor
     * @param username the username of the person logging in .
     * @param password the password of the person logging in
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
