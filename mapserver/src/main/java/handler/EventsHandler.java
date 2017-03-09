package handler;


import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;

import request.LoginRequest;
import result.LoginResult;
import service.LoginService;

/**
 * Created by jacob on 3/8/2017.
 */

public class EventsHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //How to get the path from the URI
        URI uri = exchange.getRequestURI();
        String pathString = null;
        if(uri.getPath().charAt(0) == '/') {
            pathString = uri.getPath().substring(1, uri.getPath().length() - 1);
        }else{
            pathString = uri.getPath();
        }
        String[] input = pathString.split("/");

        boolean success = false;
        String filledJson = null;
        try {
            if(exchange.getRequestMethod().toLowerCase().equals("post")){
                //InputStreamReader is = new InputStreamReader(reqBody);
                //does a bunch of stuff.
                InputStream request = exchange.getRequestBody();
                String jsonString = readString(request);
                Gson gson = new Gson();
                LoginRequest login = gson.fromJson(jsonString, LoginRequest.class);
                //call the fillservice
                LoginService loginservice = new LoginService();
                LoginResult loggedIn = loginservice.login(login);
                //now give that back to JSON
                filledJson = gson.toJson(loggedIn);


                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);


                success = true;
            }
            //Check to see if it succeeded
            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            e.printStackTrace();
        }

        OutputStream sendResponse = exchange.getResponseBody();
        //Send back the body
        sendResponse.write(filledJson.getBytes());
        sendResponse.close();


    }
    private static String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
