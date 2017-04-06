package familyapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import result.EventResult;
import result.PersonResult;

/**
 * Created by jacob on 3/25/2017.
 */

public class ModelContainer {

    private static ModelContainer _instance;

    public void setPersons(List<PersonResult> persons) {
        this.persons = persons;
    }

    public void setEvents(List<EventResult> events) {
        this.events = events;
        //separate out the events by type so I can easily
        setEventList();
    }

    int numEventTypes = 0;

    public Map<String, List<EventResult>> getEventList() {
        return eventList;
    }

    public int getNumEventTypes() {

        return numEventTypes;
    }

    public void setEventList(){
        if(eventList == null){
            eventList = new HashMap<>();
        }
        //fill the list with Lists of the type
    }


    public void setHostPort(String hostPort) {
        this.hostPort = hostPort;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private List<PersonResult> persons;
    private List<EventResult> events;
    private Map<String, List<EventResult>> eventList;
    private String hostPort;
    private String ipAddress;
    private String username;
    private String password;

    public void setMother(String mother) {
        this.mother = mother;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBirthyear(int birthyear) {
        this.birthyear = birthyear;
    }

    public void setAuthtoken(AuthToken authtoken) {
        this.authtoken = authtoken;
    }

    private String firstName;
    private String lastName;
    private String mother;
    private String father;
    private String userId;
    Person p;
    int birthyear;
    private AuthToken authtoken;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String userID;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public AuthToken getAuthToken() {
        return authtoken;
    }

    //get the modelinstance
    public static ModelContainer getModelInstance(){
        if(_instance == null){
            _instance = new ModelContainer();
        }
            return _instance;
    }

    private ModelContainer(){

    }

    public List<PersonResult> getPersons() {
        return persons;
    }

    public List<EventResult> getEvents() {
        return events;
    }

    public void setAuthToken(String authToken, String username) {
        this.authtoken = new AuthToken(username, authToken);
    }

    public String getHostPort() {
        return hostPort;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
