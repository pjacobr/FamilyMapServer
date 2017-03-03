package model;

/**
 * Created by jacob on 2/16/2017.
 */

public class User {


    private String username = null;
    private String password = null;
    private String email = null;
    private String firstName = null;
    private String lastName = null;
    private char gender;
    private String personID = null;

    /**
     * Constructor
     *
     * @param username  the user name of the user
     * @param password  the password of the user
     * @param email     the email of the user
     * @param firstName the first name of the usr
     * @param lastName  the last name of the user
     * @param gender    the gender of the user
     * @param personID  the id of the person that the user is connected with
     */
    public User(String username, String password, String email, String firstName, String lastName, char gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
