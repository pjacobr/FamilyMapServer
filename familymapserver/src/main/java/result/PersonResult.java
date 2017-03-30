package result;

/**
 * Created by jacob on 2/16/2017.
 */

public class PersonResult {
    String message = null;
    String descendant = null;
    String personID= null;
    String firstname = null;

    public PersonResult(String descendant, String personID, String firstname, String lastname, String gender, String mother, String father, String spouse) {
        this.descendant = descendant;
        this.personID = personID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.mother = mother;
        this.father = father;
        this.spouse = spouse;
    }

    String lastname = null;
    String gender = null;
    String mother = null;
    String father = null;
    String spouse = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    //create an instance of the object
    public PersonResult(String message){
        this.message = message;

    }

}
