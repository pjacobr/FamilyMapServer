package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    private void fillPerson(String username, int generations) {

    }


    /**
     * fill the
     *
     * @param r the request of the person
     * @return
     */
    public FillResult fill(FillRequest r) {
        Transaction trans = new Transaction();
        trans.openConnection();
        EventDAO eventdao = trans.getEvent();
        PersonDAO persondao = trans.getPerson();
        UserDAO userdao = trans.getUser();

        try {
            User userInput = userdao.getUser(r.getUsername());
            if (userInput == null) {
                return new FillResult("User does not exist, please try again");
            }
            Person p = persondao.getPerson(userInput.getPersonID());
            DataGenerator dg = new DataGenerator(p);
            dg.fillTrie();
            List<Person> peopleToAdd = dg.ancestors;
            List<Event> eventsToAdd = dg.eventList;
            //add the events to the database
            persondao.addPerson(peopleToAdd);
            eventdao.addEvent(eventsToAdd);
            return new FillResult("Successfully added" + peopleToAdd.size() + " persons and " + eventsToAdd.size() + " events to the database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            trans.closeConnection();
        }
        //generate the data

        return null;
    }

}
