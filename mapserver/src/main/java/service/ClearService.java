package service;

import java.sql.SQLException;

import data_access.EventDAO;
import data_access.PersonDAO;
import data_access.Transaction;
import data_access.UserDAO;
import model.AuthToken;
import model.Event;
import result.ClearResult;

/**
 * Created by jacob on 2/16/2017.
 */

public class ClearService {
    /**
     * go through the database and clear all the data
     * @return
     */
    public ClearResult clear(){
        Transaction trans = new Transaction();
        trans.openConnection();
    //try and drop the tables and recreate them. I don't know why I event have clear functions
        try {
            trans.createTables();
            return new ClearResult("Clear succeeded");
        } catch (SQLException e) {
            e.printStackTrace();
            return new ClearResult(e.getMessage());
        }finally{
            trans.closeConnection();
        }

    }
}
