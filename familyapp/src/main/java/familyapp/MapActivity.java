package familyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.CameraUpdateFactory;
import com.amazon.geo.mapsv2.OnMapReadyCallback;
import com.example.familyapp.R;

/**
 * Created by jacob on 4/17/2017.
 */

public class MapActivity extends AppCompatActivity{
    public MapActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = new MapFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }

    public void goUp(){
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //myIntent.putExtra("key", value); //Optional parameters
        startActivity(myIntent);
    }

    public void startNewActivity(ActivityType type){
        Object objectType = null;

        switch (type){
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

}
