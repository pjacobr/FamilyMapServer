package server;


import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.AuthToken;
import request.ClearRequest;
import request.FillRequest;
import request.LoadRequest;
import request.LoginRequest;
import request.PersonRequest;
import request.RegisterRequest;
import result.ClearResult;
import result.EventResult;
import result.FillResult;
import result.LoadResult;
import result.LoginResult;
import result.PersonResult;
import result.RegisterResult;
/**
 * Created by jacob on 2/13/2017.
 */

public class ServerProxy {

    String authToken = null;
    public ServerProxy(String authToken){
        this.authToken = authToken;
    }

    public static void main(String[] args){}

//    http.addRequestProperty("Authorization", "afj232hj2332");
  //  http.addRequestProperty("Content-Type", "application/json");

    //
    public RegisterResult register(String serverHost, String serverPort, RegisterRequest rg) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/user/register");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body
            http.connect();


            Gson gs = new Gson();
            String jsonString = gs.toJson(rg);
            OutputStream reqBody = http.getOutputStream();
            writeString(jsonString , reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                RegisterResult result = gs.fromJson(respData, RegisterResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            }
            else {
                return new RegisterResult("ERROR: " + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return new RegisterResult(e.getMessage());
        }
    }

    public LoginResult login(String serverHost, String serverPort, LoginRequest lg) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/user/login");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body
            http.connect();

            //Convert to JSON and back
            Gson gs = new Gson();
            String jsonString = gs.toJson(lg);
            OutputStream reqBody = http.getOutputStream();
            writeString(jsonString , reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                LoginResult result = gs.fromJson(respData, LoginResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new LoginResult("Error" + http.getResponseMessage());
            }
        }
        catch (IOException e) {
           return new LoginResult( e.toString());
        }

    }

    /**
     * Clear all the data in the database so you can start over
     * @return ClearResult
     */
    public ClearResult clear(String serverHost, String serverPort){
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/clear");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(false);	// There is a request body
            http.connect();


            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                ClearResult result = gs.fromJson(respData, ClearResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new ClearResult("Error" + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            return new ClearResult( e.toString());
        }
    }

    /**
     * Fill the persons heritage up to the number of generations
     * @param lg username of the logged in person
     * @param serverPort number of generations desired
     * @param serverHost
     * @return
     */
    public FillResult fill(String serverHost, String serverPort, FillRequest lg){
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/fill");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(false);	// There is a request body
            http.connect();

            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                FillResult result = gs.fromJson(respData, FillResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new FillResult("Error" + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            return new FillResult(e.toString());
        }
    }

    /**
     * Load the database with a bunch of data
     * @param lg load request for the load
     *
     * @return
     */
    public LoadResult load(String serverHost, String serverPort, LoginRequest lg){
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/load");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body
            http.connect();


            Gson gs = new Gson();
            String jsonString = gs.toJson(lg);
            OutputStream reqBody = http.getOutputStream();
            writeString(jsonString , reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                LoadResult result = gs.fromJson(respData, LoadResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new LoadResult("Error" + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            return new LoadResult(e.toString());
        }
    }

    /**
     * Get a person based on the id given
     * @param serverHost
     * @param serverPort
     * @param lg
     * @return PersonResult
     */
    public PersonResult person(String serverHost, String serverPort, PersonRequest lg) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/person" + File.separator + lg.getPersonID());

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("GET");
            http.setDoOutput(false);	// There is a request body
            http.addRequestProperty("Authorization", authToken);

            http.addRequestProperty("Content-Type", "application/json");
            http.connect();
            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                PersonResult result = gs.fromJson(respData, PersonResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new PersonResult("Error" + http.getResponseMessage());
            }
        }

        catch (IOException e) {
            return new PersonResult(e.toString());
        }
    }

    /**
     *  get all the people that are currently in the database
     * @return List
     */
    public List<PersonResult> person(String serverHost, String serverPort){
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/person");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("GET");
            http.setDoOutput(false);	// There is a request body
            http.addRequestProperty("Authorization", authToken);

            http.addRequestProperty("Content-Type", "application/json");
            http.connect();
            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                 class Results{
                    List<PersonResult> results = null;
                }
                Results result = gs.fromJson(respData, Results.class);
                System.out.println("Route successfully claimed.");
                return result.results;
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                List<PersonResult> results = new ArrayList<>();
                results.add(new PersonResult("Error" + http.getResponseMessage()));
                return results;
            }
        }

        catch (IOException e) {
            List<PersonResult> results = new ArrayList<>();
            results.add(new PersonResult(e.toString()));
            return results;
        }
    }

    /**
     * Get one event based on the event id
     * @param serverHost
     * @param serverPort
     * @return EventResult
     */
    public List<EventResult> event(String serverHost, String serverPort){

        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/events");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("GET");
            http.setDoOutput(false);	// There is a request body
            http.addRequestProperty("Authorization", authToken);

            http.addRequestProperty("Content-Type", "application/json");
            http.connect();
            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                class Results{
                    List<EventResult> results = null;
                }
                Results result = gs.fromJson(respData, Results.class);
                System.out.println("Route successfully claimed.");
                return result.results;
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
                List<EventResult> results = new ArrayList<>();
                results.add(new EventResult("Error" + http.getResponseMessage()));
                return results;
            }
        }

        catch (IOException e) {
            List<EventResult> results = new ArrayList<>();
            results.add(new EventResult(e.toString()));
            return results;
        }
    }

    /**
     * Get all the events that are available on the database
     * @return List
     */
    public EventResult event(){
        return null;
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

    private static void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }





}
