package familyapp;

import com.amazon.geo.mapsv2.model.Marker;

import java.util.ArrayList;
import java.util.Collections;
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
    int numEventTypes = 0;

    /*
     * Map Markers associated with the events that they are attached to
     * Key is Map Pin Marker
     * Value is the associated Map Key
     */
    private Map<Marker, EventResult> markerInfo;

    /**
     * Event ID associated with a given marker
     * Key is the event ID
     * Value is the associated Marker
     */
    private Map<String, Marker> eventMarker;
    //List of all the People associated with the current user
    private List<PersonResult> persons;
    // List of all the event results for the current User
    private List<EventResult> events;
    /*
    * Events by Type
    * Key is the type of the Event
    * Value is the Event associated with the event
    */
    private Map<String, List<EventResult>> eventList;
    /*
    * People associated by ID
    *   KEY is person ID
    *   VALUE is Person Event
    */
    private Map<String, PersonResult> accessPersons;

    /*
        Information for Current User
     */
    private String hostPort;
    private String ipAddress;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String mother;
    private String father;
    private String userId;
    int birthyear;
    private AuthToken authtoken;
    private String email;
    private String userID;
    private String gender;
    private boolean isMapActivity = false;


    //*******************************

    //Currently pressed marker Person/Event/Marker
    PersonResult curPerson;
    EventResult curEvent;
    //Settings
    // lines from the selected
    private boolean familyLines = true;
    private boolean spouseLines = true;
    private boolean lifeLines = true;

    //Map Type
    private boolean mapChanged = false;



    private String mapType = "Normal";

    private String familyLineColor = "Yellow";
    private String spouseLineColor = "Blue";
    private String lifeLineColor = "Green";

    /*********************************************************************************
     * Setters and Getters
     *
     ********************************************************************************/
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
        Collections.sort(events);
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

    public void setPersons(List<PersonResult> persons) {
        this.persons = persons;
        accessPersons = new HashMap<String, PersonResult>();

        for(PersonResult person : this.persons){
            accessPersons.put(person.getPersonID(), person);
        }
    }

    public Map<String, PersonResult> getAccessPersons() {
        return accessPersons;
    }

    public void setEvents(List<EventResult> events) {
        Collections.sort(events);
        this.events = events;
        //separate out the events by type so I can easily
        setEventList();
    }

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

    public Map<String, Marker> getEventMarker() {
        if(eventMarker == null){
            eventMarker = new HashMap<String, Marker>();
        }
        return eventMarker;
    }

    public void setEventMarker(Map<String, Marker> eventMarker) {
        this.eventMarker = eventMarker;
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

    public Map<Marker, EventResult> getMarkerInfo() {
        return markerInfo;
    }

    public void setMarkerInfo(Map<Marker, EventResult> markerInfo) {
        this.markerInfo = markerInfo;
    }



    public boolean isFamilyLines() {
        return familyLines;
    }

    public void setFamilyLines(boolean familyLines) {
        this.familyLines = familyLines;
    }

    public boolean isSpouseLines() {
        return spouseLines;
    }

    public void setSpouseLines(boolean spouseLines) {
        this.spouseLines = spouseLines;
    }

    public boolean isLifeLines() {
        return lifeLines;
    }

    public void setLifeLines(boolean lifeLines) {
        this.lifeLines = lifeLines;
    }

    public boolean isMapActivity() {
        return isMapActivity;
    }

    public void setMapActivity(boolean mapActivity) {
        isMapActivity = mapActivity;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public String getFamilyLineColor() {
        return familyLineColor;
    }

    public void setFamilyLineColor(String familyLineColor) {
        this.familyLineColor = familyLineColor;
    }

    public String getSpouseLineColor() {
        return spouseLineColor;
    }

    public void setSpouseLineColor(String spouseLineColor) {
        this.spouseLineColor = spouseLineColor;
    }

    public String getLifeLineColor() {
        return lifeLineColor;
    }

    public void setLifeLineColor(String lifeLineColor) {
        this.lifeLineColor = lifeLineColor;
    }
    public boolean isMapChanged() {
        return mapChanged;
    }

    public void setMapChanged(boolean mapChanged) {
        this.mapChanged = mapChanged;
    }

    public void syncData(){
        int numEventTypes = 0;
        markerInfo.clear();
        eventMarker.clear();
        persons.clear();
        events.clear();
        eventList.clear();
        accessPersons.clear();
        firstName = null;
        lastName = null;
        mother = null;
        father = null;
        birthyear =0;
        userId = null;
        authtoken = null;
        email = null;
        userID = null;
        gender = null;
        isMapActivity =false;
        curPerson = null;
        curEvent = null;
    }

    public void clear(){
        _instance = new ModelContainer();
    }
}
