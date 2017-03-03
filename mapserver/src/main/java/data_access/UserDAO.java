package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import model.User;

/**
 * Created by jacob on 2/16/2017.
 */

public class UserDAO {

    Connection conn = null;
    /**
     * contructor for creating a connection
     */
    public UserDAO(Connection conn){
        this.conn = conn;
    }
    /**
     * Register a new user
     * @param user the information needed to add a new user into the system Should come from a person object and a userRequest
     * @return boolean
     */
    public void addUser(User user){
        String sql = "INSERT INTO Persons (username, password, email, first_name, last_name, person_id)" +
                "VALUES ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getEmail() +
                "','" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getPersonID() + "';";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a group of users
     * @param users a list of the information needed to add a new user into the system Should come from a person object and a userRequest
     * @return boolean
     */
    public void addUser(List<User> users){
        for(User user : users){
            addUser(user);
        }
    }

    /**
     * get a user
     * @param username the id of the user that we would like to get information
     * @return User
     */
    public User getUser(String username) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;


        try{
            String sql = "select * from Users where username = '" + username + "'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            //if there was not person that existed of that type
            if(rs.wasNull()){
                return null;
            }
            //make sure that the number of rows returned was not greater than 1
            if(rs.getFetchSize() > 1){
                //throw not more than one row found exception
                return null;
            }
            //information from the database about the person
            String firstName = null;
            String lastName = null;
            String email = null;
            char gender = 0;
            String personID = null;
            String password = null;

            while (rs.next()){
                firstName = rs.getString(5);
                lastName = rs.getString(6);
                email = rs.getString(4);
                gender = rs.getString(7).charAt(0);
                personID = rs.getString(8);
                password = rs.getString(3);
            }

            user = new User(username, password, email, firstName, lastName, gender, personID);
        }catch(SQLException e) {
            //ERROR
        }finally {
            return user;
        }
    }

    /**
     * get a list of users
     * @return List
     */
    public Set<User> getUser(){

        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        Set<User> users = new TreeSet<>();
        try{
            String sql = "select * from Users";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            //if there was not person that existed of that type
            if(rs.wasNull()){
                return null;
            }
            //make sure that the number of rows returned was not greater than 1
            if(rs.getFetchSize() > 1){
                //throw not more than one row found exception
                return null;
            }
            //information from the database about the person
            String firstName = null;
            String lastName = null;
            String email = null;
            char gender = 0;
            String personID = null;
            String password = null;
                String userName = null;
            //fill a set of all the users
            while (rs.next()){
                userName = rs.getString(2);
                firstName = rs.getString(5);
                lastName = rs.getString(6);
                email = rs.getString(4);
                gender = rs.getString(7).charAt(0);
                personID = rs.getString(8);
                password = rs.getString(3);
                user = new User(userName, password, email, firstName, lastName, gender, personID);
                users.add(user);
            }
        }catch(SQLException e) {
            //ERROR
        }finally {
            return users;
        }
    }

    /**
     * delete a user
     * @param username the information needed to delete a user into the system Should come from a person object and a userRequest
     * @return
     */
    public void delete(String username){
        try {
            //Go through all the events and add the event ** Make sure to delete anything connected.
            String sql = "delete from Users where username='" + username + "';";

            PreparedStatement stmt = null;
            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * delete all the users
     * @return
     */
    public void clear(){
        try {
            //Go through all the events and add the event
            String sql = "drop table Users;";
            String sql2 = "create table Users";
            PreparedStatement stmt = null;
            ResultSet rs = null;

            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();

            //make a statement with the sql2 string above to recreate the tables
            stmt = conn.prepareStatement(sql2);
            stmt.executeQuery();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Updates the information of the user in the database. The username can't change and the userID could not change
     *  Updates user event or relationship info
     * @param user
     * @return
     */
    public void updateUser(User user){

        try {
            //Go through all the events and add the event
            String sql = "update Users\n" +
                    "SET username='" + user.getUsername() + "',"
                    + "password='" + user.getPassword() + "',"
                    + "email='" + user.getEmail() + "',"
                    + "first_name='" + user.getFirstName() + "',"
                    + "last_name='" + user.getLastName() + "',"
                    + "gender='" + user.getGender() + "',"
                    + "person_id='" + user.getPersonID() + "',"
                    + "where personID='" + user.getPersonID() + "'s;";

            PreparedStatement stmt = null;
            ResultSet rs = null;

            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
