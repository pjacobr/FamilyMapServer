package server;

import java.util.List;

import request.LoadRequest;
import request.LoginRequest;
import request.RegisterRequest;
import result.ClearResult;
import result.EventResult;
import result.FillResult;
import result.LoadResult;
import result.LoginResult;
import result.PersonResult;
import result.RegisterResult;
/**
 * Created by jacob on 2/13/2017.
 */

public class ServerProxy {


    /**
     * register a new user
     * @param r request for a person to be registered
     * @return
     */
    public RegisterResult register(RegisterRequest r){
        return null;
    }

    /**
     * log a user in
     * @param l login request for the person to be logged in.
     * @return LoginResult
     */
    public LoginResult login(LoginRequest l){
        return null;
    }

    /**
     * Clear all the data in the database so you can start over
     * @return ClearResult
     */
    public ClearResult clear(){
        return null;
    }

    /**
     * Fill the persons heritage up to the number of generations
     * @param username username of the logged in person
     * @param generations number of generations desired
     * @return
     */
    public FillResult fill(String username, int generations){
        return null;
    }

    /**
     * Load the database with a bunch of data
     * @param l load request for the load
     * @return
     */
    public LoadResult load(LoadRequest l){
        return null;
    }

    /**
     * Get a person based on the id given
     * @param personID
     * @return PersonResult
     */
    public PersonResult person(String personID){
        return null;
    }

    /**
     *  get all the people that are currently in the database
     * @return List
     */
    public static List<PersonResult> person(){

        return null;
    }

    /**
     * Get one event based on the event id
     * @param eventID
     * @return EventResult
     */
    public EventResult event(String eventID){
        return null;
    }

    /**
     * Get all the events that are available on the database
     * @return List
     */
    public List<EventResult> event(){
        return null;
    }








}
