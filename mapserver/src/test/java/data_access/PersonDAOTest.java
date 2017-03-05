package data_access;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

import model.Person;

import static org.junit.Assert.assertEquals;

/**
 * Created by jacob on 3/4/2017.
 */
public class PersonDAOTest {
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
    public void addPerson() throws Exception {
        //get a new daoconnect
        PersonDAO daoconnect = transaction.getPerson();
        Person person = new Person("abdc", "Granppa", "jacobp1794@gmail.com", "adf", "m", "Pettingill", "m", "abcd");

        daoconnect.addPerson(person);
        //Finally!!!! It works
        Person newPerson = daoconnect.getPerson("abdc");
        //System.out.println(newUser.getEmail());
        assertEquals("Granppa", newPerson.getDescendant());
    }

    @Test
    public void addPerson1() throws Exception {
        //get a new daoconnect
        PersonDAO daoconnect = transaction.getPerson();
        Person person = new Person("abdc", "Granppa", "jacobp1794@gmail.com", "adf", "m", "Pettingill", "m", "abcd");
        Person person2 = new Person("abdc2", "Granppa2", "jacobp1794@gmail.com2", "adf2", "m", "Pettingill2", "m2", "abcd2");
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        persons.add(person2);
        //Add the people in the
        daoconnect.addPerson(persons);
        //Finally!!!! It works
        List<Person> persons2 = daoconnect.getPerson();
        //System.out.println(newUser.getEmail());
        for(Person person3 : persons2){
            System.out.println(person3.getPersonID());
        }

        assertEquals(2, persons2.size());
    }

    @Test
    public void updatePerson() throws Exception {
        //get a new daoconnect
        PersonDAO daoconnect = transaction.getPerson();
        Person person = new Person("abdc", "granppa", "jacobp1794@gmail.com", "adf", "m", "Pettingill", "m", "abcd");
        Person person2 = new Person("abdc", "pops", "jacobp1794@gmail.com", "adf", "m", "Pettingill", "m", "abcd");
        daoconnect.addPerson(person);
        //make sure that it is good before
        assertEquals("granppa", daoconnect.getPerson("abdc").getDescendant());

        daoconnect.updatePerson(person2, "abdc");
        //Finally!!!! It works
        Person newPerson = daoconnect.getPerson("abdc");
        //System.out.println(newUser.getEmail());

        assertEquals("pops", newPerson.getDescendant());
    }


    @Test
    public void delete() throws Exception {
        PersonDAO daoconnect = transaction.getPerson();
        Person person = new Person("abdc", "granppa", "jacobp1794@gmail.com", "adf", "m", "Pettingill", "m", "abcd");
        //Person person2 = new Person("abdc", "pops", "jacobp1794@gmail.com", "adf", "m", "Pettingill", "m", "abcd");
        daoconnect.addPerson(person);
        //daoconnect.addPerson(person2);

        daoconnect.delete("abdc");
        assertEquals(null, daoconnect.getPerson("abdc"));
    }

    @Test
    public void clear() throws Exception {
        PersonDAO daoconnect = transaction.getPerson();
        Person person = new Person("abdc", "granppa", "jacobp1794@gmail.com", "adf", "m", "Pettingill", "m", "abcd");
        Person person2 = new Person("abdc2", "granppa2", "jacobp1794@gmail.com2", "adf2", "m", "Pettingill2", "m2", "abcd2");
        Person person3 = new Person("abdc23", "granppa23", "jacobp1794@gmail.com23", "adf23", "m", "Pettingill23", "m23", "abcd23");
        //Person person2 = new Person("abdc23", "pops", "jacobp1794@gmail.com", "adf", "m", "Pettingill", "m", "abcd");
        daoconnect.addPerson(person);
        daoconnect.addPerson(person2);
        daoconnect.addPerson(person3);
        //daoconnect.addPerson(person2);

        daoconnect.clear();
        assertEquals(null, daoconnect.getPerson("abdc"));
    }

}