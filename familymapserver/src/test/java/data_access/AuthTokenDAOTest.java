package data_access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import model.AuthToken;
import model.Person;
import model.User;

import static org.junit.Assert.*;

/**
 * Created by jacob on 3/10/2017.
 */
public class AuthTokenDAOTest {
    Transaction transaction;

    @Before
    public void setUp() throws Exception {
        transaction = new Transaction();
        //transaction.testing();
        transaction.openConnection(false);
        transaction.createTables(true);
    }

    @After
    public void tearDown() throws Exception {
        transaction.closeConnection(false);
    }

    @Test
    public void addAuthToken() throws Exception {
        //get a new daoconnect
        AuthTokenDAO daoconnect = transaction.getAuthToken();
        User myUser = new User("pjacobr", "password", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "12321");
        PersonDAO persondao = transaction.getPerson();
        UserDAO userdao = transaction.getUser();
        persondao.addPerson(new Person(myUser.getPersonID(), null, myUser.getFirstName(), myUser.getLastName(), myUser.getGender(), null, null, null));
        userdao.addUser(myUser);
        daoconnect.addAuthToken("pjacobr");
        //Finally!!!! It works
        AuthToken authToken= daoconnect.getAuthToken("pjacobr");
        //System.out.println(newUser.getEmail());
        assertEquals("pjacobr", authToken.getUsername());
    }

    @Test
    public void deleteAuthToken() throws Exception {
        AuthTokenDAO daoconnect = transaction.getAuthToken();
        User myUser = new User("pjacobr", "password", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "12321");
        PersonDAO persondao = transaction.getPerson();
        UserDAO userdao = transaction.getUser();
        persondao.addPerson(new Person(myUser.getPersonID(), null, myUser.getFirstName(), myUser.getLastName(), myUser.getGender(), null, null, null));
        userdao.addUser(myUser);
        daoconnect.addAuthToken(myUser.getUsername());
        //Finally!!!! It works
        assertEquals("pjacobr", daoconnect.getAuthToken("pjacobr").getUsername());
        //TimeUnit.SECONDS.sleep(3600);
        daoconnect.deleteAuthTokens();
        //System.out.println(newUser.getEmail());
        assertEquals(null, daoconnect.getAuthToken("pjacobr"));
    }

    @Test
    public void updateAuthToken() throws SQLException {
        //Get AuthToken connection
        AuthTokenDAO daoconnect = transaction.getAuthToken();
        User myUser = new User("pjacobr", "password", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "12321");
        PersonDAO persondao = transaction.getPerson();
        UserDAO userdao = transaction.getUser();
        persondao.addPerson(new Person(myUser.getPersonID(), null, myUser.getFirstName(), myUser.getLastName(), myUser.getGender(), null, null, null));
        userdao.addUser(myUser);
        daoconnect.addAuthToken("pjacobr");
        //Finally!!!! It works
        assertEquals("pjacobr", daoconnect.getAuthToken("pjacobr").getUsername());
        daoconnect.updateAuthToken("pjacobr");
        //System.out.println(newUser.getEmail());
        assertEquals("pjacobr", daoconnect.getAuthToken("pjacobr").getUsername());


    }



}