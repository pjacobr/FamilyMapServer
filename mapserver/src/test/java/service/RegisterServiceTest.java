package service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import data_access.Transaction;
import request.RegisterRequest;

import static org.junit.Assert.*;

/**
 * Created by jacob on 3/8/2017.
 */
public class RegisterServiceTest {
    @Before
    public void setUp() throws Exception {
        Transaction trans = new Transaction();
        trans.openConnection();
        trans.createTables();
        trans.closeConnection();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void registerUser() throws Exception {
        RegisterService reg = new RegisterService();
        RegisterRequest newUser = new RegisterRequest("pjacobr", "pass", "ema", "jacob", "petting", "m");
        reg.registerUser(newUser);
    }

}