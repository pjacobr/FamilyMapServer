package server;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import request.LoadRequest;
import request.LoginRequest;
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


    public static void main(String[] args){}

    private static void getGameList(String serverHost, String serverPort) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/games/list");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("GET");
            http.setDoOutput(false);	// There is no request body

            http.addRequestProperty("Authorization", "afj232hj2332");
            http.addRequestProperty("Accept", "application/json");

            http.connect();
            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream respBody = http.getInputStream();

                String respData = readString(respBody);

                System.out.println(respData);
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void serverProxy(String serverHost, String serverPort) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/routes/claim");

            HttpURLConnection http = (HttpURLConnection)url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);	// There is a request body

            http.addRequestProperty("Authorization", "afj232hj2332");
            http.addRequestProperty("Content-Type", "application/json");

            http.connect();

            String reqData =
                    "{" +
                            "\"route\": \"atlanta-miami\"" +
                            "}";

            OutputStream reqBody = http.getOutputStream();
            writeString(reqData, reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                System.out.println("Route successfully claimed.");
            }
            else {
                System.out.println("ERROR: " + http.getResponseMessage());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * register a new user
     * @param r request for a person to be registered
     * @return
     */
    public RegisterResult register(RegisterRequest r){
        return null;
    }

    /**
     * log a user in
     * @param l login request for the person to be logged in.
     * @return LoginResult
     */
    public LoginResult login(LoginRequest l){
        return null;
    }

    /**
     * Clear all the data in the database so you can start over
     * @return ClearResult
     */
    public ClearResult clear(){
        return null;
    }

    /**
     * Fill the persons heritage up to the number of generations
     * @param username username of the logged in person
     * @param generations number of generations desired
     * @return
     */
    public FillResult fill(String username, int generations){
        return null;
    }

    /**
     * Load the database with a bunch of data
     * @param l load request for the load
     * @return
     */
    public LoadResult load(LoadRequest l){
        return null;
    }

    /**
     * Get a person based on the id given
     * @param personID
     * @return PersonResult
     */
    public PersonResult person(String personID){
        return null;
    }

    /**
     *  get all the people that are currently in the database
     * @return List
     */
    public static List<PersonResult> person(){

        return null;
    }

    /**
     * Get one event based on the event id
     * @param eventID
     * @return EventResult
     */
    public EventResult event(String eventID){
        return null;
    }

    /**
     * Get all the events that are available on the database
     * @return List
     */
    public List<EventResult> event(){
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
