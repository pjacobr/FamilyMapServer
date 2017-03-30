package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.List;

import request.PersonRequest;
import result.PersonResult;
import service.PersonService;

/**
 * Created by jacob on 3/8/2017.
 */

public class PersonHandler implements HttpHandler {
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

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //How to get the path from the URI
        URI uri = exchange.getRequestURI();
        String pathString = null;
        if (uri.getPath().charAt(0) == '/') {
            pathString = uri.getPath().substring(1, uri.getPath().length());
        } else {
            pathString = uri.getPath();
        }
        String[] input = pathString.split("/");

        boolean success = false;
        String filledJson = null;
        try {
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                //check to be sure that the auth key exists and that it is not empty.
                if (reqHeaders.containsKey("Authorization") && !reqHeaders.getFirst("Authorization").equals("")) {

                    String authToken = reqHeaders.getFirst("Authorization");

                    Gson gson = new Gson();
                    PersonService personService = new PersonService(authToken);
                    PersonResult personResult = null;
                    List<PersonResult> personResults = null;
                    //ok do we want all of them?
                    boolean list = false;
                    if (input.length > 1) {
                        list = true;
                        personResult = personService.person(new PersonRequest(input[1]));
                    } else {
                        personResults = personService.person();
                    }

                    if (list) {
                        filledJson = gson.toJson(personResult);
                    } else {
                        if (personResults == null) {
                            filledJson = gson.toJson(new PersonResult("Invalid Authorization"));
                        }
                        PersonResult[] personresults = personResults.toArray(new PersonResult[personResults.size()]);
                        filledJson = gson.toJson(personresults);
                    }

                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    success = true;

                }
                //InputStreamReader is = new InputStreamReader(reqBody);


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
}
