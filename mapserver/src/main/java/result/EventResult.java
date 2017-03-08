package result;

/**
 * Created by jacob on 2/16/2017.
 */

public class EventResult {
    //Event datamembers
    String descendant = null;
    String eventID = null;
    String personID = null;
    double latitude = 0;
    double longitude = 0;
    String country = null;
    String city = null;
    String eventType = null;
    int year = 0;


    String message = null;

    /**
     * Error body construtor
     * @param message
     */
    public EventResult(String message) {
        this.message = message;
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
