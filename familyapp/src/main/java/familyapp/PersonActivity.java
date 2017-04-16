package familyapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.familyapp.R;

import java.util.ArrayList;

import result.EventResult;
import result.PersonResult;

/**
 * Created by jacob on 4/12/2017.
 */

public class PersonActivity extends AppCompatActivity implements Context {

    ArrayList<PersonResult> childrenList;
    ArrayList<EventResult> eventsList;
    Button changeViewPersonList;
    Button changeViewEventsList;


    RecyclerView children;
    RecyclerView eventChildren;
    TextView personInfo;
    TextView personGender;

    private PersonResult person;

    //PersonActivity constructor
    public PersonActivity(/*PersonResult person*/){
        this.person = ModelContainer.getModelInstance().curPerson;
    }

    @Override
    public void MakeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onComplete() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //lookup the recyclerview in activity layout
        children = (RecyclerView)findViewById(R.id.person_children);
        eventChildren = (RecyclerView)findViewById(R.id.person_events);
        //initialize children
        changeViewPersonList = (Button)findViewById(R.id.person_list_up);
        changeViewPersonList.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view){
                changeViewPersonList.setBackground(getDrawable(children.getVisibility() == View.VISIBLE ? R.mipmap.list_up_arrow : R.mipmap.list_down_arrow));
                children.setVisibility((children.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));
            }
        });

        changeViewEventsList = (Button)findViewById(R.id.event_list_up);
        changeViewEventsList.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            public void onClick(View view){
                changeViewEventsList.setBackground(getDrawable(eventChildren.getVisibility() == View.VISIBLE ? R.mipmap.list_up_arrow : R.mipmap.list_down_arrow));
                eventChildren.setVisibility((eventChildren.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE));
            }
        });
        childrenList = (ArrayList)ModelContainer.getModelInstance().getPersons();
        eventsList = (ArrayList<EventResult>)ModelContainer.getModelInstance().getEvents();

        personInfo = (TextView)findViewById(R.id.person_id);
        personGender = (TextView)findViewById(R.id.person_gender_activity);
        personInfo.setText(person.getFirstname() + "\n" + person.getLastname() + "\n" + (person.getGender().equals("m") ? "Male" : "Female"));
        personGender.setBackground((person.getGender().equals("m") ? getDrawable(R.mipmap.person_info_male_blue) : getDrawable(R.mipmap.person_info_female_pink)));

        PersonAdapter adapter = new PersonAdapter(this, ToolBox.getFamily(person.getPersonID()));
        EventsAdapter adapter1 = new EventsAdapter(this, person.getEvents());
        //Attach the adapter to the recyclerview to populate items
        eventChildren.setAdapter(adapter1);
        eventChildren.setLayoutManager(new LinearLayoutManager(this));
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
