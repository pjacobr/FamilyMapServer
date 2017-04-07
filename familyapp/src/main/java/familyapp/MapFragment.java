package familyapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private AmazonMap amazonMap;
    private TextView personInfo;
    //TODO put this in the Modelclass
    private Map<Marker, EventResult> markerInfo;

    public MapFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //what do I create here??

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        MainActivity mainActivity = (MainActivity)getActivity();
        @Override
        mainActivity.onCreateOptionsMenu(Menu menu) {


            MenuInflater.inflate(R.menu.map_toolbar, menu);
        }
        ModelContainer m = ModelContainer.getModelInstance();
        //  m.setEventList();
        // Extract a reference to the map fragment
        //amazonMap.addPolyline();
        amazonMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        amazonMap.setMapType(AmazonMap.MAP_TYPE_HYBRID);

        personInfo = (TextView) v.findViewById(R.id.person_id);
        personInfo.setVisibility(View.GONE);


        //TODO refactor the code into functions
        Map<String, List<EventResult>> eventsType = (HashMap) m.getEventList();
        List<EventResult> events = (ArrayList) m.getEvents();
        int hue = 35;
        int numEventTypes = 0;
        //function Initialize map data containers
        // Initialize the eventResults and get them organized by type and also connect them to their persons.
        for (EventResult eventResult : events) {
            if (!eventsType.containsKey(eventResult.getEventType())) {
                eventsType.put(eventResult.getEventType(), new ArrayList<EventResult>());
                numEventTypes++;
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
        //TODO Why do I want to keep track of the MARKERS??
        int hueOffset = 325 / numEventTypes;
        markerInfo = new HashMap<>();
        // PersonResult
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
                markerInfo.put(mark, event);
            }
            int i = (hue + hueOffset + 20 < 360) ? hue += (hueOffset + 15) : (hue += hueOffset);
        }

        amazonMap.setOnMarkerClickListener(new AmazonMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                EventResult event = markerInfo.get(marker);
                PersonResult person = ModelContainer.getModelInstance().getAccessPersons().get(event.getPersonID());
                personInfo.setText(person.getFirstname() + " " + person.getLastname() + "\n" + event.getCity() + ", " + event.getCountry()
                        + "\n" + ToolBox.capitalizeFirstLetter(event.getEventType()) + " " + event.getYear());
                personInfo.setVisibility(View.VISIBLE);
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

