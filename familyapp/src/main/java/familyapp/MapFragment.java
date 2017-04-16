package familyapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.OnMapReadyCallback;
import com.amazon.geo.mapsv2.SupportMapFragment;
import com.amazon.geo.mapsv2.model.BitmapDescriptorFactory;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.Marker;
import com.amazon.geo.mapsv2.model.MarkerOptions;
import com.example.familyapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.Person;
import result.EventResult;
import result.PersonResult;


/**
 * Created by jacob on 3/22/2017.
 */

public class MapFragment extends Fragment {
    //what kind of data will I need floating around here
    View personGender;
    private AmazonMap amazonMap;
    private TextView personInfoText;
    private LinearLayout personInfo;
    //TODO put this in the Modelclass
    private Marker curMarker;
    Context context;
    public MapFragment() {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //what do I create here??
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.map_toolbar, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.filterIcon:
                ((MainActivity)getActivity()).startNewActivity(ActivityType.FILTER_ACTIVITY);
                return true;
            case R.id.settingsIcon:
                ((MainActivity)getActivity()).startNewActivity(ActivityType.SETTINGS_ACTIVITY);
                return true;
            case R.id.searchIcon:
                ((MainActivity)getActivity()).startNewActivity(ActivityType.SEARCH_ACTIVITY);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);
        MainActivity mainActivity = (MainActivity)getActivity();



            //MenuInflater.inflate(R.menu.map_toolbar, menu);

        ModelContainer m = ModelContainer.getModelInstance();
        //m.setEventList();
        //Extract a reference to the map fragment
        //amazonMap.addPolyline();
        amazonMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        amazonMap.setMapType(AmazonMap.MAP_TYPE_HYBRID);

        personInfo = (LinearLayout) v.findViewById(R.id.person_info_wrapper);
        personInfo.setVisibility(View.GONE);
        personInfoText = (TextView)v.findViewById(R.id.person_id);
        personGender = (View)v.findViewById(R.id.person_gender);

        personInfo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
               EventResult e = ModelContainer.getModelInstance().getMarkerInfo().get(curMarker);
               ModelContainer.getModelInstance().curEvent = e;
               ModelContainer.getModelInstance().curPerson = ModelContainer.getModelInstance().getAccessPersons().get(e.getPersonID());
                // Intent intent = new Intent((MainActivity)getActivity().getBaseContext(), PersonActivity.class);
               ((MainActivity)getActivity()).startNewActivity(ActivityType.PERSON_ACTIVITY);
                List<PersonResult> list  = ModelContainer.getModelInstance().getPersons();
                list.isEmpty();
            }
        });


        //TODO refactor the code into functions
        Map<String, List<EventResult>> eventsType = (HashMap) m.getEventList();
        List<EventResult> events = (ArrayList) m.getEvents();
        int hue = 35;

        //function Initialize map data containers
        // Initialize the eventResults and get them organized by type and also connect them to their persons.
        for (EventResult eventResult : events) {
            if (!eventsType.containsKey(eventResult.getEventType())) {
                eventsType.put(eventResult.getEventType(), new ArrayList<EventResult>());

            }
            //add the event to the map of that is connected to its type
            ((ArrayList) eventsType.get(eventResult.getEventType())).add(eventResult);
            //go through and add the events connect to the person.
            for (PersonResult person : m.getPersons()) {
                if (person.getPersonID().equals(eventResult.getPersonID())) {
                    person.getEvents().add(eventResult);
                    //Once I find the person that it is connected to I can jump out because it should not be connected to multiple people.
                    break;
                }
            }
        }
        int numEventTypes = eventsType.size();
        //TODO Why do I want to keep track of the MARKERS??
        int hueOffset = 1;
        if(numEventTypes > 0) {
            hueOffset = 325 / numEventTypes;
        }

        ModelContainer.getModelInstance().setMarkerInfo(new HashMap<Marker, EventResult>());
        // PersonResult

        //Set the Markers
        Map<String, PersonResult> info = m.getAccessPersons();
        for (Map.Entry<String, List<EventResult>> entry : eventsType.entrySet()) {
            for (EventResult event : entry.getValue()) {
                String firstName = info.get(event.getPersonID()).getFirstname();
                String lastName = info.get(event.getPersonID()).getLastname();
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(new LatLng(event.getLatitude(), event.getLongitude()))
                        .alpha(5)
                        //.title(firstName + " " + lastName + "\n" + event.getCity() + ", " + event.getCountry() + "\n" + event.getEventType())
                        .icon(BitmapDescriptorFactory.defaultMarker(hue));
                Marker mark = amazonMap.addMarker(markerOptions);
                ModelContainer.getModelInstance().getMarkerInfo().put(mark, event);
                ModelContainer.getModelInstance().getEventMarker().put(event.getEventID(),mark);
            }
            int i = (hue + hueOffset + 20 < 360) ? hue += (hueOffset + 15) : (hue += hueOffset);
        }





        amazonMap.setOnMarkerClickListener(new AmazonMap.OnMarkerClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onMarkerClick(Marker marker) {
                EventResult event = ModelContainer.getModelInstance().getMarkerInfo().get(marker);
                PersonResult person = ModelContainer.getModelInstance().getAccessPersons().get(event.getPersonID());
                personInfoText.setText(person.getFirstname() + " " + person.getLastname() + "\n" + event.getCity() + ", " + event.getCountry()
                        + "\n" + ToolBox.capitalizeFirstLetter(event.getEventType()) + " " + event.getYear());
                personInfo.setVisibility(View.VISIBLE);
                ((MainActivity) getActivity()).MakeToast(person.getGender());
                if(person.getGender().equals("m")) {
                    personGender.setBackground(getActivity().getDrawable(R.mipmap.person_info_male_blue));
                }else{
                    personGender.setBackground(getActivity().getDrawable(R.mipmap.person_info_female_pink));
                }
                    curMarker = marker;
                return false;
            }
        });

        //amazonMap.setOnMarkerClickListener();

        //amazonMap.getCameraPosition();
        // Call .getMapAsync() and pass it an object that implements
        // the OnMapReadyCallback interface.

        return v;
    }



}

