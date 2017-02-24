package data_access;

import java.sql.Connection;
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
    public UserDAO(Connection conn){
        this.conn = conn;
    }
    /**
     * Register a new user
     * @param user the information needed to add a new user into the system Should come from a person object and a userRequest
     * @return boolean
     */
    public boolean addUser(User user){
        
        return true;
    }

    /**
     * Add a group of users
     * @param users a list of the information needed to add a new user into the system Should come from a person object and a userRequest
     * @return boolean
     */
    public boolean addUser(List<User> users){
        return true;
    }

    /**
     * get a user
     * @param userID the id of the user that we would like to get information
     * @return User
     */
    public User getUser(String userID) {
        return null;
    }

    /**
     * get a list of users
     * @return List
     */
    public List<User> getUser(){



        return null;
    }

    /**
     * delete a user
     * @param user the information needed to delete a user into the system Should come from a person object and a userRequest
     * @return
     */
    public boolean delete(User user){
        return true;
    }

    /**
     * delete all the users
     * @return
     */
    public boolean clear(){
        return true;
    }

    /**
     *  Updates the information of the user in the database. The username can't change and the userID could not change
     *  Updates user event or relationship info
     * @param user
     * @return
     */
    public boolean updateUser(User user){

        return true;
    }

}
