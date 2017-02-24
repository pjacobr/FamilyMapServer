package data_access;

import java.sql.Connection;

import model.AuthToken;
import model.User;

/**
 * Created by jacob on 2/16/2017.
 */

public class AuthTokenDAO {

    private Connection conn= null;
    //create new database object
    public AuthTokenDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Check on an Auth Token for a specified user
     * @param user the info of the user that should be connected to the new authtoken
     * @return
     */
    public AuthToken getAuthToken(User user){

        return null;
    }

    /**
     * create a new auth token
     * @param user the info of the user that should be connected to the new authtoken
     * @return
     */
    public boolean addAuthToken(User user){
        return true;
    }


    public boolean deleteAuthToken(User user){
        return true;
    }
}
