package data_access;

import model.AuthToken;
import model.User;

/**
 * Created by jacob on 2/16/2017.
 */

public class AuthTokenDAO {
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
}
