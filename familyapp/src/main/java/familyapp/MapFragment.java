package familyapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.familyapp.R;

import java.util.Map;

/**
 * Created by jacob on 3/22/2017.
 */

public class MapFragment extends Fragment {
    private String authToken;
    //what kind of data will I need floating around here

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //what do I create here??
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                            Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        //I don't know what kind of listeners to make here.

        return v;
    }

}
