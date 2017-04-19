package familyapp;

import android.graphics.Color;

import com.amazon.geo.mapsv2.AmazonMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import result.EventResult;
import result.PersonResult;

/**
 * Created by jacob on 4/6/2017.
 */

public class ToolBox {
   static public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }


    static public PersonResult getMother(String uuid){
        return ModelContainer.getModelInstance().getAccessPersons().get(ModelContainer.getModelInstance().getAccessPersons().get(uuid).getMother());
    }
    static public PersonResult getSpouse(String uuid){
        return ModelContainer.getModelInstance().getAccessPersons().get(ModelContainer.getModelInstance().getAccessPersons().get(uuid).getSpouse());
    }
    static public PersonResult getFather(String uuid){
        return ModelContainer.getModelInstance().getAccessPersons().get(ModelContainer.getModelInstance().getAccessPersons().get(uuid).getFather());
    }
    static public List<PersonResult> getChildren(String uuid){
        List<PersonResult> persons = ModelContainer.getModelInstance().getPersons();
        List<PersonResult> children = new ArrayList<>();
        for(PersonResult person : persons){
            if(person.getMother().equals(uuid) || person.getFather().equals(uuid)){
                children.add(person);
            }
        }
        return children;
    }

    static public List<PersonResult> getFamily(String uuid){
        List<PersonResult> family = new ArrayList<>();
        if(getFather(uuid)!= null){family.add(getFather(uuid));}
        if(getMother(uuid)!= null){family.add(getMother(uuid));}

        for(PersonResult p : getChildren(uuid)){
            family.add(p);
        }
        return family;
    }
    static public Map<String,PersonResult> getFamilyMap(String uuid){
        Map<String ,PersonResult> family = new HashMap<>();
        family.put("father", getFather(uuid));
        family.put("mother", getMother(uuid));

        for(PersonResult p : getChildren(uuid)){
            int i = 0;
            family.put("child" + i, p);
            i++;
        }
        return family;
    }

    static public List<EventResult> getEvents(String uuid){
        List<EventResult> events = new ArrayList<>();
        for(EventResult event : ModelContainer.getModelInstance().getEvents()){
            if(event.getPersonID().equals(uuid)){
                events.add(event);
            }
        }
        return events;
    }
    public static int getIndexMap(String input){
        switch(input){
            case "Satellite":
                return AmazonMap.MAP_TYPE_SATELLITE;
            case "Hybrid":
                return AmazonMap.MAP_TYPE_HYBRID;
            case "Normal":
                return AmazonMap.MAP_TYPE_NORMAL;
            case "Terrain":
                return AmazonMap.MAP_TYPE_TERRAIN;
            default:
                return -1;
        }
    }
    public static int getIndexColor(String input){
        switch(input){
            case "Yellow":
                return Color.YELLOW;
            case "Blue":
                return Color.BLUE;
            case "Green":
                return Color.GREEN;
            case "Orange":
                return Color.rgb(255,165,0);
            case "Purple":
                return Color.MAGENTA;
            default:
                return -1;
        }
    }
}
