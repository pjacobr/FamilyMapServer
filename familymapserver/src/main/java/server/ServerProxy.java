package server;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    private static ServerProxy _instance;




    public static void main(String[] args){
        String ipaddress = null;
        String portNumber = null;
        if(args != null){
            ipaddress = args[0];
            portNumber = args[1];

            ServerProxy client = new ServerProxy(ipaddress, portNumber);
        }
    }

    String authToken = null;
    //serverHost is the IP ADDRESS
    String serverHost = null;
    String serverPort = null;

    public ServerProxy(String authToken, String serverHost, String serverPort) {
        this(serverHost, serverPort);
        this.authToken = authToken;
    }

    public ServerProxy(String serverHost, String serverPort){
        this.serverPort = serverPort;
        this.serverHost = serverHost;
    }


    //http.addRequestProperty("Authorization", "afj232hj2332");
    //http.addRequestProperty("Content-Type", "application/json");

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

    //
    public RegisterResult register(RegisterRequest rg) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/user/register");

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);    // There is a request body
            http.connect();

            Gson gs = new Gson();
            String jsonString = gs.toJson(rg);
            OutputStream reqBody = http.getOutputStream();
            writeString(jsonString, reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                RegisterResult result = gs.fromJson(respData, RegisterResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            } else {
                return new RegisterResult("ERROR: " + http.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new RegisterResult(e.getMessage());
        }
    }

    public LoginResult login(LoginRequest lg) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/user/login");

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);    // There is a request body
            http.connect();

            //Convert to JSON and back
            Gson gs = new Gson();
            String jsonString = gs.toJson(lg);
            OutputStream reqBody = http.getOutputStream();
            writeString(jsonString, reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                LoginResult result = gs.fromJson(respData, LoginResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            } else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new LoginResult("Error" + http.getResponseMessage());
            }
        } catch (IOException e) {
            return new LoginResult(e.toString());
        }

    }

    /**
     * Clear all the data in the database so you can start over
     *
     * @return ClearResult
     */
    public ClearResult clear() {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/clear/");

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(false);    // There is a request body
            http.connect();


            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                ClearResult result = gs.fromJson(respData, ClearResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            } else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new ClearResult("Error" + http.getResponseMessage());
            }
        } catch (IOException e) {
            return new ClearResult(e.toString());
        }
    }

    /**
     * Fill the persons heritage up to the number of generations
     *
     * @param lg username of the logged in perso
     * @return
     */
    public FillResult fill(FillRequest lg) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/fill/" + lg.getUsername());

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(false);    // There is a request body
            http.connect();

            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                FillResult result = gs.fromJson(respData, FillResult.class);
                System.out.println("Route successfully claimed.");
                return result;
            } else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new FillResult("Error" + http.getResponseMessage());
            }
        } catch (IOException e) {
            return new FillResult(e.toString());
        }
    }

    /**
     * Load the database with a bunch of data
     *
     * @param lg load request for the load
     * @return
     */
    public LoadResult load(LoadRequest lg) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/load/");

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("POST");
            http.setDoOutput(true);    // There is a request body
            http.connect();


            Gson gs = new Gson();
            String jsonString = gs.toJson(lg);
            OutputStream reqBody = http.getOutputStream();
            writeString(jsonString, reqBody);
            reqBody.close();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                LoadResult result = gs.fromJson(respData, LoadResult.class);
                //System.out.println("Route successfully claimed.");
                return result;
            } else {
                //System.out.println("ERROR: " + http.getResponseMessage());
                return new LoadResult("Error" + http.getResponseMessage());
            }
        } catch (IOException e) {
            return new LoadResult(e.toString());
        }
    }

    /**
     * Get a person based on the id given
     *
     * @param lg
     * @return PersonResult
     */
    public PersonResult person(PersonRequest lg) {
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/person/" + lg.getPersonID());

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("GET");
            http.setDoOutput(false);    // There is a request body
            http.addRequestProperty("Authorization", authToken);
            // http.addRequestProperty("Accept", "/person" );
            //http.addRequestProperty("Content-Type", "application/json");
            http.connect();
            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                PersonResult result = gs.fromJson(respData, PersonResult.class);
                //System.out.println("Route successfully claimed.");
                return result;
            } else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new PersonResult("Error" + http.getResponseMessage());
            }
        } catch (IOException e) {
            return new PersonResult(e.toString());
        }
    }

    /**
     * get all the people that are currently in the database
     *
     * @return List
     */
    public ArrayList<PersonResult> person() {
        ArrayList<PersonResult> f = new ArrayList<PersonResult>();
        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/person/");

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("GET");
            http.setDoOutput(false);    // There is a request body
            http.addRequestProperty("Authorization", authToken);
            http.addRequestProperty("Accept", "/person/");
            //http.addRequestProperty("Content-Type", "application/json");
            http.connect();
            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {

                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);

                //get the results
                PersonResult[] results = null;

                //Get the object in json
                results = gs.fromJson(respData, PersonResult[].class);
                System.out.println("Route successfully claimed.");

                for(PersonResult r : results){
                    f.add(r);
                }
                return f;
            } else {
                System.out.println("ERROR: " + http.getResponseMessage());
                PersonResult[] results = new PersonResult[1];
                results[0] =  new PersonResult("Error" + http.getResponseMessage());
                f.add(results[0]);
                return f;
            }
        } catch (IOException e) {
            PersonResult[] results = new PersonResult[1];
            results[0] =  new PersonResult(e.getMessage());;
            f.add(results[0]);
            return f;
        }
    }

    /**
     * Get one event based on the event id
     *
     * @return EventResult
     */
    public List<EventResult> event() {

        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/event/");

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("GET");
            http.setDoOutput(false);    // There is a request body
            http.addRequestProperty("Authorization", authToken);
            http.addRequestProperty("Accept", "/event/");

            http.connect();
            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);
                EventResult[] results = null;
                results = gs.fromJson(respData, EventResult[].class);
                List<EventResult> results1= new ArrayList<>();
                for(EventResult ev : results){
                    results1.add(ev);
                }
                System.out.println("Route successfully claimed.");
                return results1;
            } else {
                System.out.println("ERROR: " + http.getResponseMessage());
                List<EventResult> results = new ArrayList<>();
                results.add(new EventResult("Error" + http.getResponseMessage()));
                return results;
            }
        } catch (IOException e) {
            List<EventResult> results = new ArrayList<>();
            results.add(new EventResult(e.toString()));
            return results;
        }
    }

    /**
     * Get all the events that are available on the database
     *
     * @return List
     */
    public EventResult event(String id) {

        try {
            URL url = new URL("http://" + serverHost + ":" + serverPort + "/event/" + id);

            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            http.setRequestMethod("GET");
            http.setDoOutput(false);    // There is a request body
            http.addRequestProperty("Authorization", authToken);
            http.addRequestProperty("Accept", "/event/");

            http.connect();
            Gson gs = new Gson();

            if (http.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream respBody = http.getInputStream();
                String respData = readString(respBody);

                EventResult ev = gs.fromJson(respData, EventResult.class);
                System.out.println("Route successfully claimed.");
                return ev;
            } else {
                System.out.println("ERROR: " + http.getResponseMessage());
                return new EventResult("Error" + http.getResponseMessage());
            }
        } catch (IOException e) {

            return new EventResult(e.toString());
        }
    }


}
