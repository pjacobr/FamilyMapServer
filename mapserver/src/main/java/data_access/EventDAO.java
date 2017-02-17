package data_access;

import model.Event;
import java.util.List;

/**
 * Created by jacob on 2/16/2017.
 */

public class EventDAO {
    /**
     * Given the request, adds an event into the database
     * if the add was successfull, return true
     * @param event
     * @return boolean
     */
    public boolean addEvent(Event event){
        return true;
    }

    /**
     * Given a list of requests to create a group of events, create a listof events.
     * @param events
     * @return boolean
     */
    public boolean addEvent(List<Event> events){
        return true;
    }

    /**
     * get a specified event based on a given request
     * @param event
     * @return EventResult
     */
    public Event getEvent(Event event){
        return null;
    }

    /**
     * gets all instances of events in the database as a list
     * @return
     */
    public List<Event> getEvent(){
        return null;
    }

    /**
     * delete a given event based on a request from the user
     * @param event
     * @return
     */
    public boolean deleteEvent(Event event){
        return true;
    }

    /**
     * delete all the events in the DB
     * @return true if true
     */
    public boolean clear(){
        return true;
    }



}
