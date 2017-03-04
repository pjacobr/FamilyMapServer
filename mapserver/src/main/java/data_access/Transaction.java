package data_access;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    //Create all the tables in the database
    public void createTables() {
        String sql = "drop table if exists Persons;" +
                "drop table if exists AuthTokens;" +
                "drop table if exists Users;" +
                "drop table if exists Events;" +
                "CREATE TABLE Persons\n " +
        "(\n" +
                "\tpersonID varchar(255) NOT NULL PRIMARY KEY,\n" +
                "\tdescendant varchar(255),\n" +
                "\tfirstname varchar(255) NOT NULL,\n" +
                "\tlastname varchar(255) NOT NULL,\n" +
                "\tgender varchar(1) NOT NULL,\n" +
                "\tfather varchar(255),\n" +
                "\tmother varchar(255),\n" +
                "\tspouse varchar(255),\n" +
                "\tCONSTRAINT ck_gender CHECK (gender in ('m', 'f'))\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Events\n" +
                "(\n" +
                "\teventID varchar(255) NOT NULL PRIMARY KEY UNIQUE,\n" +
                "\tdescendant varchar(255),\n" +
                "\tpersonID varchar(255) NOT NULL,\n" +
                "\tlatitude real not null,\n" +
                "\tlongitude real not null,\n" +
                "\tcountry varchar(255) NOT NULL,\n" +
                "\tcity varchar(255) NOT NULL,\n" +
                "\teventtype varchar(255) NOT NULL,\n" +
                "\tyear int NOT NULL,\n" +
                "\tFOREIGN KEY(personID) REFERENCES Person(personID)\n" +
                ");\n" +
                "\n" +
                "\n" +
                "CREATE TABLE AuthTokens\n" +
                "(\n" +
                "\tusername varchar(255) NOT NULL,\n" +
                "\tauthtoken varchar(255) NOT NULL,\n" +
                "\ttimestamp DATETIME NOT NULL,\n" +
                "\tFOREIGN KEY(username) REFERENCES User(username)\n" +
                ");\n" +
                "CREATE TABLE Users\n" +
                "(\n" +
                "\tusername varchar(255) NOT NULL UNIQUE,\n" +
                "\tpassword varchar(255) NOT NULL,\n" +
                "\temail varchar(255) NOT NULL UNIQUE,\n" +
                "\tfirstname varchar(255) NOT NULL,\n" +
                "\tlastname varchar(255) NOT NULL,\n" +
                "\tgender varchar(1),\n" +
                "\tpersonID varchar(255),\n" +
                "\tCONSTRAINT ck_gender CHECK (gender in ('m', 'f')),\n" +
                "\tFOREIGN KEY(personID) REFERENCES Person(personID)\n" +
                ");";

        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
