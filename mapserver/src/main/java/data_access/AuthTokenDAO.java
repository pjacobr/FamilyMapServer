package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.AuthToken;
import model.User;

/**
 * Created by jacob on 2/16/2017.
 */

public class AuthTokenDAO {

    private Connection conn= null;
    //create new database ob
    public AuthTokenDAO(Connection conn){
        this.conn = conn;
    }

    /**
     * Check on an Auth Token for a specified user
     * @param username the info of the user that should be connected to the new authtoken
     * @return
     */
    public AuthToken getAuthToken(String username){
        String sql = "select authToken from AuthTokens where userName='" + username + "';";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            stmt.close();
            //get the relevant data
            String authToken = rs.getString(1);
            String userName = rs.getString(2);
            String timeStamp = rs.getString(3);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }


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
