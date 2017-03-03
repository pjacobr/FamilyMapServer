package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import model.AuthToken;
import model.User;

/**
 * Created by jacob on 2/16/2017.
 */

public class AuthTokenDAO {

    private Connection conn = null;

    //create new database ob
    public AuthTokenDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Check on an Auth Token for a specified user
     *
     * @param username the info of the user that should be connected to the new authtoken
     * @return
     */
    public AuthToken getAuthToken(String username) {
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
            int timeStamp = rs.getInt(3);
            //close the return Set and return the authtoken
            rs.close();
            return new AuthToken(userName, authToken, timeStamp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    /**
     * create a new auth token
     *
     * @param user the info of the user that should be connected to the new authtoken
     * @return
     */
    public void addAuthToken(User user) {
        //get the current time and date to add to the person
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        Date dateobj = new Date();
        String sql = "insert into AuthTokens (username, authtoken, timestamp)"
                + "values ('" + user.getUsername() + "','" + UUID.randomUUID().toString()
                + "','" + df.format(dateobj) + "');";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            stmt.close();
            //get the relevant data
            //close the return Set and return the authtoken
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteAuthToken(String username) {
        //SQL to delete the person with given username
        String sql = "delete from AuthTokens where username='"
                + username + "';";
        //? Should we have a username that is
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            stmt.close();
            //get the relevant data
            //close the return Set and return the authtoken
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
