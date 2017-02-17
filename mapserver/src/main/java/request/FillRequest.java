package request;

/**
 * Created by jacob on 2/16/2017.
 */

public class FillRequest {

    //the username that should be filled with generated data.
    private String username;

    /**
     * get the username associated with with the request
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Create a request object that allows us to fill the
     * user with generated data.
     * @param username
     */
    public FillRequest(String username) {
        this.username = username;
    }
}
