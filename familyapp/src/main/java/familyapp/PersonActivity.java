package familyapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

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

    }

    @Override
    public void onComplete() {

    }



    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);


        //lookup the recyclerview in activity layout
        //RecyclerView children = (RecyclerView)findViewById(R.id.person_children);
        //initialize children
        //childrenList = (ArrayList)ModelContainer.getModelInstance().getPersons();

        //PersonAdapter adapter = new PersonAdapter(this, childrenList);

        //Attach tehadapter to the recyclerview to populate items
        //children.setAdapter(adapter);
        //Set Layout manager to position the items
        //children.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }



}
