package model;

/**
 * Created by jacob on 2/16/2017.
 */

public class Event {
    //datamembers

    private String eventType = null;
    private String personID = null;
    private String city = null;
    private String country = null;
    private double latitude = 0;
    private double longitude = 0;
    private int year = 0;
    private String descendant = null;
    private String eventID = null;

    /**
     * Constructor
     * creates
     *
     * @param eventID    id of the event
     * @param descendant id of direct descendant of current person
     * @param personID   id of the current person
     * @param latitude   latitude of an event
     * @param longitude  longitude of the event
     * @param country    name of the country where the event occured
     * @param city       name of the city where the event occured
     * @param eventType  the type of event
     * @param year  the year in which the event took place
     */
    public Event(String eventID, String descendant, String personID, double latitude, double longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.descendant = descendant;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEventYear() {
        return year;
    }

    public void setEventYear(int year) {
        this.year = year;
    }


}