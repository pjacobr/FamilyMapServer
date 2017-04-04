package familyapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import request.LoginRequest;
import request.PersonRequest;
import result.LoginResult;
import result.PersonResult;
import server.ServerProxy;

/**
 * Created by jacob on 3/25/2017.
 */

public class LoginTask extends AsyncTask<LoginRequest, Void, LoginResult> {
    familyapp.Context fm;
    public LoginTask(familyapp.Context fm){
       this.fm = fm;
    }

    @Override
    protected LoginResult doInBackground(LoginRequest... params) {
        ModelContainer m = ModelContainer.getModelInstance();
        //connect to the server
        ServerProxy proxy = new ServerProxy( m.getIpAddress() ,m.getHostPort());
        LoginResult result = proxy.login(params[0]);
        //give it the auth token from the login
        m.setAuthToken(result.getAuthToken(), result.getUserName());
        m.setUserID(result.getPersonID());

        return result;
    }

    @Override
    public void onPostExecute(LoginResult login){
        //update stuff
        ModelContainer m = ModelContainer.getModelInstance();
        if( login.getMessage() != null){
            fm.MakeToast("User is not Registered");
            return;
        }
        SyncTask sync = new SyncTask(fm, true);
        sync.execute();
    }

}
