package data_access;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jacob on 2/24/2017.
 */
public class TransactionTest {
    Transaction trans = null;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        trans = null;
    }

    @Test
    public void openConnection() throws Exception {
        //make a new Transaction object
        trans = new Transaction();
        //call the connection object
        if(trans.openConnection()){
            System.out.println("Successfully made the database!");
        }
        trans.closeConnection();
    }

    @Test
    public void closeConnection() throws Exception {
        trans = new Transaction();
        //try closing an unopened connection
        if(!trans.closeConnection()){
            System.out.println("Failed to close an unopened connection");
        }else{
            System.out.println("what the...");
        }
        //open a connection and then close the connection
        trans.openConnection();
        if(trans.closeConnection()){
            System.out.println("successfully opened and closed the connection");
        }
    }

    @Test
    public void getEvent() throws Exception {
        //create new Transaction
        trans = new Transaction();
        //open a new transaction
        trans.openConnection();
        //get a new EventDao object
        EventDAO access_events = trans.getEvent();
        //

        trans.closeConnection();


    }



    @Test
    public void getPerson() throws Exception {

    }

    @Test
    public void getUser() throws Exception {

    }

    @Test
    public void getAuthToken() throws Exception {

    }

}