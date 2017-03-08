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

import request.ClearRequest;
import request.LoginRequest;
import result.ClearResult;
import result.LoginResult;
import service.ClearService;
import service.LoginService;

/**
 * Created by jacob on 2/16/2017.
 */

public class ClearHandler implements HttpHandler {

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
        String pathString = uri.getPath();

        boolean success = false;
        String filledJson = null;
        try {
            //InputStreamReader is = new InputStreamReader(reqBody);
            //does a bunch of stuff.
            InputStream request = exchange.getRequestBody();
            String jsonString = readString(request);
            Gson gson = new Gson();
            ClearRequest clear = gson.fromJson(jsonString, ClearRequest.class);
            //call the fillservice
            ClearService clearService = new ClearService();
            ClearResult clearIt = clearService.clear();
            //now give that back to JSON
            filledJson = gson.toJson(clearIt);


            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            // TODO: Claim a route based on the request data

            success = true;

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
