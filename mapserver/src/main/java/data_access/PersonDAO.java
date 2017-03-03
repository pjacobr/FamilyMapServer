package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Person;

/**
 * Created by jacob on 2/16/2017.
 */

public class PersonDAO {
    private Connection conn = null;

    //create a new Person Database connection
    public PersonDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * add a new person to the database
     *
     * @param person the info needed to add a new person into the databases
     * @return
     */
    public boolean addPerson(Person person) {
        String sql = "INSERT INTO Persons (personID, descendant, firstName, lastName, gender, father, mother, spouse)\n" +
                "VALUES (" + "'" + person.getPersonID() + "','" + person.getDescendant() + "','" + person.getFirstName() +
                "','" + person.getLastName() + "','" + person.getGender() + "','" + person.getFather() + "','"
                + person.getMother() + "','" + person.getSpouse() + "');";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * add a list of people to the
     *
     * @param persons a list of the info needed to add a new person into the databases
     * @return
     */
    public boolean addPerson(List<Person> persons) {
        for (Person person : persons) {
            addPerson(person);
        }
        return true;
    }

    /**
     * Update a person in the database based on the personID
     *
     * @param updatedPerson the info needed to update a new person into the databases
     * @param personID      the id of the person
     * @return
     */
    public boolean updatePerson(Person updatedPerson, String personID) {

        try {
            //Go through all the events and add the event
            String sql = "update Persons\n" +
                    "SET personID='" + updatedPerson.getPersonID() + "',"
                    + "descendant='" + updatedPerson.getDescendant() + "',"
                    + "firstName='" + updatedPerson.getFirstName() + "',"
                    + "lastName='" + updatedPerson.getLastName() + "',"
                    + "gender='" + updatedPerson.getGender() + "',"
                    + "father='" + updatedPerson.getFather() + "',"
                    + "mother='" + updatedPerson.getMother() + "',"
                    + "spouse='" + updatedPerson.getSpouse() + "'\n" +
                    "where personID='" + personID + ";";

            PreparedStatement stmt = null;
            ResultSet rs = null;


            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;


    }


    /**
     * get a specific person from the database to check
     *
     * @param personID the id of the person
     * @return Person object
     */
    public Person getPerson(String personID) {
        String sql = "select * from Persons where personID='" + personID + "';";

        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;


            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            //Get the data that is returned
            String person_ID = rs.getString(1);
            String descendant = rs.getString(2);
            String firstName = rs.getString(3);
            String lastName = rs.getString(4);
            char gender = rs.getString(5).charAt(0);
            String father = rs.getString(6);
            String mother = rs.getString(7);
            String spouse = rs.getString(8);

            stmt.close();

            return new Person(person_ID, descendant, firstName, lastName, gender, father, mother, spouse);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return null;
        }

    }

    /**
     * get all the people in the database
     *
     * @return List
     */
    public List<Person> getPerson() {
        List<Person> persons = new ArrayList<>();

        String sql = "select personID, descendant, firstName, lastName, gender, father, mother, spouse from Persons";
        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;

            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();


            while (rs.next()) {

                //Get the data that is returned
                String personID = rs.getString(1);
                String descendant = rs.getString(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                char gender = rs.getString(5).charAt(0);
                String father = rs.getString(6);
                String mother = rs.getString(7);
                String spouse = rs.getString(8);

                //Create a new event and add it to the list
                Person p = new Person(personID, descendant, firstName, lastName, gender, father, mother, spouse);
                persons.add(p);
            }
            //close the necessary
            stmt.close();
            rs.close();
            return persons;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return null;
        }
    }

    /**
     * delete a person in the database
     *
     * @param personID The id of the person that we are trying to delete
     * @return boolean
     */
    public void delete(String personID) {

        try {
        //Go through all the events and add the event ** Make sure to delete anything connected.
        String sql = "delete from Persons where personID='" + personID + "';";

        PreparedStatement stmt = null;
        ResultSet rs = null;


        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeQuery();
        stmt.close();


    } catch (SQLException e) {
        e.printStackTrace();
    }


    }

    /**
     * clear all the people in the database
     *
     * @return
     */
    public void clear() {
        try {
            //Go through all the events and add the event
            String sql = "drop table Events;";
            String sql2 = "create table Events";
            PreparedStatement stmt = null;
            ResultSet rs = null;

            //make a statement with the sql string above
            stmt = conn.prepareStatement(sql);
            stmt.executeQuery();
            stmt.close();

            //make a statement with the sql2 string above to recreate the tables
            stmt = conn.prepareStatement(sql2);
            stmt.executeQuery();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
