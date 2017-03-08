package handler;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;

import request.FillRequest;
import result.FillResult;
import service.FillService;

/**
 * Created by jacob on 2/16/2017.
 */

public class FillHandler implements HttpHandler {
    private static final String DEFAULT_FILE_NAME = "\\index.html";
    private static final String LOC_404 = "C:\\Users\\jacob\\AndroidStudioProjects\\FamilyMapServer\\familymapserver\\data\\HTML\\404.html";
    private static final String HTTP_ROOT = "C:\\Users\\jacob\\AndroidStudioProjects\\FamilyMapServer\\familymapserver\\data\\HTML";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //How to get the path from the URI
        URI uri = exchange.getRequestURI();
        String pathString = uri.getPath();
        String[] input = uri.getPath().split("/");

        boolean success = false;

        try {
            //if (exchange.getRequestMethod().toLowerCase().equals("post")) {

            //get the request body which should be 3 arrays
            // InputStream reqBody = exchange.getRequestBody();
            //InputStreamReader is = new InputStreamReader(reqBody);
            //does a bunch of stuff.
            Gson converter = new Gson();
            FillRequest fill = null;
            if (input.length > 2) {
                fill = new FillRequest(input[1], Integer.parseInt(input[2]));
            }else {
                fill = new FillRequest(input[1]);
            }
            //call the fillservice
            FillService fillService = new FillService();
            FillResult filled = fillService.fill(fill);
            //now give that back to JSON
            String filledJson = converter.toJson(filled);

            OutputStream sendResponse = exchange.getResponseBody();
            //Send back the body
            sendResponse.write(filledJson.getBytes());
            sendResponse.close();

            // TODO: Claim a route based on the request data

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            success = true;
            //}
            //Check to see if it succeeded
            if (!success) {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            e.printStackTrace();
        }


    }
}
