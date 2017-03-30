package result;

/**
 * Created by jacob on 2/16/2017.
 */

public class EventResult {
    //Event datamembers
    String descendant = null;
    String eventID = null;
    String personID = null;
    Double latitude = null;
    Double longitude = null;
    String country = null;
    String city = null;
    String eventType = null;
    Integer year = null;


    String message = null;

    /**
     * Error body construtor
     * @param message
     */
    public EventResult(String message) {
        this.message = message;
    }

    public String getDescendant() {
        return descendant;
    }

    public String getEventID() {
        return eventID;
    }

    public String getPersonID() {
        return personID;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEventType() {
        return eventType;
    }

    public int getYear() {
        return year;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Success body constructor
     * @param descendant
     * @param eventID
     * @param personID
     * @param latitude
     * @param longitude
     * @param country
     * @param city
     * @param eventType
     * @param year
     */
    public EventResult(String descendant, String eventID, String personID, double latitude, double longitude, String country, String city, String eventType, int year) {
        this.descendant = descendant;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }
}
