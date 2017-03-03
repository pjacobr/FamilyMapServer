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


    public boolean dropTable() {
        //using connection


        return true;
    }

    public boolean checkTable() {
        //use connection and check if the table is there
        try {
            // Table does not exist
            String sql = "create table Events (\n"
                    + " eventID varchar(255) NOT NULL PRIMARY KEY UNIQUE,\n"
                    + " descendant varchar(255),\n"
                    + " person_id varchar(255) NOT NULL,\n"
                    + " latitude int not null,\n"
                    + " longitude int not null,\n"
                    + " country varchar(255) NOT NULL,\n"
                    + " city varchar(255) NOT NULL,\n"
                    + " event_type varchar(255) NOT NULL,\n"
                    + " year int NOT NULL,\n"
                    + " CONSTRAINT ck_eventType CHECK (event_type in ('Birth', 'Baptism', 'Christening', 'Marriage', 'Death')),\n"
                    + " FOREIGN KEY(person_id) REFERENCES Person(person_id)\n"
                    + ");";
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();


            return true;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        // check if "employee" table is there

        //if it is not there then make the table
        return false;
    }

    /**
     * Given the request, adds an event into the database
     * if the add was successfull, return true
     *
     * @param event
     * @return boolean
     */
    public boolean addEvent(Event event) {
        String sql = "INSERT INTO Events (eventID, descendant, person_id, latitude, longitude, country, city, event_type, year)\n" +
                "VALUES (" + "'" + event.getEventID() + "','" + event.getDescendant() + "','" + event.getPersonID() +
                "','" + event.getLatitude() + "','" + event.getLongitude() + "','" + event.getCountry() + "','"
                + event.getCity() + "','" + event.getEventType() + "','" + event.getEventYear() + "');";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Given a list of requests to create a group of events, create a listof events.
     *
     * @param events
     * @return boolean
     */
    public boolean addEvent(List<Event> events) {

        //Go through all the events and add the events
        for (Event event : events) {
            addEvent(event);
        }

        return true;

    }

    /**
     * Update an event in the database based on the info given to the event
     *
     * @param updateEvent the new information for the event
     * @param eventID     the id of the event that we are looking for
     * @return boolean
     */
    public boolean setEvent(Event updateEvent, String eventID) {
        try {
            //Go through all the events and add the event
            String sql = "update Events\n" +
                    "SET eventID='" + updateEvent.getEventID() + "',"
                    + "descendant='" + updateEvent.getDescendant() + "',"
                    + "person_id='" + updateEvent.getPersonID() + "',"
                    + "latitude='" + updateEvent.getLatitude() + "',"
                    + "longitude='" + updateEvent.getLongitude() + "',"
                    + "country='" + updateEvent.getCountry() + "',"
                    + "city='" + updateEvent.getCity() + "',"
                    + "event_type='" + updateEvent.getEventType() + "',"
                    + "year='" + updateEvent.getEventYear() + "'\n" +
                    "where eventID='" + eventID + ";";

            PreparedStatement stmt = null;
            ResultSet rs = null;


            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;


    }

    /**
     * get a specified event based on a given request
     *
     * @param eventid the eventid for the event that would like to get
     * @return EventResult
     */
    public Event getEvent(String eventid) {
        String sql = "select * from Events where eventID='" + eventid + "';";


        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;


            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
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

            stmt.close();

            return new Event(eventID, descendant, personID, latitude, longitude, country, city, eventType, year);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return null;
        }


    }

    /**
     * gets all instances of events in the database as a list
     *
     * @return
     */
    public List<Event> getEvent() {
        String sql = "select * from Events;";
        List<Event> events = new ArrayList<>();

        try {
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
            return events;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }


    /**
     * delete a given event based on a request from the user
     *
     * @param event
     * @return
     */
    public boolean deleteEvent(Event event) {
        try {
            //Go through all the events and add the event
            String sql = "delete from Events where eventID='" + event.getEventID() + "';";

            PreparedStatement stmt = null;
            ResultSet rs = null;


            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;


    }

    /**
     * delete all the events in the DB
     *
     * @return true if true
     */
    public boolean clear() {
        try {
            //Go through all the events and add the event
            String sql = "drop table Events;";
            String sql2 = "create table Events";
            PreparedStatement stmt = null;
            ResultSet rs = null;


            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();

            //make a statement with the sql2 string above to recreate the tables
            stmt = conn.prepareStatement(sql2);
            stmt.executeQuery();
            stmt.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }


}
