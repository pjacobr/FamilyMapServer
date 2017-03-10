package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_access.AuthTokenDAO;
import data_access.PersonDAO;
import data_access.Transaction;
import data_access.UserDAO;
import model.Person;
import model.User;
import request.PersonRequest;
import result.PersonResult;

/**
 * Created by jacob on 2/16/2017.
 */

public class PersonService {
    String authToken = null;

    public PersonService(String authToken) {
        this.authToken = authToken;
    }

    /**
     * get a person from the server
     *
     * @return
     */
    public PersonResult person(PersonRequest p) {
        Transaction trans = new Transaction();
        trans.openConnection();

        PersonDAO persondao = trans.getPerson();
        AuthTokenDAO authTokenDAO = trans.getAuthToken();
        String username = null;
        if ((username = authTokenDAO.checkUser(authToken)) != null) {

            Person person = null;
            try {
                person = persondao.getPerson(p.getPersonID());
                if(!person.getDescendant().equals(username)){
                    return new PersonResult("Invalid Authtoken");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return new PersonResult(e.getMessage());
            } finally {
                try {
                    trans.closeConnection(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //successfully retrieved person!
            return new PersonResult(person.getDescendant(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getMother(), person.getFather(), person.getSpouse());
        }else{
            return new PersonResult("AuthToken not valid, please login again");
        }
    }


    /**
     * get all the people from the server
     *
     * @return
     */
    public List<PersonResult> person() {
        Transaction trans = new Transaction();
        trans.openConnection();

        //Access the database
        PersonDAO persondao = trans.getPerson();
        AuthTokenDAO authTokenDAO = trans.getAuthToken();
        UserDAO userDao = trans.getUser();

        //this is the name of the person that we want all their ancestors
        String username = null;
        List<Person> people = null;
        List<PersonResult> results = null;

        if ((username = authTokenDAO.checkUser(authToken)) != null) {
            results = new ArrayList<>();
            try {
                User user = userDao.getUser(username);
                //Get all the people connected through the descendant
                people = persondao.getPersons(user.getUsername());

                for (Person person : people) {
                    results.add(new PersonResult(person.getDescendant(), person.getPersonID(), person.getFirstName(), person.getLastName(), person.getGender(), person.getMother(), person.getFather(), person.getSpouse()));
                }
                return results;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    trans.closeConnection(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else {
            return null;
        }
    }

}
