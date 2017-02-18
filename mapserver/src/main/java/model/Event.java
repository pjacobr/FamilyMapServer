package model;

/**
 * Created by jacob on 2/16/2017.
 */

public class Event {
    //datamembers
    private String eventID = null;
    private String descendant = null;
    private String personID = null;
    private int latitude = 0;
    private int longitude = 0;
    private String country = null;
    private String city = null;
    private String eventType = null;
    private int eventYear = 0;

    /**
     * Constructor
     * creates

     * @param eventID id of the event
     * @param descendant id of direct descendant of current person
     * @param personID id of the current person
     * @param latitude latitude of an event
     * @param longitude longitude of the event
     * @param country name of the country where the event occured
     * @param city name of the city where the event occured
     * @param eventType the type of event
     * @param eventYear the year in which the event took place
     */
    public Event(String eventID, String descendant, String personID, int latitude, int longitude, String country, String city, String eventType, int eventYear) {
        this.eventID = eventID;
        this.descendant = descendant;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.eventYear = eventYear;
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

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
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
        return eventYear;
    }

    public void setEventYear(int eventYear) {
        this.eventYear = eventYear;
    }


}