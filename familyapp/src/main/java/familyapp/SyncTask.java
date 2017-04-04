package familyapp;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import request.LoginRequest;
import request.PersonRequest;
import result.LoginResult;
import result.PersonResult;
import result.RegisterResult;
import server.ServerProxy;

/**
 * Created by jacob on 3/27/2017.
 */

public class SyncTask extends AsyncTask<Void, Void, Void> {
        ModelContainer cm;
        Context fm;
        boolean register;
        public SyncTask(Context fm, boolean register){
            cm = ModelContainer.getModelInstance();
            this.fm = fm;
            this.register = register;
        }

        @Override
        protected Void doInBackground(Void ... Void) {
           ServerProxy proxy = new ServerProxy(cm.getAuthToken().getAuthToken(),cm.getIpAddress(),cm.getHostPort());
           cm.setEvents(proxy.event());
           cm.setPersons(proxy.person());
           PersonResult person = proxy.person(new PersonRequest(cm.getUserID()));
           cm.setFirstName(person.getFirstname());
           cm.setLastName(person.getLastname());
           cm.setGender(person.getGender());
           cm.setMother(person.getMother());
           cm.setFather(person.getFather());
           cm.setUserID(person.getPersonID());
           return null;
        }

        //After we are done with this.
        protected void onPostExecute(Void v){
            fm.MakeToast("ON Post");
            if(register){
                fm.MakeToast("Successfully logged in " + cm.getFirstName() + " " + cm.getLastName());
                fm.onComplete();
            }else{
                fm.MakeToast("Successfully synced");
            }

        }
}
