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
    FragmentInterface fm;
    public LoginTask(FragmentInterface fm){
       this.fm = fm;
    }

    @Override
    protected LoginResult doInBackground(LoginRequest... params) {
        ServerProxy proxy = new ServerProxy(/*ipAddress*/null,/*fm.getHostPort()*/null);
        LoginResult result = proxy.login(params[0]);
        ModelContainer m = ModelContainer.getModelInstance();
        m.setAuthToken(result.getAuthToken(), result.getUserName());
        m.setUserID(result.getPersonID());
        //LoginResult r = new LoginResult("pjacobr", "auth", "what");
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
        SyncTask sync = null;
        fm.MakeToast("Auth Token: " + m.getFirstName() + " " + m.getLastName());
    }

}
