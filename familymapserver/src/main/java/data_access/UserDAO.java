package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 * Created by jacob on 2/16/2017.
 */

public class UserDAO {

    Connection conn = null;

    /**
     * contructor for creating a connection
     */
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Register a new user
     *
     * @param user the information needed to add a new user into the system Should come from a person object and a userRequest
     * @return boolean
     */
    public void addUser(User user) throws SQLException {

        String sql = "INSERT INTO Users(username, password, email, firstname, lastname, username, personID)" +
                "VALUES (?,?,?,?,?,?,?);";
        PreparedStatement stmt = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        //inject the data into the sql query
        stmt.setString(1,user.getUsername());
        stmt.setString(2,user.getPassword());
        stmt.setString(3,user.getEmail());
        stmt.setString(4,user.getFirstName());
        stmt.setString(5,user.getLastName());
        stmt.setString(6,user.getUsername());
        stmt.setString(7,user.getPersonID());
        stmt.executeUpdate();
        stmt.close();

    }

    /**
     * Add a group of users
     *
     * @param users a list of the information needed to add a new user into the system Should come from a person object and a userRequest
     * @return boolean
     */
    public void addUser(List<User> users) throws SQLException {
        for (User user : users) {
            addUser(user);
        }
    }

    /**
     * get a user
     *
     * @param username the id of the user that we would like to get information
     * @return User
     */
    public User getUser(String username) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        String sql = "select * from Users where username = '" + username + "'";
        stmt = conn.prepareStatement(sql);

        rs = stmt.executeQuery();


        //make sure that the number of rows returned was not greater than
        //information from the database about the person
        String firstName = null;
        String lastName = null;
        String email = null;
        String gender = null;
        String personID = null;
        String password = null;

        if (rs.next()) {
            password = rs.getString(2);
            email = rs.getString(3);
            firstName = rs.getString(4);
            lastName = rs.getString(5);
            gender = rs.getString(6);
            personID = rs.getString(7);
            stmt.close();
            rs.close();

            return new User(username, password, email, firstName, lastName, gender, personID);
        }
        return null;
    }

    /**
     * get a user
     *
     * @param personID the id of the user that we would like to get information
     * @return User
     */
    public User getUserByID(String personID) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        String sql = "select username, password, email, firstname, lastname, gender, personID from Users where personID = '" + personID+ "'";
        stmt = conn.prepareStatement(sql);

        rs = stmt.executeQuery();


        //make sure that the number of rows returned was not greater than
        //information from the database about the person
        String username = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String gender = null;
         personID = null;
        String password = null;

        if (rs.next()) {
            username = rs.getString(1);
            password = rs.getString(2);
            email = rs.getString(3);
            firstName = rs.getString(4);
            lastName = rs.getString(5);
            gender = rs.getString(6);
            personID = rs.getString(7);
            stmt.close();
            rs.close();

            return new User(username, password, email, firstName, lastName, gender, personID);
        }
        return null;
    }

    /**
     * get a list of users
     *
     * @return List
     */
    public List<User> getUser() throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        List<User> users = new ArrayList<>();
        String sql = "select * from Users;";
        stmt = conn.prepareStatement(sql);

        rs = stmt.executeQuery();

        //information from the database about the person
        String firstName = null;
        String lastName = null;
        String email = null;
        String gender = null;
        String personID = null;
        String password = null;
        String userName = null;
        //fill a set of all the users
        while (rs.next()) {
            userName = rs.getString(1);
            password = rs.getString(2);
            email = rs.getString(3);
            firstName = rs.getString(4);
            lastName = rs.getString(5);
            gender = rs.getString(6);
            personID = rs.getString(7);
            user = new User(userName, password, email, firstName, lastName, gender, personID);
            users.add(user);
        }
        rs.close();

        return users;

    }

    /**
     * delete a user
     *
     * @param username the information needed to delete a user into the system Should come from a person object and a userRequest
     * @return
     */
    public void delete(String username) throws SQLException {
        //Go through all the events and add the event ** Make sure to delete anything connected.
        String sql = "delete from Users where username='" + username + "';";

        PreparedStatement stmt = null;
        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * delete all the users
     *
     * @return
     */
    public void clear() throws SQLException {

        //Go through all the events and add the event
        String sql = "drop table Users;";
        String sql2 = "CREATE TABLE Users\n" +
                "(\n" +
                "\tusername varchar(255) NOT NULL UNIQUE,\n" +
                "\tpassword varchar(255) NOT NULL,\n" +
                "\temail varchar(255) NOT NULL UNIQUE,\n" +
                " \tfirstname varchar(255) NOT NULL,\n" +
                "\tlastname varchar(255) NOT NULL,\n" +
                "\tgender varchar(1),\n" +
                "\tpersonID varchar(255),\n" +
                "\tCONSTRAINT ck_gender CHECK (gender in ('m', 'f')),\n" +
                "\tFOREIGN KEY(personID) REFERENCES Person(personID)\n" +
                ");";
        PreparedStatement stmt = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
        Statement stmt2 = null;

        //make a statement with the sql2 string above to recreate the tables
        stmt2 = conn.createStatement();
        stmt2.executeUpdate(sql2);
        stmt2.close();
    }

    /**
     * Updates the information of the user in the database. The username can't change and the userID could not change
     * Updates user event or relationship info
     *
     * @param user
     * @return
     */
    public void updateUser(User user) throws SQLException {

        //Go through all the events and add the event
        String sql = "update Users\n" +
                "SET username='" + user.getUsername() + "',\n" +
                "password='" + user.getPassword() + "',\n" +
                "email='" + user.getEmail() + "',\n" +
                "firstname='" + user.getFirstName() + "',\n" +
                "lastname='" + user.getLastName() + "',\n" +
                "gender='" + user.getGender() + "',\n" +
                "personID='" + user.getPersonID() + "' " +
                "where personID='" + user.getPersonID() + "';";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }
}
