package data_access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.TimeUnit;

import model.AuthToken;
import model.User;

import static org.junit.Assert.assertEquals;

/**
 * Created by jacob on 3/4/2017.
 */
public class AuthTokenDAOTest {
    Transaction transaction;

    @Before
    public void setUp() throws Exception {
        transaction = new Transaction();
        transaction.openConnection();
        transaction.createTables();
    }

    @After
    public void tearDown() throws Exception {
        transaction.closeConnection();
    }

    @Test
    public void addAuthToken() throws Exception {
        //get a new daoconnect
        AuthTokenDAO daoconnect = transaction.getAuthToken();
        //User myUser = new User("pjacobr", "password", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "12321");

        daoconnect.addAuthToken("pjacobr");
        //Finally!!!! It works
        AuthToken authToken= daoconnect.getAuthToken("pjacobr");
        //System.out.println(newUser.getEmail());
        assertEquals("pjacobr", authToken.getAuthToken());
    }

    @Test
    public void deleteAuthToken() throws Exception {
        //get a new daoconnect
        AuthTokenDAO daoconnect = transaction.getAuthToken();
        User user = new User("pjacobr", "password", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "12321");
        daoconnect.addAuthToken(user.getUsername());
        //Finally!!!! It works
        assertEquals("pjacobr", daoconnect.getAuthToken("pjacobr"));
        TimeUnit.SECONDS.sleep(3600);
        daoconnect.deleteAuthTokens();
        //System.out.println(newUser.getEmail());
        assertEquals(null, daoconnect.getAuthToken("pjacobr"));
    }

    @Test
    public void updateAuthToken() throws Exception {
        //get a new daoconnect
        AuthTokenDAO daoconnect = transaction.getAuthToken();
        User user = new User("pjacobr", "password", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "12321");
        daoconnect.addAuthToken("pjacobr");
        //Finally!!!! It works
        assertEquals("pjacobr", daoconnect.getAuthToken("pjacobr"));
        daoconnect.updateAuthToken("pjacobr");
        //System.out.println(newUser.getEmail());
        assertEquals("pjacobr", daoconnect.getAuthToken("pjacobr").getUsername());


    }


}