package request;

/**
 * Created by jacob on 2/16/2017.
 */

public class RegisterRequest {
    //Private datamembers
    private String userName = null;
    private String password = null;
    private String email = null;
    private String firstName = null;
    private String lastName = null;
    private String gender;

    /**
     * <h1>Constructor</h1>
     *
     * @param username the username of the person being registerd
     * @param password the password of the
     * @param email the email of the person being registered
     * @param firstName the first name of the person being registered
     * @param lastName the last name of the person being registered
     * @param gender the gender of the person being registered
     */
    public RegisterRequest(String userName, String password, String email, String firstName, String lastName, String gender) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
