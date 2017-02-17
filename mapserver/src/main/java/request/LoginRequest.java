package request;

/**
 * Created by jacob on 2/16/2017.
 */

public class LoginRequest {
    //private data members
    private String userName = null;
    private String password = null;

    /**
     * Login constructor
     * @param userName
     * @param password
     */
    public LoginRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
