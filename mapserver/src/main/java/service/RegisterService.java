package service;

import java.sql.SQLException;
import java.util.UUID;

import data_access.AuthTokenDAO;
import data_access.EventDAO;
import data_access.PersonDAO;
import data_access.Transaction;
import data_access.UserDAO;
import model.AuthToken;
import model.Person;
import model.User;
import request.FillRequest;
import request.RegisterRequest;
import result.RegisterResult;

/**
 * Created by jacob on 2/16/2017.
 */

public class RegisterService {
    public RegisterResult registerUser(RegisterRequest newRegister){
        int generations = 4;
        Transaction newTrans = new Transaction();
        newTrans.openConnection();
        //Database objects
        UserDAO userdao = newTrans.getUser();
        PersonDAO persondao = newTrans.getPerson();
        AuthTokenDAO authdao = newTrans.getAuthToken();

        try {
            //
            if(userdao.getUser(newRegister.getUsername()) != null){
                System.out.println("User already exists");
                return new RegisterResult("User Already Exists, unable to register!");
            }



            //Create a personID for the person
            String uuid = UUID.randomUUID().toString();
            String username = newRegister.getUsername();
            String password = newRegister.getPassword();
            String firstName = newRegister.getFirstName();
            String lastName = newRegister.getLastName();
            String email = newRegister.getEmail();
            String gender = newRegister.getGender();



            //add them as a Person and a user.
            persondao.addPerson(new Person(uuid, null, firstName, lastName, gender, null, null, null));
            User newUser = new User(username, password, email, firstName, lastName, gender, uuid);
            //register the person as a person first
            userdao.addUser(newUser);
            authdao.addAuthToken(username);
            //Fill their data
            //log them in
            return new RegisterResult(authdao.getAuthToken(username).getAuthToken(), username, uuid);
        } catch (SQLException e) {
            //SQL error found
            e.printStackTrace();
            return new RegisterResult(e.getMessage());
        }finally{
            try {
                newTrans.closeConnection(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
