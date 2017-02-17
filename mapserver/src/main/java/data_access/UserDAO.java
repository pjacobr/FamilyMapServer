package data_access;

import model.User;
import java.util.List;

/**
 * Created by jacob on 2/16/2017.
 */

public class UserDAO {
    /**
     * contructor for creating a connection
     */
    public UserDAO(){

    }

    /**
     * Register a new user
     * @param user
     * @return
     */
    public boolean addUser(User user){
        return true;
    }

    /**
     * Add a group of users
     * @param users
     * @return
     */
    public boolean addUser(List<User> users){
        return true;
    }

    /**
     * get a user
     * @param userID
     * @return
     */
    public User getUser(String userID) {
        return null;
    }

    /**
     * get a list of users
     * @return
     */
    public List<User> getUser(){
        return null;
    }

    /**
     * delete a user
     * @param user
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


}
