package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "select * from AuthTokens where username = ?;";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            //get the relevant data
            if(rs.next()) {
                String userName = rs.getString(1);
                String authToken = rs.getString(2);
                //close the return Set and return the authtoken

                return new AuthToken(userName, authToken);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{

            try {
                if(stmt != null) {
                    stmt.close();
                }
                if( rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    /**
     *  Gets the authtoken from the authtoken class
     * @param authToken
     * @return username
     */
    public String checkUser(String authToken) {
        String sql = "select username from AuthTokens where authtoken = ?;";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, authToken);
            rs = stmt.executeQuery();
            //get the relevant data
            if(rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{

            try {
                if(stmt != null) {
                    stmt.close();
                }
                if( rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;

    }



    public void deleteAuthTokens(){
        String sql = "delete from AuthTokens where currenttime <= datetime('now','-1 hour');";

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
     * @param username the info of the user that should be connected to the new authtoken
     * @return
     */
    public void addAuthToken(String username) {
        //get the current time and date to add to the person
        String sql = "insert into AuthTokens(username, authtoken, currenttime) values ('" + username + "','" + UUID.randomUUID().toString() +  "',datetime('now','localtime'));";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, UUID.randomUUID().toString());
            stmt.executeUpdate();
            stmt.close();
            //get the relevant data
            //close the return Set and return the authtoken
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }




    public void updateAuthToken(String username) {
        System.out.println("hello world");
    }
}
