package request;

/**
 * Created by jacob on 2/16/2017.
 */

public class RegisterRequest {
    //Private datamembers
    private String username = null;
    private String password = null;
    private String email = null;
    private String firstName = null;
    private String lastName = null;
    private char gender;

    /**
     * <h1>Constructor</h1>
     *
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     */
    public RegisterRequest(String username, String password, String email, String firstName, String lastName, char gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }
}
