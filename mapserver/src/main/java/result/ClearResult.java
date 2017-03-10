package result;

/**
 * Created by jacob on 2/16/2017.
 */

public class ClearResult {
    String message = null;

    /**
     * create a message based on the result.
     * @param message
     */
    public ClearResult(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
