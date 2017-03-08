package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_access.PersonDAO;
import data_access.Transaction;
import model.Person;
import request.PersonRequest;
import result.PersonResult;

/**
 * Created by jacob on 2/16/2017.
 */

public class PersonService {
    /**
     * get a person from the server
     *
     * @return
     */
    public PersonResult person(PersonRequest p) {
        Transaction trans = new Transaction();
        trans.openConnection();

        PersonDAO persondao = trans.getPerson();
        Person person = null;
        try {
            person = persondao.getPerson(p.getPersonID());
        } catch (SQLException e) {
            e.printStackTrace();
            return new PersonResult(e.getMessage());
        } finally {
            trans.closeConnection();
        }
        //successfully retrieved person!
        return new PersonResult(person.getDescendant(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getMother(), person.getFather(), person.getSpouse());
    }

    /**
     * get all the people from the server
     *
     * @return
     */
    public List<PersonResult> person() {
        Transaction trans = new Transaction();
        trans.openConnection();

        PersonDAO persondao = trans.getPerson();
        List<PersonResult> results = new ArrayList<>();
        try {
            List<Person> people = persondao.getPerson();

            for (Person person : people) {
                results.add(new PersonResult(person.getDescendant(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getMother(), person.getFather(), person.getSpouse()));
            }
            return results;
        } catch (SQLException e) {
            e.printStackTrace();
            List<PersonResult> list = new ArrayList<>();
            list.add(new PersonResult(e.getMessage()));
            return list;
        } finally {
            trans.closeConnection();
        }
    }
}
