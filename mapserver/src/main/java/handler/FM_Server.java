package handler;

/**
 * Created by jacob on 3/7/2017.
 */

import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;


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
        //server.createContext("/user/login", new );
        //server.createContext("/user/register",  );
        //server.createContext("/clear",);
        //server.createContext("/fill/username/generations", );
        //server.createContext("/load", );
        //server.createContext("/person/personID", );
        //server.createContext("/person", );
        //server.createContext("/event/[eventID]",);
        //server.createContext("/event",);
        server.createContext("/", new FileHandler());
        System.out.println("Starting server");
        server.start();
    }

    public static void main(String[] args) {
        String portNumber = args[0];
        new FM_Server().run(portNumber);
    }
}
