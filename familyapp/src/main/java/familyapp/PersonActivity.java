package familyapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.familyapp.R;

import java.util.ArrayList;

import result.PersonResult;

/**
 * Created by jacob on 4/12/2017.
 */

public class PersonActivity extends AppCompatActivity implements Context {

    ArrayList<PersonResult> childrenList;

    public PersonActivity(){
    }

    @Override
    public void MakeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onComplete() {

    }



    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //lookup the recyclerview in activity layout
        RecyclerView children = (RecyclerView)findViewById(R.id.person_children);
        //initialize children
        childrenList = (ArrayList)ModelContainer.getModelInstance().getPersons();

        PersonAdapter adapter = new PersonAdapter(this, childrenList);

        //Attach tehadapter to the recyclerview to populate items
        children.setAdapter(adapter);
        //Set Layout manager to position the items
        children.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.up_button:
                goUp();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.person_toolbar, menu);
        return true;
    }

    private void goUp(){
        Intent myIntent = new Intent(this, MainActivity.class);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //myIntent.putExtra("key", value); //Optional parameters
        startActivity(myIntent);
    }

}
