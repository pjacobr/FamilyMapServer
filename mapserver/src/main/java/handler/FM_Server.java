package handler;

/**
 * Created by jacob on 3/7/2017.
 */

import java.io.*;
import java.net.*;
import java.sql.SQLException;

import com.sun.net.httpserver.*;

import data_access.Transaction;


public class FM_Server {

    private static final int MAX_WAITING_CONNECTIONS = 12;

    private HttpServer server;

    private void run(String portNumber) {
        System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null); // use the default executor

        System.out.println("Creating contexts");
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill", new FillHandler());
        //server.createContext("/load", );
        server.createContext("/person", new PersonHandler());
        //server.createContext("/event",);
        server.createContext("/", new DefaultHandler());

        System.out.println("Starting server");
        server.start();
    }

    public static void main(String[] args) {
        String portNumber = args[0];
        Transaction trans = new Transaction();
        trans.openConnection();
        try {
            trans.createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                trans.closeConnection(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        new FM_Server().run(portNumber);
    }
}
