package data_access;

import java.sql.Connection;
import java.util.List;

import model.Person;

/**
 * Created by jacob on 2/16/2017.
 */

public class PersonDAO {
    private Connection conn = null;
    //create a new Person Database connection
    public PersonDAO(Connection conn){
        this.conn = conn;
    }
    /**
     * add a new person to the database
     * @param person the info needed to add a new person into the databases
     * @return
     */
    public boolean addPerson(Person person){
        return true;
    }

    /**
     * add a list of people to the
     * @param persons a list of the info needed to add a new person into the databases
     * @return
     */
    public boolean addPerson(List<Person> persons){
        return true;
    }

    /**
     * Update a person in the database based on the personID
     * @param updatedPerson the info needed to update a new person into the databases
     * @param personID the id of the person
     * @return
     */
    public boolean setPerson(Person updatedPerson, String personID){
        return true;
    }




    /**
     * get a specific person from the database to check
     * @param personID the id of the person
     * @return Person object
     */
    public Person getPerson(String personID){
        return null;
    }

    /**
     * get all the people in the database
     * @return List
     */
    public List<Person> getPerson(){
        return null;
    }

    /**
     * delete a person in the database
     * @param personID The id of the person that we are trying to delete
     * @return boolean
     */
    public boolean delete(String personID){
        return true;
    }

    /**
     * clear all the people in the database
     * @return
     */
    public boolean clear(){
        return true;
    }
}
