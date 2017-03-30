package request;

/**
 * Created by jacob on 2/16/2017.
 */

public class PersonRequest {
    String personID;

    /**
     * make a new request object
     * @param personID
     */
    public PersonRequest(String personID) {
        this.personID = personID;
    }

    /**
     * gets the person in the request
     * @return personID
     */
    public String getPersonID(){
        return personID;
    }
}
