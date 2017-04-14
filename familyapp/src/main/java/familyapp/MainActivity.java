package familyapp;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.familyapp.R;

import result.PersonResult;

public class MainActivity extends AppCompatActivity implements Context{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null && ModelContainer.getModelInstance().getAuthToken() == null){
            fragment = new LoginFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }else if(fragment == null && ModelContainer.getModelInstance().getAuthToken() != null){
            fragment = new MapFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }else{

        }

    }



    public void changeToRegisterFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new RegisterFragment())
                .commit();
    }
    public void backToLoginFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new LoginFragment())
                .commit();
    }
    public void goToMapFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new MapFragment())
                .commit();
    }

    public void startNewActivity(ActivityType type){
        Object objectType = null;

        switch (type){
            case SEARCH_ACTIVITY:
                objectType = new SearchActivity();
                break;
            case SETTINGS_ACTIVITY:
                objectType = new SettingsActivity();
                break;
            case FILTER_ACTIVITY:
                objectType = new FilterActivity();
                break;
            case PERSON_ACTIVITY:
                objectType = new PersonActivity(/*(PersonResult)info*/);
                break;
            default:
                System.out.print("Error");
                break;
        }
           Intent myIntent = new Intent(this, objectType.getClass());

        //myIntent.putExtra("key", value); //Optional parameters
        startActivity(myIntent);
    }

    @Override
    public void MakeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onComplete() {
        goToMapFragment();
    }
}
