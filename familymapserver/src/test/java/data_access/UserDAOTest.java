package data_access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.User;

import static org.junit.Assert.*;

/**
 * Created by jacob on 3/10/2017.
 */
public class UserDAOTest {

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
    public void createTables(){

    }


    @Test
    public void addUser() throws Exception {
        //get a new daoconnect
        UserDAO daoconnect = transaction.getUser();
        User user = new User("pjacobr", "test", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "abcd");
        daoconnect.addUser(user);
        //Finally!!!! It works
        User newUser = daoconnect.getUser("pjacobr");
        //System.out.println(newUser.getEmail());
        assertEquals("jacobp1794@gmail.com", newUser.getEmail());

    }

    @Test
    public void addUser1() throws Exception {
        //get a new daoconnect
        UserDAO daoconnect = transaction.getUser();
        User user = new User("pjacobr", "test", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "abcd");
        User user2 = new User("pjacobr2", "test2", "jacobp1794@gmail.com2", "Jacob2", "Pettingill2", "f", "abcd2");
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        //add list of users to the database
        daoconnect.addUser(users);

        daoconnect = transaction.getUser();
        List<User> users2 = daoconnect.getUser();
        for(User user3 : users2){
           // System.out.println(user3.getEmail());
        }
        assertEquals(2, users2.size());
    }

    @Test
    public void getUser() throws Exception {
        //get the user
        UserDAO daoconn = transaction.getUser();
        User user = daoconn.getUser("pjacobr");
        //System.out.println(user.getFirstName());
        assertEquals(null, user);
    }

    @Test
    public void getUser1() throws Exception {
        //get all the users in the database
        UserDAO daoconn = transaction.getUser();
        daoconn.addUser(new User("ggrant", "test2", "grant@grant.com", "Gregory", "Rowberry", "m", "abbb"));
        List<User> ls = daoconn.getUser();
        for(User user:ls){
           // System.out.println(user.getLastName());
        }
        assertEquals(1 , ls.size());
    }

    @Test
    public void delete() throws Exception {
        //get a new daoconnect
        UserDAO daoconnect = transaction.getUser();
        User user = new User("pjacobr", "test", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "abcd");
        daoconnect.addUser(user);
        //Finally!!!! It works
        daoconnect.delete("pjacobr");
        //System.out.println(newUser.getEmail());
        assertEquals(null, daoconnect.getUser("pjacobr"));
    }

    @Test
    public void clear() throws Exception {
        //get a new daoconnect
        UserDAO daoconnect = transaction.getUser();
        User user = new User("pjacobr", "test", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "abcd");
        daoconnect.addUser(user);
        assertEquals("test", daoconnect.getUser("pjacobr").getPassword());
        daoconnect.clear();
        assertEquals(null, daoconnect.getUser("pjacobr"));
    }

    @Test
    public void updateUser() throws Exception {
        //get a new daoconnect
        UserDAO daoconnect = transaction.getUser();
        User user = new User("pjacobr", "test", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "abcd");
        daoconnect.addUser(user);
        assertEquals("test", daoconnect.getUser("pjacobr").getPassword());
        User userUpdate = new User("pjacobr", "this", "jacobp1794@gmail.com", "Jacob", "Pettingill", "m", "abcd");
        daoconnect.updateUser(userUpdate);
        assertEquals("this", daoconnect.getUser("pjacobr").getPassword());
    }

}