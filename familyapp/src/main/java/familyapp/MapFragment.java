package familyapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import result.EventResult;
import result.PersonResult;


/**
 * Created by jacob on 3/22/2017.
 */

public class MapFragment extends Fragment {
    //what kind of data will I need floating around here
    private SupportMapFragment map;
    private AmazonMap amazonMap;
    private List<Marker> markers;
    public MapFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //what do I create here??

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                            Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        ModelContainer m = ModelContainer.getModelInstance();
      //  m.setEventList();
        // Extract a reference to the map fragment
        //amazonMap.addPolyline();
        amazonMap = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();
        amazonMap.setMapType(AmazonMap.MAP_TYPE_HYBRID);


        Map<String, List<EventResult>> eventsType = (HashMap)m.getEventList();
        List<EventResult> events = (ArrayList)m.getEvents();
        int hue = 35;
        int numEventTypes = 0;
        for(EventResult eventResult : events){
            if(!eventsType.containsKey(eventResult.getEventType())){
                eventsType.put(eventResult.getEventType(), new ArrayList<EventResult>());
                numEventTypes++;
            }
            ((ArrayList)eventsType.get(eventResult.getEventType())).add(eventResult);
        }

        markers = new ArrayList<Marker>();
        int hueOffset = 325/numEventTypes;

        Map<Marker, Map<EventResult, PersonResult>> markerMap = new HashMap<>();


       // PersonResult
        for(Map.Entry<String, List<EventResult>> entry : eventsType.entrySet()) {
            for (EventResult event : entry.getValue()) {
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(new LatLng(event.getLatitude(), event.getLongitude()))
                        .alpha(5)
                        .icon(BitmapDescriptorFactory.defaultMarker(hue));
                Marker mark = amazonMap.addMarker(markerOptions);
                /*if(!markerMap.containsKey(mark)){
                    markerMap.put(mark, new HashMap<EventResult, PersonResult>());
                }*/
                //((HashMap)markerMap.get(mark)).put(event, );

                markers.add(mark);
            }
            int i = (hue + hueOffset +20 < 360) ? hue+= (hueOffset+15) : (hue+=hueOffset);
        }


        //amazonMap.setOnMarkerClickListener();



        //amazonMap.getCameraPosition();
        // Call .getMapAsync() and pass it an object that implements
        // the OnMapReadyCallback interface.

        return v;
    }

}
