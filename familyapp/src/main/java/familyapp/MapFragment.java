package familyapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazon.geo.mapsv2.AmazonMap;
import com.amazon.geo.mapsv2.OnMapReadyCallback;
import com.example.familyapp.R;

import java.util.Map;

/**
 * Created by jacob on 3/22/2017.
 */

public class MapFragment extends Fragment {
    //what kind of data will I need floating around here

    private MapFragment mMapFragment;
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
        // Extract a reference to the map fragment

        // Call .getMapAsync() and pass it an object that implements
        // the OnMapReadyCallback interface.


        return v;
    }

}
