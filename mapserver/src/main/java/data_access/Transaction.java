package data_access;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by jacob on 2/23/2017.
 */

public class Transaction {
    static {
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    final private String dbName = "db" + File.separator + "familyMap.sqlite";
    final private String connectionURL = "jdbc:sqlite:" + dbName;
    private Connection conn = null;

    public Transaction(/* Request Object here? */) {

    }

    public boolean openConnection() {
        if (conn != null) {
            //check to see if the connection already open
            //System.out.println("Connection open");
            return false;
        }
        try {
            //Open a database connection
            conn = DriverManager.getConnection(connectionURL);

            //Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            // ERROR
            return false;
        } finally {
            //System.out.println("Success");
            return true;
        }
    }


    //Close the connection
    public boolean closeConnection() {
        //make sure that the connection exists before killing it
        if (conn == null) {
            //the connection does not exist
            return false;
        }
        //sets the connection to false
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            conn = null;
            return true;
        }
    }

    //Create a new event dao connector
    public EventDAO getEvent() {
        return new EventDAO(conn);
    }

    //Create a new Person dao object
    public PersonDAO getPerson() {
        return new PersonDAO(conn);
    }

    //Create a new user dao object
    public UserDAO getUser() {
        return new UserDAO(conn);
    }

    //Create a new AuthToken Dao Object
    public AuthTokenDAO getAuthToken() {
        return new AuthTokenDAO(conn);
    }


}
