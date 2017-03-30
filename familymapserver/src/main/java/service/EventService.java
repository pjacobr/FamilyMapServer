package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_access.AuthTokenDAO;
import data_access.EventDAO;
import data_access.Transaction;
import data_access.UserDAO;
import model.Event;
import model.User;
import result.EventResult;

/**
 * Created by jacob on 2/16/2017.
 */

public class EventService {
    String authToken = null;

    public EventService(String authToken) {
        this.authToken = authToken;
    }

    /**
     * get an event based on an event id.
     *
     * @param eventID the eventID
     * @return EventResult
     */
    public EventResult event(String eventID) {
        //The transactions work like this.
        Transaction trans = new Transaction();
        trans.openConnection(true);
        UserDAO userDAO = trans.getUser();
        EventDAO eventdao = trans.getEvent();
        AuthTokenDAO authTokenDAO = trans.getAuthToken();
        String username = null;

        if ((username = authTokenDAO.checkUser(authToken)) != null) {
            Event event = null;

               /*
            Get the username of the person associated with the authtoken
         */
            User user = null;
            try {
                user = userDAO.getUser(username);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // check if they gave me the wrong AuthToken
            if (user == null) {
                return new EventResult("Invalid Authtoken");
            }

            try {
                event = eventdao.getEvent(eventID, user.getUsername());
                if(event == null){
                    return new EventResult("Invalid Authtoken");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return new EventResult(e.getMessage());
            } finally {
                try {
                    trans.closeConnection(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return new EventResult(event.getDescendant(), event.getEventID(), event.getPersonID(), event.getLatitude(), event.getLongitude(), event.getCountry(), event.getCity(), event.getEventType(), event.getEventYear());
        } else {
            return new EventResult("AuthToken not valid, please login again");
        }

    }

    /**
     * get all the evetns for all the family members of the current user
     *
     * @return List of EventResults or just a message with the error.
     */
    public List<EventResult> event() {
        //The transactions work like this.
        Transaction trans = new Transaction();
        trans.openConnection(true);
        List<EventResult> eventResList = null;
        EventDAO eventdao = trans.getEvent();
        UserDAO userDAO = trans.getUser();
        AuthTokenDAO authTokenDao = trans.getAuthToken();
        /*
            Get the username of the person associated with the authtoken
         */
        User user = null;
        try {
            user = userDAO.getUser(authTokenDao.checkUser(authToken));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // check if they gave me the wrong AuthToken
        if (user == null) {
            return null;
        }
        List<Event> eventList = null;

        //GET ALL the events in the database
        try {
            eventList = eventdao.getEvents(user.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();

            try {
                trans.closeConnection(false);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return null;
        }


        eventResList = new ArrayList<>();
        for (Event e : eventList) {
            eventResList.add(new EventResult(e.getDescendant(), e.getEventID(), e.getPersonID(), e.getLatitude(), e.getLongitude(), e.getCountry(), e.getCity(), e.getEventType(), e.getEventYear()));
        }
        try {
            trans.closeConnection(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return eventResList;
    }
}
