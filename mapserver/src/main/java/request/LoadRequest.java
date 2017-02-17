package request;


import model.Event;
import model.Person;
import model.User;

/**
 * Created by jacob on 2/16/2017.
 */

public class LoadRequest {
    //data to be loaded into the database
    private User[] users = null;
    private Person[] people = null;
    private Event[] events = null;

    /**
     * Create an object that will have lots of data to be passed
     * @param users
     * @param people
     * @param events
     */
    public LoadRequest(User[] users, Person[] people, Event[] events) {
        this.users = users;
        this.people = people;
        this.events = events;
    }
}
