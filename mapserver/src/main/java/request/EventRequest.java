package request;

/**
 * Created by jacob on 2/16/2017.
 */

public class EventRequest {
    private String eventID = null;

    /**
     * Get the id of the event
     * @return
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Create the event request that has the evetn that is being requested
     * @param eventID the id of the event requested
     */
    public EventRequest(String eventID) {
        this.eventID = eventID;
    }
}
