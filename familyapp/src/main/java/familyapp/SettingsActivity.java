package familyapp;

import android.content.Intent;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.familyapp.R;

import request.LoginRequest;

/**
 * Created by jacob on 4/13/2017.
 */

public class SettingsActivity extends AppCompatActivity implements familyapp.Context {

    Spinner lifeStoryLineColors;
    ToggleButton lifeStoryOn;
    Spinner familyTreeLineColors;
    ToggleButton familyTreeLineOn;
    Spinner spouseLineColors;
    ToggleButton spouseLineOn;
    Spinner mapType;
    LinearLayout resyncData;
    LinearLayout logout;
    //we will need a list of colors that they can choose from
    //as well a list of map types
    @Override
    public void MakeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onComplete() {
        Intent i = new Intent(this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public SettingsActivity(){

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set all the buttons and what not
        lifeStoryLineColors = (Spinner)findViewById(R.id.life_story_line_color);
        lifeStoryOn = (ToggleButton)findViewById(R.id.life_story_line_on);
        familyTreeLineColors = (Spinner)findViewById(R.id.family_tree_line_color);
        familyTreeLineOn = (ToggleButton)findViewById(R.id.family_tree_line_on);
        spouseLineColors = (Spinner)findViewById(R.id.spouse_line_color);
        spouseLineOn = (ToggleButton)findViewById(R.id.spouse_lines_on);
        mapType = (Spinner)findViewById(R.id.map_type);
        resyncData = (LinearLayout)findViewById(R.id.resync_data);
        logout = (LinearLayout)findViewById(R.id.logout);

        lifeStoryOn.setChecked(ModelContainer.getModelInstance().isLifeLines());
        familyTreeLineOn.setChecked(ModelContainer.getModelInstance().isFamilyLines());
        spouseLineOn.setChecked(ModelContainer.getModelInstance().isSpouseLines());

     // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
       // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // Apply the adapter to the spinner
        lifeStoryLineColors.setAdapter(adapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        familyTreeLineColors.setAdapter(adapter1);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spouseLineColors.setAdapter(adapter2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.map_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mapType.setAdapter(adapter3);
        //using the spinner compare function get the position of the specified object
        int spinnerPosition = adapter.getPosition(ModelContainer.getModelInstance().getLifeLineColor());
        lifeStoryLineColors.setSelection(spinnerPosition);
        spinnerPosition = adapter1.getPosition(ModelContainer.getModelInstance().getFamilyLineColor());
        familyTreeLineColors.setSelection(spinnerPosition);
        spinnerPosition = adapter2.getPosition(ModelContainer.getModelInstance().getSpouseLineColor());
        spouseLineColors.setSelection(spinnerPosition);
        spinnerPosition = adapter3.getPosition(ModelContainer.getModelInstance().getMapType());
        mapType.setSelection(spinnerPosition);

        spouseLineColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ModelContainer.getModelInstance().setSpouseLineColor(spouseLineColors.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        mapType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ModelContainer.getModelInstance().setMapType(mapType.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        familyTreeLineColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ModelContainer.getModelInstance().setFamilyLineColor(familyTreeLineColors.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        lifeStoryLineColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ModelContainer.getModelInstance().setLifeLineColor(lifeStoryLineColors.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });
        //If the spouse toggle button is turned off then the lines that show those events will not be showed
        lifeStoryOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    ModelContainer.getModelInstance().setLifeLines(true);
                }else{
                    ModelContainer.getModelInstance().setLifeLines(false);
                }
            }
        });
        //If the spouse toggle button is turned off then the lines that show those events will not be showed
        familyTreeLineOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    ModelContainer.getModelInstance().setFamilyLines(true);
                }else{
                    ModelContainer.getModelInstance().setFamilyLines(false);
                }
            }
        });
        //If the spouse toggle button is turned off then the lines that show those events will not be showed
        spouseLineOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    ModelContainer.getModelInstance().setSpouseLines(true);
                }else{
                    ModelContainer.getModelInstance().setSpouseLines(false);
                }
            }
        });

        resyncData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Go in and resync the data and make sure that everything is upto date
                ModelContainer.getModelInstance().syncData();
                syncData();

            }});

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ModelContainer.getModelInstance().clear();
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });


    }
    private void syncData(){
        LoginTask lg = new LoginTask(this);
        LoginRequest request = new LoginRequest(ModelContainer.getModelInstance().getUsername(), ModelContainer.getModelInstance().getPassword());
        lg.execute(request);
    }






}
