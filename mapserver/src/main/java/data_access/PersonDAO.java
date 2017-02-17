package data_access;

import model.Person;
import java.util.List;

/**
 * Created by jacob on 2/16/2017.
 */

public class PersonDAO {
    /**
     * add a new person to the database
     * @param person
     * @return
     */
    public boolean addPerson(Person person){
        return true;
    }

    /**
     * add a list of people to the
     * @param persons
     * @return
     */
    public boolean addPerson(List<Person> persons){
        return true;
    }

    /**
     * get a specific person from the database to check
     * @param person
     * @return
     */
    public Person getPerson(Person person){
        return null;
    }

    /**
     * get all the people in the database
     * @return
     */
    public List<Person> getPerson(){
        return null;
    }

    /**
     * delete a person in the database
     * @param person
     * @return
     */
    public boolean delete(Person person){
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
