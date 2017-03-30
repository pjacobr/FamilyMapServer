package request;

/**
 * Created by jacob on 2/16/2017.
 */

public class FillRequest {

    //the username that should be filled with generated data.
    private String username;
    private int generations = 4;

    //if I want to specify the number of generations
    public FillRequest(String username, int generations) {
        this(username);
        this.generations = generations;
    }
    //default constructor
    public FillRequest(String username){
        this.username = username;
    }

    /**
     * get the username associated with with the request
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    public int getGenerations() {
        return generations;
    }

}
