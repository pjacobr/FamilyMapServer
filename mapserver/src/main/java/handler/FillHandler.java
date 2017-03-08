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
        String pathString = uri.getPath().substring(1,uri.getPath().length() - 1);
        String[] input = pathString.split("/");
        for(String inputString : input){
            System.out.println(inputString);
        }
        boolean success = false;
        String filledJson = null;
        try {
            //Conver what from json? nothing
            Gson converter = new Gson();
            FillRequest fill = null;

            //check and see if they gave us a number of generations to fill
            if (input.length > 2) {
                //format should be fill/username/# of generations
                fill = new FillRequest(input[1], Integer.parseInt(input[2]));
            }else {
                fill = new FillRequest(input[1]);
            }

            //call the fillservice
            FillService fillService = new FillService();
            FillResult filled = fillService.fill(fill);

            //now give that back to JSON
            filledJson = converter.toJson(filled);
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

        OutputStream sendResponse = exchange.getResponseBody();
        //Send back the body
        sendResponse.write(filledJson.getBytes());
        sendResponse.close();

    }
}
