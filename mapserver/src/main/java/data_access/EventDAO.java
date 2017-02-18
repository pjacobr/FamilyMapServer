package data_access;

import java.util.List;

import model.Event;

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
     * Update an event in the database based on the info given to the event
     * @param updateEvent the new information for the event
     * @param eventID the id of the event that we are looking for
     * @return boolean
     */
    public boolean setEvent(Event updateEvent, String eventID){
        return true;
    }

    /**
     * get a specified event based on a given request
     * @param eventid the eventid for the event that would like to get
     * @return EventResult
     */
    public Event getEvent(String eventid){
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
