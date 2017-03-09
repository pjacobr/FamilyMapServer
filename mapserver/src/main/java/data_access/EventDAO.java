package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Event;

/**
 * Created by jacob on 2/16/2017.
 */

public class EventDAO {

    private Connection conn = null;

    //Create new Event Database object
    public EventDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * Given the request, adds an event into the database
     * if the add was successfull, return true
     *
     * @param event
     * @return boolean
     */
    public void addEvent(Event event) throws SQLException {
        String sql = "INSERT INTO Events (eventID, descendant, personid, latitude, longitude, country, city, eventtype, year)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement stmt = null;
        //make a statement with the sql string above
        if(event.getCity() == null || event.getCountry() == null){
            System.out.println("WHat the?");
        }
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, event.getEventID());
        stmt.setString(2, event.getDescendant());
        stmt.setString(3, event.getPersonID());
        stmt.setDouble(4, event.getLatitude());
        stmt.setDouble(5, event.getLongitude());
        stmt.setString(6, event.getCountry());
        stmt.setString(7, event.getCity());
        stmt.setString(8, event.getEventType());
        stmt.setInt(9, event.getEventYear());
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * Given a list of requests to create a group of events, create a listof events.
     *
     * @param events
     * @return boolean
     */
    public void addEvent(List<Event> events) throws SQLException {

        //Go through all the events and add the events
        for (Event event : events) {
            addEvent(event);
        }
    }



    /**
     * Update an event in the database based on the info given to the event
     *
     * @param updateEvent the new information for the event
     * @param eventID     the id of the event that we are looking for
     * @return boolean
     */
    public void updateEvent(Event updateEvent, String eventID) throws SQLException {
        //Go through all the events and add the event
        String sql = "update Events\n" +
                "SET eventID='" + updateEvent.getEventID() + "',"
                + "descendant='" + updateEvent.getDescendant() + "',"
                + "personid='" + updateEvent.getPersonID() + "',"
                + "latitude='" + updateEvent.getLatitude() + "',"
                + "longitude='" + updateEvent.getLongitude() + "',"
                + "country='" + updateEvent.getCountry() + "',"
                + "city='" + updateEvent.getCity() + "',"
                + "eventtype='" + updateEvent.getEventType() + "',"
                + "year='" + updateEvent.getEventYear() + "'\n" +
                "where eventID='" + eventID + "';";

        PreparedStatement stmt = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }



    /**
     * get a specified event based on a given request
     *
     * @param eventid the eventid for the event that would like to get
     * @return EventResult
     */
    public Event getEvent(String eventid) throws SQLException {
        String sql = "select eventID, descendant, personID, latitude, longitude, country, city, eventtype, year from Events where eventID='" + eventid + "';";


        PreparedStatement stmt = null;
        ResultSet rs = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        if (rs.next()) {
            //Get the data that is returned
            String eventID = rs.getString(1);
            String descendant = rs.getString(2);
            String personID = rs.getString(3);
            double latitude = rs.getDouble(4);
            double longitude = rs.getDouble(5);
            String country = rs.getString(6);
            String city = rs.getString(7);
            String eventType = rs.getString(8);
            int year = rs.getInt(9);

            stmt.close();

            return new Event(eventID, descendant, personID, latitude, longitude, country, city, eventType, year);
        }
        return null;
    }

    /**
     * gets all instances of events in the database as a list
     *
     * @return
     */
    public List<Event> getEvent() throws SQLException {
        String sql = "select * from Events;";
        List<Event> events = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();


        while (rs.next()) {

            //Get the data that is returned
            String eventID = rs.getString(1);
            String descendant = rs.getString(2);
            String personID = rs.getString(3);
            int latitude = rs.getInt(4);
            int longitude = rs.getInt(5);
            String country = rs.getString(6);
            String city = rs.getString(7);
            String eventType = rs.getString(8);
            int year = rs.getInt(9);

            //Create a new event and add it to the list
            Event e = new Event(eventID, descendant, personID, latitude, longitude, country, city, eventType, year);
            events.add(e);
        }
        //close the necessary
        stmt.close();
        rs.close();
        if (events.size() > 0) {
            return events;
        }

        return null;
    }


    /**
     * delete a given event based on a request from the user
     *
     * @param eventID
     * @return
     */
    public void deleteEvent(String eventID) throws SQLException {

        //Go through all the events and add the event
        String sql = "delete from Events where eventID='" + eventID + "';";

        PreparedStatement stmt = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();


    }

    /**
     * delete all the events in the DB
     *
     * @return true if true
     */
    public void clear() throws SQLException {

        //Go through all the events and add the event
        String sql = "drop table Events;";
        String sql2 = "create table Events";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();

        //make a statement with the sql2 string above to recreate the tables
        stmt = conn.prepareStatement(sql2);
        stmt.executeQuery();
        stmt.close();
    }
}
