package data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public boolean addPerson(Person person) throws SQLException {
        String sql = "INSERT INTO Persons (personID, descendant, firstName, lastName, gender, father, mother, spouse)\n" +
                "VALUES (" + "'" + person.getPersonID() + "','" + person.getDescendant() + "','" + person.getFirstName() +
                "','" + person.getLastName() + "','" + person.getGender() + "','" + person.getFather() + "','"
                + person.getMother() + "','" + person.getSpouse() + "');";
        PreparedStatement stmt = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
        return true;
    }

    /**
     * add a list of people to the
     *
     * @param persons a list of the info needed to add a new person into the databases
     * @return
     */
    public void addPerson(List<Person> persons) throws SQLException {
        for (Person person : persons) {
            addPerson(person);
        }
    }

    /**
     * Update a person in the database based on the personID
     *
     * @param updatedPerson the info needed to update a new person into the databases
     * @param personID      the id of the person
     * @return
     */
    public void updatePerson(Person updatedPerson, String personID) throws SQLException {

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
                "where personID='" + personID + "';";

        PreparedStatement stmt = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();

        stmt.close();
    }





    /**
     * get a specific person from the database to check
     *
     * @param personID the id of the person
     * @return Person object
     */
    public Person getPerson(String personID) throws SQLException {

        //get the person based on their person ID


        String sql = "select personID, descendant, firstname, lastname, gender, father, mother, spouse from Persons where personID='" + personID + "';";
        PreparedStatement stmt = null;
        ResultSet rs = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        rs = stmt.executeQuery();
        if (rs.next()) {

            //Get the data that is returned
            String person_ID = rs.getString(1);
            String descendant = rs.getString(2);
            String firstName = rs.getString(3);
            String lastName = rs.getString(4);
            String gender = rs.getString(5);
            String father = rs.getString(6);
            String mother = rs.getString(7);
            String spouse = rs.getString(8);

            //handle closing the statement and result set and return the user
            stmt.close();
            rs.close();
            return new Person(person_ID, descendant, firstName, lastName, gender, father, mother, spouse);
        }

        return null;
    }

    /**
     * get all the people in the database
     *
     * @return List
     */
    public List<Person> getPersons(String descendantID) throws SQLException {
        List<Person> persons = new ArrayList<>();

        String sql = "select * from Persons where descendant = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, descendantID);
        rs = stmt.executeQuery();
        boolean added = false;

        while (rs.next()) {
            added = true;
            //Get the data that is returned
            String personID = rs.getString(1);
            String descendant = rs.getString(2);
            String firstName = rs.getString(3);
            String lastName = rs.getString(4);
            String gender = rs.getString(5);
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
        //if it got added
        if (added) {
            return persons;
        }
        return null;
    }

    /**
     * delete a person in the database
     *
     * @param personID The id of the person that we are trying to delete
     * @return boolean
     */
    public void delete(String personID) throws SQLException {

        //Go through all the events and add the event ** Make sure to delete anything connected.
        String sql = "delete from Persons where descendant='" + personID + "'";

        PreparedStatement stmt = null;
        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * clear all the people in the database
     *
     * @return
     */
    public void clear() throws SQLException {
        //Go through all the events and add the event
        String sql = "drop table Persons;";
        String sql2 = "CREATE TABLE Persons\n" +
                "(\n" +
                "\tpersonID varchar(255) NOT NULL PRIMARY KEY,\n" +
                "\tdescendant varchar(255),\n" +
                "\tfirstname varchar(255) NOT NULL,\n" +
                "\tlastname varchar(255) NOT NULL,\n" +
                "\tgender varchar(1) NOT NULL,\n" +
                "\tfather varchar(255),\n" +
                "\tmother varchar(255),\n" +
                "\tspouse varchar(255),\n" +
                "\tCONSTRAINT ck_gender CHECK (gender in ('m', 'm'))\n" +
                ");";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        //make a statement with the sql string above
        stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
        stmt.close();

        Statement stmt2 = null;
        //make a statement with the sql2 string above to recreate the tables
        stmt2 = conn.createStatement();
        stmt2.executeUpdate(sql2);
        stmt2.close();
    }
}
