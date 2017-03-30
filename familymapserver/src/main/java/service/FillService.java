package service;

import java.sql.SQLException;
import java.util.List;

import data_access.AuthTokenDAO;
import data_access.EventDAO;
import data_access.PersonDAO;
import data_access.Transaction;
import data_access.UserDAO;
import model.Event;
import model.Person;
import model.User;
import request.FillRequest;
import result.FillResult;

/**
 * Created by jacob on 2/16/2017.
 */

public class FillService {


    public FillService() {

    }

    /**
     * fill the
     *
     * @param r the request of the person
     * @return
     */
    public FillResult fill(FillRequest r) {
        //START TRANSACTION
        Transaction trans = new Transaction();
        trans.openConnection(true);
        EventDAO eventdao = trans.getEvent();
        PersonDAO persondao = trans.getPerson();
        UserDAO userdao = trans.getUser();
        AuthTokenDAO authTokenDAO = trans.getAuthToken();

        try {
            //Get the current user
            User userInput = userdao.getUser(r.getUsername());

            if (userInput == null) {
                return new FillResult("User does not exist, please try again");
            }

            //save the data and delete all relavant data related to the person.
            Person p = persondao.getPerson(userInput.getPersonID());
            persondao.delete(p.getPersonID());

            //Add the current User back to the database
            persondao.addPerson(p);
            userdao.addUser(userInput);
            //authTokenDAO.addAuthToken(userInput.getUsername());

            //Create a data tree using data generator
            DataGenerator dg = new DataGenerator(p, r.getGenerations(), r.getUsername());
            dg.fillTrie();
            List<Person> peopleToAdd = dg.ancestors;
            List<Event> eventsToAdd = dg.eventList;

            //add the events to the database
            persondao.addPerson(peopleToAdd);
            eventdao.addEvent(eventsToAdd);
            return new FillResult("Successfully added " + peopleToAdd.size() + " persons and " + eventsToAdd.size() + " events to the database.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //FINISH TRANSACTION
                trans.closeConnection(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //generate the data

        return null;
    }

}
