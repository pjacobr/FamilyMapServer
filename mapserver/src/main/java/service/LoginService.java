package service;

import java.sql.SQLException;

import data_access.AuthTokenDAO;
import data_access.Transaction;
import data_access.UserDAO;
import model.User;
import request.LoginRequest;
import result.LoginResult;

/**
 * Created by jacob on 2/16/2017.
 */

public class LoginService {

    public LoginResult login(LoginRequest l) {

        Transaction trans = new Transaction();
        trans.openConnection();

        //We need to get an auth token, make sure they are registered
        UserDAO userdao = trans.getUser();
        try {
            User user = userdao.getUser(l.getUserName());
            //if the user exists in the database
            if (user != null) {
                //check to see if the correct password is given
                if (user.getPassword() == l.getPassword()) {
                    AuthTokenDAO authdao = trans.getAuthToken();
                    authdao.addAuthToken(l.getUserName());
                    String authtoken = authdao.getAuthToken(l.getUserName()).getAuthToken();
                    //successfully logged in and got an authtoken
                    return new LoginResult(authtoken, l.getUserName(), user.getPersonID());
                }
                return new LoginResult("Incorrect Password");
            } else {
                return new LoginResult("Username not associated with an account");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new LoginResult(e.getMessage());
        } finally {
            trans.closeConnection();
        }
    }
}
