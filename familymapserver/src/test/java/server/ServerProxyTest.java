package server;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import model.Event;
import model.Person;
import model.User;
import request.FillRequest;
import request.LoadRequest;
import request.LoginRequest;
import request.PersonRequest;
import request.RegisterRequest;
import result.ClearResult;
import result.EventResult;
import result.FillResult;
import result.LoadResult;
import result.LoginResult;
import result.PersonResult;
import result.RegisterResult;

import static org.junit.Assert.*;

/**
 * Created by jacob on 3/10/2017.
 */
public class ServerProxyTest {
    ServerProxy serverProxy = null;
    RegisterRequest rg = null;
    @Before
    public void setUp() throws Exception {
        rg = new RegisterRequest("pjacobr", "test", "jacobp1794@byu.edu", "Jacob", "Pettingill", "m");
        serverProxy = new ServerProxy("Auth_Token", "10.10.148.99", "8080");
        //ClearResult cleared = serverProxy.clear();
        RegisterResult result = serverProxy.register(rg);
        serverProxy.authToken = result.getAuthToken();
    }

    @After
    public void tearDown() throws Exception {
        serverProxy.clear();
    }


    @Test
    public void login() throws Exception {
        LoginResult result = serverProxy.login(new LoginRequest("pjacobr", "test"));
        serverProxy.authToken = result.getAuthToken();
        assertEquals("pjacobr", result.getUserName());
    }

    @Test
    public void clear() throws Exception {
        ClearResult result = serverProxy.clear();
        assertEquals("Clear succeeded", result.getMessage());
    }

    @Test
    public void fill() throws Exception {
        FillResult result = serverProxy.fill(new FillRequest("pjacobr"));
        assertEquals(false, result.getMessage().isEmpty());
    }

    @Test
    public void load() throws Exception {
        Event[] events = new Event[2];
        Person[] persons = new Person[2];
        User[] users = new User[2];
        persons[0] =  new Person("personOne","pjacobr","John", "Doe", "m", null, null, null);
        persons[1] = new Person("personTwo","pjacobr","Jan", "Kill", "f", null, null, null);

        events[0] = new Event("thisEvent", "pjacobr", "personOne", 100.2, 50.4, "USA", "folsom", "death", 1970);
        events[1] = new Event("thisEvent1", "pjacobr", "personTwo", -100.2, -50.4, "MOTHERRUSSIA", "New York", "marriage", 1985);

        users[0] = new User("personOne", "test", "email@byu.edu", "John", "Doe", "m", "personOne");
        users[1] = new User("personTwo", "test1", "jacobp@byu.edu", "Jan", "Kill", "f", "personOne");

        LoadResult result = serverProxy.load(new LoadRequest(users, persons,events));
        assertEquals("Successfully returned 2 users, 2 people, and 2 events",result.getMessage());
    }

    @Test
    public void person() throws Exception {

        List<PersonResult> rs = serverProxy.person();
        String personID = null;
        for(PersonResult result : rs){
            personID = result.getPersonID();
            break;
        }

        PersonResult resultat = serverProxy.person(new PersonRequest(personID));

        assertEquals(resultat.getPersonID(), personID);


    }

    @Test
    public void person1() throws Exception {
        PersonRequest rq = new PersonRequest("pjacobr");
        List<PersonResult> results = serverProxy.person();
        assertEquals(results.size(), 30);
    }

    @Test
    public void event() throws Exception {
        List<EventResult> results = serverProxy.event();
        EventResult eventGet = null;

        //EventResult e = serverProxy.event(eventGet.getEventID());
        assertEquals(false, results.isEmpty());
       //assertEquals(e.getEventID(), eventGet.getPersonID());
    }
}