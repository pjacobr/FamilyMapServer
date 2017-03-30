package request;


import model.Event;
import model.Person;
import model.User;

/**
 * Created by jacob on 2/16/2017.
 */

public class LoadRequest {
    public User[] getUsers() {
        return users;
    }

    public Person[] getPeople() {
        return persons;
    }

    public Event[] getEvents() {
        return events;
    }

    //data to be loaded into the database
    private User[] users = null;
    private Person[] persons = null;
    private Event[] events = null;

    /**
     * Create an object that will have lots of data to be passed
     * @param users the users that were loaded
     * @param persons the persons that were loaded
     * @param events the events that were loaded
     */
    public LoadRequest(User[] users, Person[] persons, Event[] events) {
        this.users = users;
        this.persons = persons;
        this.events = events;
    }
}
