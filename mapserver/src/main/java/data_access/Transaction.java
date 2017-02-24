package data_access;
import java.io.File;
import java.sql.*;
import java.sql.Connection;

/**
 * Created by jacob on 2/23/2017.
 */

public class Transaction {
    Connection conn = null;
    String dbName = "db" + File.separator + "familyMap.sqlite";
    String connectionURL = "jdbc:sqlite:" + dbName;

    public Transaction(/* Request Object here? */){
        try{
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }catch(ClassNotFoundException e){
            //Error! Could not load database driver
        }
    }

    public boolean openConnection() {
        try {
            //Open a database connection
            conn = DriverManager.getConnection(connectionURL);

            //Start a transaction
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            // ERROR
            return false;
        } finally{
            //System.out.println("Success");
            return true;
        }
    }


    //Close the connection
    public boolean closeConnection(){
        //sets the connection to false
        try{
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            conn = null;
            return true;
        }
    }

    /**
     * Get the connection
     * @return Connection
     */
    public Connection getConnection(){
        return conn;
    }

    public EventDAO getEvent(){
        return new EventDAO(conn);
    }

}
