package model;

/**
 * Created by jacob on 2/16/2017.
 */

public class User {

    private String userName = null;
    private String password = null;
    private String email = null;
    private String firstName = null;
    private String lastName = null;
    private String gender;
    private String personID = null;

    /**
     * Constructor
     *
     * @param userName  the user name of the user
     * @param password  the password of the user
     * @param email     the email of the user
     * @param firstName the first name of the usr
     * @param lastName  the last name of the user
     * @param gender    the gender of the user
     * @param personID  the id of the person that the user is connected with
     */
    public User(String userName, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
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

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
