package model;

import java.util.UUID;

/**
 * Created by jacob on 2/16/2017.
 */

public class Person {
    //private datamembers
    private String personID = null;
    private String descendant = null;
    private String firstName = null;
    private String lastName = null;
    private String gender;
    private String father = null;
    private String mother = null;
    private String spouse = null;
    private int birthyear = 0;

    /**
     * Constructor
     * generates a new person that can be used to hold data
     *
     * @param personID   the id associated to the person
     * @param descendant the id associated with the descendant of the person
     * @param firstName  the first name of the person
     * @param lastName   last name of the person
     * @param gender     gender of the person
     * @param father     id of the father of the person (not required)
     * @param mother     id of the mother of the person (not required)
     * @param spouse     id of the spouse of the person (not required)
     */
    public Person(String personID, String descendant, String firstName, String lastName, String gender, String father, String mother, String spouse) {
        this.personID = personID;
        this.descendant = descendant;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.father = father;
        this.mother = mother;
        this.spouse = spouse;
    }


    //Other version of our constructor that will take in more varied parameters
    public Person(String descendant, String firstName, String lastName, String gender, String father, String mother, String spouse) {
        this(UUID.randomUUID().toString(), descendant, firstName, lastName, gender, father, mother, spouse);
    }

    public Person(String personID, String descendant, String firstName, String lastName, String gender, String father, String mother, String spouse, int year) {
        this(personID, descendant, firstName, lastName, gender, father, mother, spouse);
        this.birthyear = year;
    }



    public int getBirthyear(){
        return birthyear;
    }

    public void setBirthyear(int birthyear){
        this.birthyear = birthyear;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }
}
