package service;

import java.sql.SQLException;

import data_access.EventDAO;
import data_access.PersonDAO;
import data_access.Transaction;
import data_access.UserDAO;
import model.Event;
import model.Person;
import model.User;
import request.LoadRequest;
import result.LoadResult;

/**
 * Created by jacob on 2/16/2017.
 */

public class LoadService {
    public LoadResult load(LoadRequest loader) {
        Transaction trans = new Transaction();
        trans.openConnection();

        //we want to load it with what
        Event[] events = loader.getEvents();
        Person[] persons = loader.getPeople();
        User[] users = loader.getUsers();

        try {
            trans.createTables();
            UserDAO userdao = trans.getUser();
            PersonDAO persondao = trans.getPerson();
            EventDAO eventdao = trans.getEvent();
            //fill the event table
            for (Event event : events) {
                eventdao.addEvent(event);
            }
            //fill the user table
            for (User user : users) {
                userdao.addUser(user);
            }
            //fill the person table
            for (Person person : persons) {
                persondao.addPerson(person);
            }
            return new LoadResult("Successfully returned " + users.length + " users, " + persons.length + " people, and " + events.length + " events");
        } catch (SQLException e) {
            e.printStackTrace();
            return new LoadResult(e.getMessage());
        }finally{
            try {
                trans.closeConnection(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
