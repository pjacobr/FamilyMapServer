package server;

/**
 * Created by jacob on 3/9/2017.
 */

import data_access.AuthTokenDAOTest;
import server.ServerProxyTest;

public class TestDriver {

    public static void main(String[] args) {

        org.junit.runner.JUnitCore.main(
                "data_access.AuthTokenDAOTest",
                "data_access.EventDAOTest",
                "data_access.PersonDAOTest",
                "data_access.UserDAOTest",
                "server.ServerProxyTest");



    }
}