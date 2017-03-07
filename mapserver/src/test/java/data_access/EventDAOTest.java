package data_access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Event;

import static org.junit.Assert.assertEquals;

/**
 * Created by jacob on 3/4/2017.
 */
public class EventDAOTest {
    Transaction transaction;

    @Before
    public void setUp() throws Exception {
        transaction = new Transaction();
        transaction.openConnection();
        transaction.createTables();
    }

    @After
    public void tearDown() throws Exception {
        transaction.closeConnection();
    }

    @Test
    public void addEvent() throws Exception {
        //get a new daoconnect
        EventDAO daoconnect = transaction.getEvent();
        Event event = new Event("1234", "Granppa", "id", 2323.3, 12342.2, "USA", "Provo", "baptism", 1994);

        daoconnect.addEvent(event);
        //Finally!!!! It works
        Event newEvent = daoconnect.getEvent("1234");
        //System.out.println(newUser.getEmail());
        assertEquals("Granppa", newEvent.getDescendant());
    }

    @Test
    public void addEvent1() throws Exception {
        //get a new daoconnect
        EventDAO daoconnect = transaction.getEvent();
        Event event = new Event("1234", "Granppa", "id", 2323.3, 12342.2, "USA", "Provo", "baptism", 1994);
        Event event2 = new Event("12341", "Granppa1", "id1", 232333.3, 123421111.2, "USA2", "Provo1", "baptism1", 1993);
        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(event2);
        daoconnect.addEvent(events);
        //Finally!!!! It works
        List<Event> events2 = new ArrayList<>();
        events2 = daoconnect.getEvent();

        //System.out.println(newUser.getEmail());
        assertEquals(2, events2.size());
    }

    @Test
    public void updateEvent() throws Exception {
        //get a new daoconnect
        EventDAO daoconnect = transaction.getEvent();
        //create the original event
        Event event = new Event("1234", "Granppa", "id", 2323.3, 12342.2, "USA", "Provo", "baptism", 1994);
        daoconnect.addEvent(event);
        assertEquals("Granppa", daoconnect.getEvent("1234").getDescendant());
        //create an package with all the updated info
        Event event2 = new Event("1234", "Granppa1", "id1", 232333.3, 123421111.2, "USA2", "Provo1", "baptism1", 1993);
        daoconnect.updateEvent(event2, "1234");
        //System.out.println(newUser.getEmail());
        assertEquals("Granppa1", daoconnect.getEvent("1234").getDescendant());
    }

    @Test
    public void deleteEvent() throws Exception {
        //get a new daoconnect
        EventDAO daoconnect = transaction.getEvent();
        //create the original event
        Event event = new Event("1234", "Granppa", "id", 2323.3, 12342.2, "USA", "Provo", "baptism", 1994);
        daoconnect.addEvent(event);
        assertEquals("Granppa", daoconnect.getEvent("1234").getDescendant());
        //create an package with all the updated info
        daoconnect.deleteEvent("1234");
        //System.out.println(newUser.getEmail());
        assertEquals(null, daoconnect.getEvent("1234"));
    }

    @Test
    public void clear() throws Exception {
        //get a new daoconnect
        EventDAO daoconnect = transaction.getEvent();
        //create the original event
        Event event = new Event("1234", "Granppa", "id", 2323.3, 12342.2, "USA", "Provo", "baptism", 1994);
        daoconnect.addEvent(event);
        assertEquals("Granppa", daoconnect.getEvent("1234").getDescendant());
        //create an package with all the updated info
        daoconnect.clear();
        //System.out.println(newUser.getEmail());
        assertEquals(null, daoconnect.getEvent("1234"));
    }

}