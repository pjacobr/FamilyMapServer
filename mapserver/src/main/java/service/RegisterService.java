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
        UserDAO userdao = newTrans.getUser();
        PersonDAO persondao = newTrans.getPerson();
        EventDAO eventdao = newTrans.getEvent();
        AuthTokenDAO authdao = newTrans.getAuthToken();

        try {
            //
            if(userdao.getUser(newRegister.getUsername()) != null){
                System.out.println("User already exists");
                return new RegisterResult("User Already Exists, unable to register!");
            }

            //Create a personID for the person
            String uuid = UUID.randomUUID().toString();
            persondao.addPerson(new Person(uuid, null, newRegister.getFirstName(), newRegister.getLastName(), newRegister.getGender(), null, null, null));
            //register the person as a person first
            userdao.addUser(new User(newRegister.getUsername(), newRegister.getPassword(), newRegister.getEmail(), newRegister.getFirstName(), newRegister.getLastName(), newRegister.getGender(), uuid));
            authdao.addAuthToken(newRegister.getUsername());
            //Fill their data
            //log them in
            return new RegisterResult(authdao.getAuthToken(newRegister.getUsername()).getAuthToken(), newRegister.getUsername(), uuid);

        } catch (SQLException e) {
            //SQL error found
            e.printStackTrace();
            return new RegisterResult(e.getMessage());
        }finally{
            newTrans.closeConnection();
        }
    }
}
