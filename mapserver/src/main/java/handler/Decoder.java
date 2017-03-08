package handler;

import com.google.gson.Gson;

import request.LoadRequest;
import request.RegisterRequest;

/**
 * Created by jacob on 2/16/2017.
 */

public class Decoder {

    //
    public RegisterRequest fromJSONtoRegisterRequest(String Rjson){
        Gson convert = new Gson();
        RegisterRequest request = convert.fromJson(Rjson, RegisterRequest.class);
        return request;
    }
    //Get a load request object to call the rest of the functions
    public LoadRequest fromJSONtoLoadRequest(String Ljson){
        Gson convert = new Gson();
        LoadRequest loadRequest = convert.fromJson(Ljson, LoadRequest.class);
        return loadRequest;
    }
}
