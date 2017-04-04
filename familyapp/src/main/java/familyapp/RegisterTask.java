package familyapp;

import android.os.AsyncTask;

import request.LoginRequest;
import request.PersonRequest;
import request.RegisterRequest;
import result.LoginResult;
import result.RegisterResult;
import server.ServerProxy;

/**
 * Created by jacob on 3/27/2017.
 */

public class RegisterTask extends AsyncTask<RegisterRequest, Void, RegisterResult> {
        private Context fm;
        public RegisterTask(Context fm){
            this.fm = fm;
        }

        @Override
        protected RegisterResult doInBackground(RegisterRequest... params) {
            ModelContainer m = ModelContainer.getModelInstance();
            ServerProxy proxy = new ServerProxy(m.getIpAddress(), m.getHostPort());
            RegisterResult result = proxy.register(params[0]);
            m.setAuthToken(result.getAuthToken(), result.getUserName());
            m.setUserID(result.getPersonID());
            //LoginResult r = new LoginResult("pjacobr", "auth", "what");
            return result;
        }

        @Override
        public void onPostExecute(RegisterResult login){
            //update stuffmGender.getText().toString()
            ModelContainer m = ModelContainer.getModelInstance();
            SyncTask sync = new SyncTask(fm, true);
            sync.execute();

            fm.MakeToast("Thank you for registering "  + m.getFirstName() + " " + m.getLastName());
        }
}
