package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        String sql = "select authtoken from AuthTokens where username='" + username + "';";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            stmt.close();
            //get the relevant data
            String authToken = rs.getString(1);
            String userName = rs.getString(2);
            //close the return Set and return the authtoken
            rs.close();
            return new AuthToken(userName, authToken);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /*
function callEveryHour() {
    setInterval(yourFunction, 1000 * 60 * 60);
}

var nextDate = new Date();
if (nextDate.getMinutes() === 0) { // You can check for seconds here too
    callEveryHour()
} else {
    nextDate.setHours(d.getHours() + 1);
    nextDate.setMinutes(0);
    nextDate.setSeconds(0);// I wouldn't do milliseconds too ;)

    var difference = nextDate - new Date();
    setTimeout(callEveryHour, difference);
}

     */

    public void deleteAuthTokens(){
        String sql = "delete from AuthTokens where timestamp <= datetime('now','-1 hour');";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
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
        String sql = "insert into AuthTokens(username, authtoken, timestamp) values (?,?,datetime('now','localtime'));";
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, UUID.randomUUID().toString());
            stmt.executeUpdate();
            stmt.close();
            //get the relevant data
            //close the return Set and return the authtoken
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAuthToken(List<User> users) {
        for (User user : users) {
            addAuthToken(user);
        }
    }


    public void updateAuthToken(String username) {
        //SQL to delete the person with given username
        String sql = "update AuthTokens set timestamp='datetime('now','localtime')\n" +
                "where username='" + username + "';";
        //? Should we have a username that is
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
            //get the relevant data
            //close the return Set and return the authtoken
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
