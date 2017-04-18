package familyapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
import com.amazon.geo.mapsv2.CameraUpdate;
import com.amazon.geo.mapsv2.CameraUpdateFactory;
import com.amazon.geo.mapsv2.OnMapReadyCallback;
import com.amazon.geo.mapsv2.SupportMapFragment;
import com.amazon.geo.mapsv2.internal.ICameraUpdateDelegate;
import com.amazon.geo.mapsv2.model.BitmapDescriptorFactory;
import com.amazon.geo.mapsv2.model.CameraPosition;
import com.amazon.geo.mapsv2.model.LatLng;
import com.amazon.geo.mapsv2.model.Marker;
import com.amazon.geo.mapsv2.model.MarkerOptions;
import com.amazon.geo.mapsv2.model.Polyline;
import com.amazon.geo.mapsv2.model.PolylineOptions;
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
    //Constants and definitions
    private final int LINE_INITIAL_WIDTH = 10;
    private final double LINE_DEPRECATION_RATE = .8;
    private final int LINE_ARRAY_SIZE = 2;


    //what kind of data will I need floating around here
    View personGender;
    private AmazonMap amazonMap;
    private TextView personInfoText;
    private LinearLayout personInfo;
    //The marker that was most recently clicked
    private Marker curMarker;
    Context context;
    /*
     * Map Markers associated with the events that they are attached to
     * Key is Map Pin Marker
     * Value is the associated Map Key
     */
    private Map<Marker, EventResult> markerInfo = new HashMap<>();

    /**
     * Event ID associated with a given marker
     * Key is the event ID
     * Value is the associated Marker
     */
    private Map<String, Marker> eventMarker = new HashMap<>();
    //List of all the People associated with the current user
    private List<PersonResult> persons;
    // List of all the event results for the current User
    private List<EventResult> events = new ArrayList<>();
    /*
    * Events by Type
    * Key is the type of the Event
    * Value is the Event associated with the event
    */
    private Map<String, List<EventResult>> eventList;
    /*
    * People associated by ID
    *   KEY is person ID
    *   VALUE is Person Event
    */
    private Map<String, PersonResult> accessPersons = new HashMap<>();

    private List<Polyline> polylines = new ArrayList<>();
    public MapFragment() {
        for (PersonResult person : ModelContainer.getModelInstance().getPersons())  {
                accessPersons.put(person.getPersonID(), person);
        }
        this.context = context;
    }

    private boolean isMapActivity = ModelContainer.getModelInstance().isMapActivity();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //what do I create here??
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       super.onCreateOptionsMenu(menu, inflater);
        if(isMapActivity){
            inflater.inflate(R.menu.person_toolbar, menu);
        }else {
            inflater.inflate(R.menu.map_toolbar, menu);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(isMapActivity){
           // Handle item selection
            switch (item.getItemId()) {
                case R.id.up_button:
                    ModelContainer.getModelInstance().setMapActivity(false);
                    ((MapActivity)getActivity()).goUp();
                    return true;
                case android.R.id.home:
                    ModelContainer.getModelInstance().setMapActivity(false);
                    getActivity().finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
          //getActivity().onOptionsItemSelected(item);
        }else {
            // Handle item selection
            switch (item.getItemId()) {
                case R.id.filterIcon:
                    ((MainActivity) getActivity()).startNewActivity(ActivityType.FILTER_ACTIVITY);
                    return true;
                case R.id.settingsIcon:
                    ((MainActivity) getActivity()).startNewActivity(ActivityType.SETTINGS_ACTIVITY);
                    return true;
                case R.id.searchIcon:
                    ((MainActivity) getActivity()).startNewActivity(ActivityType.SEARCH_ACTIVITY);
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);


        //MenuInflater.inflate(R.menu.map_toolbar, menu);

        ModelContainer m = ModelContainer.getModelInstance();
        //m.setEventList();
        //Extract a reference to the map fragment
        //amazonMap.addPolyline();
        amazonMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        amazonMap.setMapType(AmazonMap.MAP_TYPE_HYBRID);
        if(isMapActivity) {
            curMarker = ModelContainer.getModelInstance().getEventMarker().get(ModelContainer.getModelInstance().curEvent.getEventID());
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(curMarker.getPosition(), 0, 0, 0));
            amazonMap.animateCamera(update);
        }


        personInfo = (LinearLayout) v.findViewById(R.id.person_info_wrapper);
        personInfo.setVisibility(View.GONE);
        personInfoText = (TextView) v.findViewById(R.id.person_id);
        personGender = (View) v.findViewById(R.id.person_gender);

        personInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EventResult e = markerInfo.get(curMarker);
                ModelContainer.getModelInstance().curEvent = e;
                ModelContainer.getModelInstance().curPerson = accessPersons.get(e.getPersonID());
                // Intent intent = new Intent((MainActivity)getActivity().getBaseContext(), PersonActivity.class);
                if (isMapActivity) {
                    ((MapActivity) getActivity()).startNewActivity(ActivityType.PERSON_ACTIVITY);
                } else {
                    ((MainActivity) getActivity()).startNewActivity(ActivityType.PERSON_ACTIVITY);
                }
                List<PersonResult> list = ModelContainer.getModelInstance().getPersons();
                list.isEmpty();
            }
        });


        //TODO refactor the code into functions
        eventList = (HashMap) m.getEventList();
        events = (ArrayList) m.getEvents();
        int hue = 35;

        //function Initialize map data containers
        // Initialize the eventResults and get them organized by type and also connect them to their persons.
        if (!isMapActivity) {
            for (EventResult eventResult : events) {
                if (!eventList.containsKey(eventResult.getEventType())) {
                    eventList.put(eventResult.getEventType(), new ArrayList<EventResult>());

                }
                //add the event to the map of that is connected to its type
                ((ArrayList) eventList.get(eventResult.getEventType())).add(eventResult);
                //go through and add the events connect to the person.


                for (PersonResult person : m.getPersons()) {
                    if (person.getPersonID().equals(eventResult.getPersonID())) {
                        person.getEvents().add(eventResult);
                        //Once I find the person that it is connected to I can jump out because it should not be connected to multiple people.
                        break;
                    }
                }
            }
         }
        int numEventTypes = eventList.size();
        //TODO Why do I want to keep track of the MARKERS??
        int hueOffset = 1;
        if(numEventTypes > 0) {
            hueOffset = 325 / numEventTypes;
        }

        ModelContainer.getModelInstance().setMarkerInfo(new HashMap<Marker, EventResult>());
        // PersonResult

        //Set the Markers
        Map<String, PersonResult> info = m.getAccessPersons();
        for (Map.Entry<String, List<EventResult>> entry : eventList.entrySet()) {
            for (EventResult event : entry.getValue()) {
                String firstName = info.get(event.getPersonID()).getFirstname();
                String lastName = info.get(event.getPersonID()).getLastname();
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(new LatLng(event.getLatitude(), event.getLongitude()))
                        .alpha(5)
                        //.title(firstName + " " + lastName + "\n" + event.getCity() + ", " + event.getCountry() + "\n" + event.getEventType())
                        .icon(BitmapDescriptorFactory.defaultMarker(hue));
                Marker mark = amazonMap.addMarker(markerOptions);
                markerInfo.put(mark, event);
                eventMarker.put(event.getEventID(),mark);
            }
            ModelContainer.getModelInstance().setMarkerInfo(markerInfo);
            ModelContainer.getModelInstance().setEventMarker(eventMarker);
            int i = (hue + hueOffset + 20 < 360) ? hue += (hueOffset + 15) : (hue += hueOffset);
        }



        /**
         * Go through and set a on click function for  each marker
         *
         * */
        amazonMap.setOnMarkerClickListener(new AmazonMap.OnMarkerClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public boolean onMarkerClick(Marker marker) {
                for(Polyline line : polylines){
                    line.remove();
                }
                addFamilyTreeLines(marker, LINE_INITIAL_WIDTH);
                addSpouseLines(marker);
                addLifeLines(marker);
                EventResult event = markerInfo.get(marker);
                PersonResult person = accessPersons.get(event.getPersonID());
                personInfoText.setText(person.getFirstname() + " " + person.getLastname() + "\n" + event.getCity() + ", " + event.getCountry()
                        + "\n" + ToolBox.capitalizeFirstLetter(event.getEventType()) + " " + event.getYear());
                personInfo.setVisibility(View.VISIBLE);
                //((MainActivity) getActivity()).MakeToast(person.getGender());
                if(person.getGender().equals("m")) {
                    personGender.setBackground(getActivity().getDrawable(R.mipmap.person_info_male_blue));
                }else{
                    personGender.setBackground(getActivity().getDrawable(R.mipmap.person_info_female_pink));
                }
                    curMarker = marker;
                return false;
            }
        });
        //amazonMap.getCameraPosition();
        // Call .getMapAsync() and pass it an object that implements
        // the OnMapReadyCallback interface
        return v;
    }

    private void addFamilyTreeLines(Marker marker, int lineSize){
        //get family members
        EventResult event = markerInfo.get(marker);

        PersonResult dad = ToolBox.getFather(event.getPersonID());
        PersonResult mom = ToolBox.getMother(event.getPersonID());

        //make sure that they have parents
        if(dad != null && mom != null) {

            //get marker for first events **Should be the birth event, unless one does not exist
            Marker dadConnection = eventMarker.get(dad.getEvents().get(0).getEventID());
            Marker momConnection = eventMarker.get(mom.getEvents().get(0).getEventID());

                //put those lines to be drawn in a vato
            //amazonMap.setOnMarkerClickListener()
            //add the line
            ArrayList<LatLng> dadPoint = new ArrayList<LatLng>(LINE_ARRAY_SIZE);
            ArrayList<LatLng> momPoint = new ArrayList<LatLng>(LINE_ARRAY_SIZE);
            dadPoint.add(marker.getPosition());
            momPoint.add(marker.getPosition());
            momPoint.add(momConnection.getPosition());
            dadPoint.add(dadConnection.getPosition());
            PolylineOptions momOpt = new PolylineOptions()
                    .addAll(momPoint)
                    .color(Color.RED)
                    .geodesic(true)
                    .width(lineSize);
            PolylineOptions dadOpt = new PolylineOptions()
                    .addAll(dadPoint)
                    .color(Color.RED)
                    .geodesic(true)
                    .width(lineSize);
            Polyline p = amazonMap.addPolyline(momOpt);
            Polyline d = amazonMap.addPolyline(dadOpt);
            polylines.add(p);
            polylines.add(d);
            addFamilyTreeLines(dadConnection, (int)(lineSize*=LINE_DEPRECATION_RATE));
            addFamilyTreeLines(momConnection, (int)(lineSize));
        }
     return;
    }

    private void addSpouseLines(Marker marker){
        //get family members
        EventResult event = markerInfo.get(marker);
        PersonResult spouse = ToolBox.getSpouse(event.getPersonID());

        //make sure that they have parents
        if(spouse != null) {

            //get marker for first events
            Marker spouseConnection = eventMarker.get(spouse.getEvents().get(0).getEventID());

            //put those lines to be drawn in a vato
            //amazonMap.setOnMarkerClickListener()
            //add the line
            ArrayList<LatLng> spousePoint = new ArrayList<LatLng>(LINE_ARRAY_SIZE);
            spousePoint.add(marker.getPosition());
            spousePoint.add(spouseConnection.getPosition());
            PolylineOptions spouseOpt = new PolylineOptions()
                    .addAll(spousePoint)
                    .color(Color.BLUE)
                    .geodesic(true)
                    .width(LINE_INITIAL_WIDTH);
            Polyline d = amazonMap.addPolyline(spouseOpt);
            polylines.add(d);
            //addFamilyTreeLines(spouseConnection, (int)(lineSize*=.8));
        }
        return;
    }
    private void addLifeLines(Marker marker){
        //get family members
        EventResult event = markerInfo.get(marker);
        PersonResult person = accessPersons.get(event.getPersonID());
        List<EventResult> lifeEvents = person.getEvents();


        //make sure that they have parents
        if(person != null) {
            for(int i = 0; i < lifeEvents.size()-1; i++) {
                //get marker for first events
                Marker firstConnection = eventMarker.get(lifeEvents.get(i).getEventID());
                Marker secondConnection = eventMarker.get(lifeEvents.get(i+1).getEventID());
                //put those lines to be drawn in a vato
                //amazonMap.setOnMarkerClickListener()
                //add the line
                ArrayList<LatLng> lifeLine = new ArrayList<LatLng>(LINE_ARRAY_SIZE);
                lifeLine.add(firstConnection.getPosition());
                lifeLine.add(secondConnection.getPosition());
                PolylineOptions spouseOpt = new PolylineOptions()
                        .addAll(lifeLine)
                        .color(Color.GREEN)
                        .geodesic(true)
                        .width(LINE_INITIAL_WIDTH);
                Polyline d = amazonMap.addPolyline(spouseOpt);
                polylines.add(d);
                //addFamilyTreeLines(spouseConnection, (int)(lineSize*=.8));
            }
        }
        return;
    }
}

